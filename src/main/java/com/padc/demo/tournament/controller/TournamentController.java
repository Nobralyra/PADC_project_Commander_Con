package com.padc.demo.tournament.controller;

import com.padc.demo.core.IService;
import com.padc.demo.tournament.domain.Tournament;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TournamentController
{
    private final IService<Tournament> iTournamentService;

    // https://stackoverflow.com/questions/40620000/spring-autowire-on-properties-vs-constructor
    @Autowired
    public TournamentController(IService<Tournament> iTournamentService)
    {
        this.iTournamentService = iTournamentService;
    }

    @GetMapping({"/turnering"})
    public String tournamentPage(Model model)
    {
        //model.addAttribute("tournament", iTournamentService.findAll());

        return ("/turnering/turnering_side");
    }

    @GetMapping("/turnering/opret_turnering")
    public String showCreateTournament(Tournament tournament, Model model)
    {
        model.addAttribute("tournament");

        return "/turnering/opret_turnering";
    }

    @PostMapping("/turnering/opret_turnering")
    public String createTournament(@ModelAttribute("tournament") Tournament tournament, BindingResult bindingResult, Model model)
    {

        return "redirect:/";
    }
}
