package com.padc.demo.deck.repository;

import com.padc.demo.deck.domain.Deck;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Called by DeckService
 */
public interface IDeckRepository extends JpaRepository<Deck, Long>
{
    /**
     * Spring Data JPA supports custom database queries and those can be made with the annotation @Query or query methods.
     * Here it is a query method that finds all decks that is associated with a specific users id.
     *
     * https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods
     * https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories.query-methods.details
     *
     * @param userId - Id of the User object
     * @return List<Deck>
     */
    List<Deck> findDecksByUser_Id(Long userId);
}
