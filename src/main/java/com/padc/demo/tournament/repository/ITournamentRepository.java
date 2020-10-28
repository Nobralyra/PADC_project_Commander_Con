package com.padc.demo.tournament.repository;

import com.padc.demo.tournament.domain.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITournamentRepository extends JpaRepository<Tournament, Long>
{
}
