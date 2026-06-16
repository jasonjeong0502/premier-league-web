package com.pl.premier_league.player;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController /* @Controller + @ResponseBody => returns JSON response instead of view template => more potential for front-end to manipulate that JSON data */
@RequestMapping(path = "api/v1/player") /* base URL path for all endpoints: matches any GET, POST, PUT, DELETE request */
public class PlayerController {
    private final PlayerService playerService;

    @Autowired /* Injects playerService into controller */
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping /* typing in URL will always call GET /api/v1/player, not POST, PUT, DELETE*/
    public List<Player> getPlayers( /* ? signals query string in URL, URL query should usually match variable name defined within this method(unless you specified otherwise) */
            @RequestParam(required = false) String team, /* use & for linking multiple arguments*/
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String position,
            @RequestParam(required = false) String nation) {
        if(team != null && position != null) {
            return playerService.getPlayersByTeamAndPosition(team, position);
        } else if (team != null) {
            return playerService.getPlayersFromTeam(team);
        } else if (name != null) {
            return playerService.getPlayersByName(name);
        } else if (position != null) {
            return playerService.getPlayersByPosition(position);
        } else if (nation != null) {
            return playerService.getPlayersByNation(nation);
        } else {
            return playerService.getPlayers();
        }
    }

    @GetMapping("/{playerName}")
    public ResponseEntity<Player> getPlayerByName(@PathVariable String playerName) {

        return playerService.getPlayerByName(playerName)
                .map(player -> new ResponseEntity<>(player, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));

    } /* ResponseEntity<>: Player is converted to ResponseEntity, where you can can control status code, access response body(player data), and headers */

    @PostMapping /* Handles HTTP Post requests to add player to db */

    /*@RequestBody: client sends /api/v1/player with JSON body that includes player data, Spring sees @RequestBody and creates a Player object with data*/
    public ResponseEntity<Player> addPlayer(@RequestBody Player player) {
        Player createdPlayer = playerService.addPlayer(player);
        return new ResponseEntity<>(createdPlayer, HttpStatus.CREATED);
    } /* Returns 200 if player creation was successful */


    @PutMapping /* Handles HTTP Put requests to update player in db */
    public ResponseEntity<Player> updatePlayer(@RequestBody Player player) {
        Player resultPlayer = playerService.updatePlayer(player);
        if(resultPlayer != null) {
            return new ResponseEntity<>(resultPlayer, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{playerName}") /* Handle HTTP Delete requests to delete player */
    /* /api/v1/player/Haaland passes in Haaland to playerName */
    public ResponseEntity<String>  deletePlayer(@PathVariable String playerName) {
        playerService.deletePlayer(playerName);
        return new ResponseEntity<>("Player deleted successfully", HttpStatus.OK);
    }

}