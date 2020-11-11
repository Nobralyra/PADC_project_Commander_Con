package com.padc.demo.tournament.service;

import com.padc.demo.core.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.padc.demo.tournament.domain.Tournament;
import com.padc.demo.tournament.repository.ITournamentRepository;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class TournamentService implements IService<Tournament>
{
    @Autowired
    private final ITournamentRepository iTournamentRepository;

    // https://stackoverflow.com/questions/40620000/spring-autowire-on-properties-vs-constructor

    public TournamentService(ITournamentRepository iTournamentRepository)
    {
        this.iTournamentRepository = iTournamentRepository;
    }

    @Override
    public void save(Tournament element)
    {
        iTournamentRepository.save(element);
    }

    @Override
    public Tournament findById(long id)
    {
        Optional<Tournament> tournament = iTournamentRepository.findById(id);

        return tournament.orElseThrow(EntityNotFoundException::new);
    }

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

    @Override
    public void deleteByID(long id)
    {
        iTournamentRepository.deleteById(id);
    }
}
