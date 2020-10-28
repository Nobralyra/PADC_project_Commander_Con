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
    private final IService iTournamentService;

    // https://stackoverflow.com/questions/40620000/spring-autowire-on-properties-vs-constructor
    @Autowired
    public TournamentController(IService iTournamentService)
    {
        this.iTournamentService = iTournamentService;
    }

    @GetMapping({"/tournament"})
    public String tournamentPage(Model model)
    {
        //model.addAttribute("tournament", iTournamentService.findAll());

        return ("/tournament/tournamentPage");
    }

    @GetMapping("/tournament/createTournament")
    public String showCreate(Tournament tournament, Model model)
    {
//        model.addAttribute("category", iCategoryCrudService.findAll());
//        model.addAttribute("company", iCompanyCrudService.findAll());
//        model.addAttribute("product", product);

        return "/tournament/createTournament";
    }

    @GetMapping("/tournament/createTournament")
    public String createProduct(@ModelAttribute("tournament") Tournament tournament, BindingResult bindingResult, Model model)
    {

        return "redirect:/";
    }
}
