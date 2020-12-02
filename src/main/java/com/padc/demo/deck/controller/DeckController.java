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

    @Autowired
    public DeckController(IService<Deck> iDeckService)
    {
        this.iDeckService = iDeckService;
    }

    /**
     * Finds all decks the authenticated user has registered
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
     *
     * @param deck - object of Deck
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
     * Checks if the input id valid or not.
     * If it is valid, then the data gets saved in the database, and should redirect back to
     * administration site (does not exist yet).
     * If it is not valid, then print out the error in the standard error stream,
     * sends the error back to the HTML (what is written in the annotation over the fields in Deck),
     * sends the data that was in the form back to the HTML,
     * and redirect to the showCreateDeck method
     *
     * @param deck - object of Deck
     * @param bindingResult - holds result of validation, binding, and contains errors that may occurred
     * @param model - holder for model attributes
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
     * @param id - id of the Deck object
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

        /**
         * Håndtere exceptions:
         *
         * https://stackoverflow.com/questions/54395695/what-are-the-best-practices-to-handler-or-throw-exceptions-in-a-spring-boot-appl
         * https://stackoverflow.com/questions/55283605/spring-mvc-should-service-return-optional-or-throw-an-exception
         * https://keepgrowing.in/category/java/page/2/
         * https://stackoverflow.com/questions/49316751/spring-data-jpa-findone-change-to-optional-how-to-use-this
         * https://stackoverflow.com/questions/60608873/optional-class-in-spring-boot
         * https://stackoverflow.com/questions/42993428/throw-exception-in-optional-in-java8/42993594
         * https://stackabuse.com/guide-to-optional-in-java-8/
         * https://medium.com/faun/working-on-null-elegantly-with-java-optional-62f5e65869c5
         */
    }

    /**
     *
     * @param deck - object of deck
     * @param bindingResult - holds result of validation, binding, and contains errors that may occurred
     * @param model - holder for model attributes
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
