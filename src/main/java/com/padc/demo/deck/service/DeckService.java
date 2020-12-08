package com.padc.demo.deck.service;

import com.padc.demo.core.IService;
import com.padc.demo.core.security.Securitycontext;
import com.padc.demo.deck.domain.Deck;
import com.padc.demo.deck.repository.IDeckRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Called by DeckController through the IService interface
 */
@Service
public class DeckService implements IService<Deck>
{
    private final IDeckRepository iDeckRepository;
    private final Securitycontext securitycontext;

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
     * @param iDeckRepository - interface of provided methods
     * @param securitycontext - gives details about user
     */
    @Autowired
    public DeckService(IDeckRepository iDeckRepository, Securitycontext securitycontext)
    {
        this.iDeckRepository = iDeckRepository;
        this.securitycontext = securitycontext;
    }

    /**
     * To save a deck the deck needs to be associated with a users id because the relationship is bidirectional.
     * The id comes from what user that created the deck.
     *
     * @param element - object of Deck
     */
    @Override
    public void save(Deck element)
    {
        securitycontext.getUserDetailHandler().getUser().addDeck(element);
        iDeckRepository.save(element);
    }

    /**
     * Returns the Deck that matches the id that was given, but if the database didn't have a match,
     * then throw a EntityNotFoundException that can be catch later.
     * Optional takes care of a potential NullPointerException because the database maybe returns a null value
     * if it did not find a match to the given id. Optional is container that can hold non-null values.
     *
     * To understand how to use Optional:
     * https://stackoverflow.com/questions/55283605/spring-mvc-should-service-return-optional-or-throw-an-exception
     * https://stackoverflow.com/questions/49316751/spring-data-jpa-findone-change-to-optional-how-to-use-this
     * https://stackoverflow.com/questions/60608873/optional-class-in-spring-boot
     * https://stackoverflow.com/questions/42993428/throw-exception-in-optional-in-java8/42993594
     * https://stackabuse.com/guide-to-optional-in-java-8/
     *
     * To understand how to use Optional and a idea to expansion with a exception controller
     * https://stackoverflow.com/questions/54395695/what-are-the-best-practices-to-handler-or-throw-exceptions-in-a-spring-boot-appl
     *
     * @param id - id of Deck object
     * @return Deck
     */
    @Override
    public Deck findById(Long id)
    {
        Optional<Deck> deck = iDeckRepository.findById(id);

        /*The double colon operator :: is used to call a method/constructor
        by referring to the class. Syntax: <<Class name>> :: <<method or constructor>>*/
        return deck.orElseThrow(EntityNotFoundException::new);
    }

    /**
     * Returns a list of decks that findDecksByUser has found
     *
     * @return List<Deck>
     */
    @Override
    public List<Deck> findAll()
    {
        List<Deck> deckListUserHas = findDecksByUser();

        return deckListUserHas;
    }

    /**
     * Deletes a specific deck entity
     *
     * @param id - id of Deck object
     */
    @Override
    public void deleteByID(Long id)
    {
        iDeckRepository.deleteById(id);
    }

    /**
     * Finds all deck entities who is associated with users id
     * For each loop adds what method findDeckByUser_Id(userId) returns to the deckList
     *
     * @return List<Deck>
     */
    public List<Deck> findDecksByUser()
    {
        List<Deck> deckList = new ArrayList<>();
        Long userId = securitycontext.getUserDetailHandler().getId();
        for (Deck deck : iDeckRepository.findDecksByUser_Id(userId))
        {
            deckList.add(deck);
        }

        return deckList;
    }
}
