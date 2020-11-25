package com.padc.demo.deck.repository;

import com.padc.demo.deck.domain.Deck;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDeckRepository extends JpaRepository<Deck, Long>
{
}
