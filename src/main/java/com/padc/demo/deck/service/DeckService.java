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

@Service
public class DeckService implements IService<Deck>
{
    private final IDeckRepository iDeckRepository;
    private final Securitycontext securitycontext;

    // https://stackoverflow.com/questions/40620000/spring-autowire-on-properties-vs-constructor
    @Autowired
    public DeckService(IDeckRepository iDeckRepository, Securitycontext securitycontext)
    {
        this.iDeckRepository = iDeckRepository;
        this.securitycontext = securitycontext;
    }

    /**
     * To save the deck it needs to be associated with the User, so User and Deck is still in sync
     * @param element
     */
    @Override
    public void save(Deck element)
    {
        securitycontext.getUserDetailHandler().getUser().addDeck(element);
        iDeckRepository.save(element);
    }

    /**
     * Returns the Deck that matches the id that was given, but if the database didn't have a match,
     * then throw a EntityNotFoundException that can be catches later.
     * Optional takes care of not give a NullPointerException, because the return maybe non-null value
     * from the database is in the Optional container.
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

    @Override
    public List<Deck> findAll()
    {
        List<Deck> deckListUserHas = findDecksByUser();

        return deckListUserHas;
    }

    @Override
    public void deleteByID(Long id)
    {
        iDeckRepository.deleteById(id);
    }

    public List<Deck> findDecksByUser()
    {
        List<Deck> deckList = new ArrayList<>();
        Long userId = securitycontext.getUserDetailHandler().getId();
        for (Deck deck: iDeckRepository.findDecksByUser_Id(userId))
        {
            deckList.add(deck);
        }

        return deckList;
    }
}
