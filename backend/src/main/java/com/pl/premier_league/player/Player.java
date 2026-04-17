package com.pl.premier_league.player;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/* JPA = Java Persistence API: standard specification in Java for managing relational data, allowing developers to map Java objects to database tables (ORM) using annotations or XML
 -> included in Jakarta Persistence */

@Entity /* Marks the class as a JPA entity - tells Spring that player class will be mapped to db */
@Table(name= "player_stat") /* states that entity should use a db table named "player_stat": Player <-> player_stat */
public class Player {
    @Id /* marks a field as primary key - every JPA entity must have one identifier filed  */
    /* annotation specifies that primary key of player entity is Name */
    @Column(name = "player_name", unique = true) /* Maps Java field to a specific column in database */
    /* name = "player_name" means the name field in this class will be mapped to db column called player_name */
    private String name;                         /* unique = true means there must be no duplicates within that column - no 2 player has same name */
    private String nation;
    private String position;
    private Integer age;
    private Integer matches;
    private Integer starts;
    private Double minutes;
    private Double goals;
    private Double assists;
    private Double penalties;
    private Double yellow;
    private Double red;
    private Double expected_goals;
    private Double expected_assists;
    private String team_name;

    public Player() {
    }

    public Player(String name, String nation, String position, Integer age, Integer matches, Integer starts, Double minutes, Double goals, Double assists, Double penalties, Double yellow, Double red, Double expected_goals, Double expected_assists, String team_name) {
        this.name = name;
        this.nation = nation;
        this.position = position;
        this.age = age;
        this.matches = matches;
        this.starts = starts;
        this.minutes = minutes;
        this.goals = goals;
        this.assists = assists;
        this.penalties = penalties;
        this.yellow = yellow;
        this.red = red;
        this.expected_goals = expected_goals;
        this.expected_assists = expected_assists;
        this.team_name = team_name;
    }

    public Player(String name) {
        this.name = name;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getMatches() {
        return matches;
    }

    public void setMatches(Integer matches) {
        this.matches = matches;
    }

    public Integer getStarts() {
        return starts;
    }

    public void setStarts(Integer starts) {
        this.starts = starts;
    }

    public Double getMinutes() {
        return minutes;
    }

    public void setMinutes(Double minutes) {
        this.minutes = minutes;
    }

    public Double getGoals() {
        return goals;
    }

    public void setGoals(Double goals) {
        this.goals = goals;
    }

    public Double getAssists() {
        return assists;
    }

    public void setAssists(Double assists) {
        this.assists = assists;
    }

    public Double getPenalties() {
        return penalties;
    }

    public void setPenalties(Double penalties) {
        this.penalties = penalties;
    }

    public Double getYellow() {
        return yellow;
    }

    public void setYellow(Double yellow) {
        this.yellow = yellow;
    }

    public Double getRed() {
        return red;
    }

    public void setRed(Double red) {
        this.red = red;
    }

    public Double getExpectedGoals() {
        return expected_goals;
    }

    public void setExpectedGoals(Double expected_goals) {
        this.expected_goals = expected_goals;
    }

    public Double getExpectedAssists() {
        return expected_assists;
    }

    public void setExpectedAssists(Double expected_assists) {
        this.expected_assists = expected_assists;
    }

    public String getTeamName() {
        return team_name;
    }

    public void setTeamName(String team_name) {
        this.team_name = team_name;
    }
}
