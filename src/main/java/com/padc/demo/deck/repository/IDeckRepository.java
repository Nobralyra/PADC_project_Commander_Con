package com.padc.demo.deck.repository;

import com.padc.demo.deck.domain.Deck;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IDeckRepository extends JpaRepository<Deck, Long>
{
    /**
     * Spring Data JPA supports custom database queries.
     * It can be made with query methods or @Query
     *
     * https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods
     * https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories.query-methods.details
     * @param userId
     * @return
     */
    List<Deck> findDecksByUser_Id(Long userId);
}
