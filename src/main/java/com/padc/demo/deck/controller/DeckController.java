package com.padc.demo.deck.controller;

import com.padc.demo.core.IService;
import com.padc.demo.deck.domain.Deck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class DeckController
{

    private final IService<Deck> iDeckService;

    @Autowired
    public DeckController(IService<Deck> iDeckService)
    {
        this.iDeckService = iDeckService;
    }

    @GetMapping("/deck")
    public String deckPage(Model model)
    {
        model.addAttribute("deck", iDeckService.findAll());
        return "deck/mine_decks";
    }

    @GetMapping("/deck/opret_deck")
    public String showCreateDeck(Deck deck, Model model)
    {
        model.addAttribute("deck", deck);

        return "/deck/opret_deck";
    }

    /**
     * Checks if the input id valid or not.
     * If it is valid, then the data gets saved in the database, and should redirect back to
     * administration site (does not exist yet).
     * If it is not valid, then print out the error in the standard error stream,
     * sends the error back to the HTML (what is written in the annotation over the fields in Deck),
     * sends the data that was in the form back to the HTML,
     * and redirect to the showCreateDeck method
     * @param deck
     * @param bindingResult
     * @param model
     * @return String
     */
    @PostMapping("/deck/opret_deck")
    public String createDeck(@Valid Deck deck, BindingResult bindingResult, Model model)
    {
        if(!bindingResult.hasErrors())
        {
            iDeckService.save(deck);
        }
        else
        {
            System.err.println(bindingResult);
            model.addAttribute("bindingResult", bindingResult);
            model.addAttribute("deck", deck);

            return "/deck/opret_deck";
        }
        return "redirect:/deck";
    }
}
