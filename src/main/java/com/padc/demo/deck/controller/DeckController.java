package com.padc.demo.deck.controller;

import com.padc.demo.core.IService;
import com.padc.demo.deck.domain.Deck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

@Controller
public class DeckController
{
    private final IService<Deck> iDeckService;

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
     * @param iDeckService - interface of provided methods
     */
    @Autowired
    public DeckController(IService<Deck> iDeckService)
    {
        this.iDeckService = iDeckService;
    }

    /**
     * Finds all decks the authenticated user has registered
     *
     * @param model - holder for model attributes
     * @return String
     */
    @GetMapping("/deck")
    public String deckPage(Model model)
    {
        model.addAttribute("deck", iDeckService.findAll());
        return "deck/mine_decks";
    }

    /**
     * Provides the Deck object so user can register a deck
     *
     * @param deck  - object of Deck
     * @param model - holder for model attributes
     * @return String
     */
    @GetMapping("/deck/opret_deck")
    public String showCreateDeck(Deck deck, Model model)
    {
        model.addAttribute("deck", deck);

        return "/deck/opret_deck";
    }

    /**
     * Checks if the input of creating a new deck is valid or not.
     * If it is valid, then the data gets saved in the database, and should redirect back to
     * deck site.
     * If it is not valid, then print out the error in the standard error stream,
     * sends the error back to the HTML (what is written in the annotation over the fields in Deck),
     * sends the data that was in the form back to the HTML, and redirect to the showCreateDeck method.
     *
     * @param deck          - object of Deck
     * @param bindingResult - holds result of validation, binding, and contains errors that may occurred
     * @param model         - holder for model attributes
     * @return String
     */
    @PostMapping("/deck/opret_deck")
    public String createDeck(@Valid Deck deck, BindingResult bindingResult, Model model)
    {
        if (!bindingResult.hasErrors())
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

    /**
     * Use @PathVariable to bound id from URL to method parameter
     * If a deck needs to be updated, the user clicks on that deck "opdater" button. The "opdater" button provide
     * the id of what specific deck the user want to update.
     * We then use that id to find the entity of the deck in the database, and returns the result back to the HTML
     * where user can update the information of that deck.
     *
     * If the database could not find a deck that matches the given id, then DeckService throw a EntityNotFoundException
     * that get caught here.
     *
     * @param id    - id of the Deck object
     * @param model - holder for model attributes
     * @return String
     */
    @GetMapping("/deck/opdater_deck/{id}")
    public String showUpdateDeck(@PathVariable("id") Long id, Model model)
    {
        try
        {
            model.addAttribute("deck", iDeckService.findById(id));
            return "/deck/opdater_deck";
        }
        catch (EntityNotFoundException entityNotFoundException)
        {
            entityNotFoundException.printStackTrace(); // Goes to System.err
            entityNotFoundException.printStackTrace(System.out);
            return "redirect:/deck";
        }
    }

    /**
     * Checks if the input of updating a deck is valid or not.
     * If it is valid, then the data gets saved in the database, and should redirect back to
     * deck site.
     * If it is not valid, then print out the error in the standard error stream,
     * sends the error back to the HTML (what is written in the annotation over the fields in Deck),
     * sends the data that was in the form back to the HTML,
     * and redirect to the showCreateDeck method
     *
     * @param deck          - object of deck
     * @param bindingResult - holds result of validation, binding, and contains errors that may occurred
     * @param model         - holder for model attributes
     * @return String
     */
    @PostMapping("/deck/opdater_deck")
    public String updateDeck(@ModelAttribute @Valid Deck deck, BindingResult bindingResult, Model model)
    {
        if (!bindingResult.hasErrors())
        {
            iDeckService.save(deck);
        }
        else
        {
            System.err.println(bindingResult);
            model.addAttribute("bindingResult", bindingResult);
            model.addAttribute("deck", deck);

            return "/deck/opdater_deck";
        }
        return "redirect:/deck";
    }

    /**
     * If user want to delete a deck, the user click on that deck "Slet" button. The "slet" button provide
     * the id of what specific deck the user want to delete.
     * We then use that id to delete the entity of the deck in the database.
     *
     * @param id - id of the Deck object
     * @return String
     */
    @GetMapping("/deck/slet_deck/{id}")
    public String deleteDeck(@PathVariable("id") Long id)
    {
        iDeckService.deleteByID(id);

        return "redirect:/deck";
    }
}
