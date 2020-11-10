package com.padc.demo.tournament.controller;

import com.padc.demo.core.IService;
import com.padc.demo.tournament.domain.Tournament;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class TournamentController
{
    @Autowired
    private final IService<Tournament> iTournamentService;

    // https://stackoverflow.com/questions/40620000/spring-autowire-on-properties-vs-constructor

    public TournamentController(IService<Tournament> iTournamentService)
    {
        this.iTournamentService = iTournamentService;
    }

    @GetMapping("/turnering/turnering_side/{id}")
    public String tournamentPage(@PathVariable("id") long id, Model model)
    {
        Tournament tournament = iTournamentService.findById(id).get();
        model.addAttribute("tournament", tournament);

        return ("/turnering/turnering_side");
    }

    @GetMapping("/turnering/opret_turnering")
    public String showCreateTournament(Tournament tournament, Model model)
    {
        model.addAttribute("tournament", tournament);

        return "/turnering/opret_turnering";
    }

    /**
     * Checks if the input id valid or not.
     * If it is valid, then the data gets saved in the database, and should redirect back to
     * administration site (does not exist yet).
     * If it is not valid, then print out the error in the standard error stream,
     * sends the error back to the HTML (what is written in the annotation over the fields in Tournament),
     * sends the data that was in the form back to the HTML,
     * and redirect to the showCreateTournament method
     * @param tournament
     * @param bindingResult
     * @param model
     * @return String
     */
    @PostMapping("/turnering/opret_turnering")
    public String createTournament(@Valid Tournament tournament, BindingResult bindingResult, Model model)
    {
        if(!bindingResult.hasErrors())
        {
            iTournamentService.save(tournament);
        }
        else
        {
            System.err.println(bindingResult);
            model.addAttribute("bindingResult", bindingResult);
            model.addAttribute("tournament", tournament);

            return "/turnering/opret_turnering";
        }
        return "redirect:/turnering";
    }
}
