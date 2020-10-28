package com.padc.demo.tournament.controller;

import com.padc.demo.core.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class TournamentController
{
    private final IService iTournamentService;

    // https://stackoverflow.com/questions/40620000/spring-autowire-on-properties-vs-constructor
    @Autowired
    public TournamentController(IService iTournamentService)
    {
        this.iTournamentService = iTournamentService;
    }
}
