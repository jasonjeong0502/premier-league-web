package com.pl.premier_league.player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository; /* indicates that this is a spring data repo */

import java.util.Optional;

@Repository /* marks a class as Data Access Object (DAO), which talks to the database */
/* DAO: separates the business logic from accessing, storing, and fetching data */
/* primary goal: separation of concerns -> By encapsulating database-specific code within dedicated objects, you ensure that the rest of your application doesn't need to know whether the data is stored in a SQL database, a flat file, or an external API */

/* PlayerRepository interface inherits JpaRepository's components */
public interface PlayerRepository extends JpaRepository<Player, String> { /* JpaRepository - Spring Data JPA interface that provides CRUD, sorting, and pagination functions for Player Entity */
    void deleteByName(String playerName);                                 /* JpaRepository<EntityType, PrimaryKeyType> means the repository manages Player Objects, and the ID used to identify each Player is String */
    /* PrimaryKeyType has to be string b/c @Id is private String name as shown in Player.java */
    Optional<Player> findByName(String name);  /* use Optional<T> Wrapper to handle cases when Player isn't found in repo */
    /* T = Player, ID(Key) = String */
}