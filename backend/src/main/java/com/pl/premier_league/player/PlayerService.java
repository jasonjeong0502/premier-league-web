package com.pl.premier_league.player;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/* Service Layer - encapsulates business logic for managing players - sits b/t controller and repository to interact with db */
/* leverage PlayerRepository interface to perform operations on players */

@Component  /* marks class as general-purpose Spring bean  */
/* tells Spring container to automatically detect this class, create an instance, and manage its entire life-cycle */
/* Other spring classes can inject and use this bean (PlayerService Class) */
/* Without this annotation, Spring would not automatically create PlayerService instance, and dependency injection(@Autowired) would fail*/
public class PlayerService {
    private final PlayerRepository playerRepository; // interface


    @Autowired /* Injects dependency automatically - PlayerService class depends on playerRepository to talk to database -> Spring injects playerRepository bean into constructor automatically */
    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public List<Player> getPlayers() {
        return playerRepository.findAll(); /* we defined T = Player in "extends JpaRepository<Player, String>", and findAll() is defined in JpaRepository */
    }
    /* method chaining: the previous method call must return a non-void object that can be called by the next method
                      : Java evaluates chained methods from left to right
                      : For a chain to work, the method on the right must be defined in the class or interface of the object returned by the method on the left
                      : Don't confuse it as return value of previous method == input parameter of next method / we only care about the previous method's return value's Object type and what method it provides by default
                      : However, the final returning product's type must match the return type of method      */
    /* filter() is a Stream method, returns another stream that matches the condition(predicate) */

    public List<Player> getPlayersFromTeam(String teamName) {

        return playerRepository.findAll().stream()
                .filter(player -> teamName.equals(player.getTeamName()))
                .collect(Collectors.toList());
    }

    public List<Player> getPlayersByName(String searchText) {

        return playerRepository.findAll().stream()
                .filter(player -> player.getName().toLowerCase().contains(searchText.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Player> getPlayersByPosition(String searchText) {

        return playerRepository.findAll().stream()
                .filter(player -> player.getPosition() != null &&
                        player.getPosition().toLowerCase().contains(searchText.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Player> getPlayersByNation(String searchText) {

        return playerRepository.findAll().stream()
                .filter(player -> player.getNation() != null &&
                        player.getNation().toLowerCase().contains(searchText.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Player> getPlayersByTeamAndPosition(String team, String position) {

        return playerRepository.findAll().stream()
                .filter(player -> player.getPosition() != null && player.getTeamName() != null &&
                        team.equals(player.getTeamName()) && position.equals(player.getPosition()))
                .collect(Collectors.toList());
    }

    public Player addPlayer(Player player) {
        playerRepository.save(player);
        return player;
    }

    public Player updatePlayer(Player updatedPlayer) { /* updatedPlayer object is already set up with the values I want to change */
        Optional<Player> existingPlayer = playerRepository.findByName(updatedPlayer.getName()); // checks if player to update exists

        if(existingPlayer.isPresent()) {
            Player playerToUpdate = existingPlayer.get();
            playerToUpdate.setName(updatedPlayer.getName());
            playerToUpdate.setTeamName(updatedPlayer.getTeamName());
            playerToUpdate.setPosition(updatedPlayer.getPosition());
            playerToUpdate.setNation(updatedPlayer.getNation());

            playerRepository.save(playerToUpdate);
            return playerToUpdate;
        }
        return null;
    }

    @Transactional /* maintains data integrity during deletion */
    /* wraps the entire method in a transaction to prevent db damage */
    /* Spring starts transaction before method execution, if method succeeds - commits transaction, if method fails - rolls back transaction */
    public void deletePlayer(String playerName) {
        playerRepository.deleteByName(playerName);
    }

    /* findAll(): Java compiler knows T = Player b/c findAll() is in JpaRepository<Player, String> and playerRepository extends it */
    /* We've defined T = Player, ID = String inside JpaRepository */
    /* findAll() returns List<T> <-> List<Player> */

    /* stream(): returns a stream with List<Player> as its source */
    /* filter(Predicate<? super T> predicate): Predicate<T> is a functional interface with one method: boolean test(T t) */
    /* Since lambda expressions implement functional interface methods, in this case test(T t), the compiler can tell T t = player, and teamName.equals(player.getTeam()) is the implementation of test(T t) */
}