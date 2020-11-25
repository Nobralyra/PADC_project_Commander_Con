package com.padc.demo.deck.service;

import com.padc.demo.core.IService;
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

    // https://stackoverflow.com/questions/40620000/spring-autowire-on-properties-vs-constructor
    @Autowired
    public DeckService(IDeckRepository iDeckRepository)
    {
        this.iDeckRepository = iDeckRepository;
    }

    @Override
    public void save(Deck element)
    {
        iDeckRepository.save(element);
    }

    /**
     * Returns the Deck that matches the id that was given, but if the database didn't have a match,
     * then throw a EntityNotFoundException that can be catches later.
     * Optional takes care of not give a NullPointerException, because the return maybe non-null value
     * from the database is in the Optional container.
     * @param id
     * @return Deck
     */
    @Override
    public Deck findById(long id)
    {
        Optional<Deck> deck = iDeckRepository.findById(id);

        /*The double colon operator :: is used to call a method/constructor
        by referrring to the class. Syntax: <<Class name>> :: <<method or constructor>>*/
        return deck.orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<Deck> findAll()
    {
        List<Deck> deckList = new ArrayList<>();

        for (Deck deck: iDeckRepository.findAll())
        {
            deckList.add(deck);
        }
        return deckList;
    }

    @Override
    public void deleteByID(long id)
    {
        iDeckRepository.deleteById(id);
    }
}
