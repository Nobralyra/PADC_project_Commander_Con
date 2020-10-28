package com.padc.demo.tournament.service;

import com.padc.demo.core.IService;
import org.springframework.stereotype.Service;
import com.padc.demo.tournament.domain.Tournament;
import com.padc.demo.tournament.repository.ITournamentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TournamentService implements IService<Tournament>
{
    private final ITournamentRepository iTournamentRepository;

    public TournamentService(ITournamentRepository iTournamentRepository)
    {
        this.iTournamentRepository = iTournamentRepository;
    }

    @Override
    public void save(Tournament element)
    {

    }

    @Override
    public Optional<Tournament> findById(long id)
    {
        return Optional.empty();
    }

    @Override
    public List<Tournament> findAll()
    {
        return null;
    }

    @Override
    public void deleteByID(long id)
    {

    }
}
