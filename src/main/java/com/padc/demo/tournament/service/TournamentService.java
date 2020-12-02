package com.padc.demo.tournament.service;

import com.padc.demo.core.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.padc.demo.tournament.domain.Tournament;
import com.padc.demo.tournament.repository.ITournamentRepository;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TournamentService implements IService<Tournament>
{

    private final ITournamentRepository iTournamentRepository;

    /**
     * https://stackoverflow.com/questions/40620000/spring-autowire-on-properties-vs-constructor
     * @param iTournamentRepository
     */
    @Autowired
    public TournamentService(ITournamentRepository iTournamentRepository)
    {
        this.iTournamentRepository = iTournamentRepository;
    }

    /**
     * Saves the tournament in the database
     * @param element - object of Tournament
     */
    @Override
    public void save(Tournament element)
    {
        iTournamentRepository.save(element);
    }

    /**
     * Returns the Deck that matches the id that was given, but if the database didn't have a match,
     * then throw a EntityNotFoundException that can be catches later.
     * Optional takes care of not give a NullPointerException, because the return maybe non-null value
     * from the database is in the Optional container.
     * @param id - id of Tournament object
     * @return Tournament
     */
    @Override
    public Tournament findById(Long id)
    {
        Optional<Tournament> tournament = iTournamentRepository.findById(id);

        /*The double colon operator :: is used to call a method/constructor
        by referring to the class. Syntax: <<Class name>> :: <<method or constructor>>*/
        return tournament.orElseThrow(EntityNotFoundException::new);
    }

    /**
     * Finds all tournament entities in the database
     * For each loop adds what method findAll returns to the tournamentList
     * @return List<Tournament>
     */
    @Override
    public List<Tournament> findAll()
    {
        List<Tournament> tournamentList = new ArrayList<>();

        for (Tournament tournament: iTournamentRepository.findAll())
        {
            tournamentList.add(tournament);
        }
        return tournamentList;
    }

    /**
     * Deletes a specific tournament entity
     * @param id - id of Tournament object
     */
    @Override
    public void deleteByID(Long id)
    {
        iTournamentRepository.deleteById(id);
    }
}
