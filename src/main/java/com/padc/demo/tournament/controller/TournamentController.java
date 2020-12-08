package com.padc.demo.tournament.controller;

import com.padc.demo.core.IService;
import com.padc.demo.tournament.domain.Tournament;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class TournamentController
{
    private final IService<Tournament> iTournamentService;

    /**
     * Constructor injection
     * We do not have to specify @Autowired, as long as the class only have one constructor and the class itself
     * is annotated with a Spring bean, because Spring automatic make the dependency to be injected via the constructor.
     * It is used here just for readability
     *
     * To understand how constructor injection works:
     * https://stackoverflow.com/questions/40620000/spring-autowire-on-properties-vs-constructor
     * https://reflectoring.io/constructor-injection/
     *
     * @param iTournamentService - interface of provided methods
     */
    @Autowired
    public TournamentController(IService<Tournament> iTournamentService)
    {
        this.iTournamentService = iTournamentService;
    }

    /**
     * Finds all tournaments available and shows it to ROLE_ORGANIZER and ROLE_ADMIN
     *
     * @param model - holder for model attributes
     * @return String
     */
    @Secured({"ROLE_ORGANIZER", "ROLE_ADMIN"})
    @GetMapping("/turnering")
    public String tournamentPage(Model model)
    {
        model.addAttribute("tournament", iTournamentService.findAll());
        return "turnering/turnering_side";
    }

    /**
     * Provides the Tournament object so ROLE_ORGANIZER and ROLE_ADMIN can register a tournament
     *
     * @param tournament - object of Tournament
     * @param model      - holder of model attributes
     * @return String
     */
    @Secured({"ROLE_ORGANIZER", "ROLE_ADMIN"})
    @GetMapping("/turnering/opret_turnering")
    public String showCreateTournament(Tournament tournament, Model model)
    {
        model.addAttribute("tournament", tournament);

        return "/turnering/opret_turnering";
    }

    /**
     * Checks if the input of creating a new tournament is valid or not.
     * If it is valid, then the data gets saved in the database, and should redirect back to
     * turnering site.
     * If it is not valid, then print out the error in the standard error stream,
     * sends the error back to the HTML (what is written in the annotation over the fields in Tournament),
     * sends the data that was in the form back to the HTML, and redirect to the showCreateTournament method.
     *
     * @param tournament    - object of Tournament
     * @param bindingResult - holds result of validation, binding, and contains errors that may occurred
     * @param model         - holder for model attributes
     * @return String
     */
    @Secured({"ROLE_ORGANIZER", "ROLE_ADMIN"})
    @PostMapping("/turnering/opret_turnering")
    public String createTournament(@Valid Tournament tournament, BindingResult bindingResult, Model model)
    {
        if (!bindingResult.hasErrors())
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
