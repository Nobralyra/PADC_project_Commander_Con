package com.padc.demo.tournament.service;

import com.padc.demo.core.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.padc.demo.tournament.domain.Tournament;
import com.padc.demo.tournament.repository.ITournamentRepository;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Called by TournamentController through the IService interface
 */
@Service
public class TournamentService implements IService<Tournament>
{

    private final ITournamentRepository iTournamentRepository;

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
     * @param iTournamentRepository - interface of provided methods
     */
    @Autowired
    public TournamentService(ITournamentRepository iTournamentRepository)
    {
        this.iTournamentRepository = iTournamentRepository;
    }

    /**
     * Saves the tournament in the database
     * @param element - object of Tournament
     */
    @Override
    public void save(Tournament element)
    {
        iTournamentRepository.save(element);
    }

    /**
     * Returns the Deck that matches the id that was given, but if the database didn't have a match,
     * then throw a EntityNotFoundException that can be catches later.
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
     * @param id - id of Tournament object
     * @return Tournament
     */
    @Override
    public Tournament findById(Long id)
    {
        Optional<Tournament> tournament = iTournamentRepository.findById(id);

        /*The double colon operator :: is used to call a method/constructor
        by referring to the class. Syntax: <<Class name>> :: <<method or constructor>>*/
        return tournament.orElseThrow(EntityNotFoundException::new);
    }

    /**
     * Finds all tournament entities in the database
     * For each loop adds what method findAll returns to the tournamentList
     * @return List<Tournament>
     */
    @Override
    public List<Tournament> findAll()
    {
        List<Tournament> tournamentList = new ArrayList<>();

        for (Tournament tournament: iTournamentRepository.findAll())
        {
            tournamentList.add(tournament);
        }
        return tournamentList;
    }

    /**
     * Deletes a specific tournament entity
     * @param id - id of Tournament object
     */
    @Override
    public void deleteByID(Long id)
    {
        iTournamentRepository.deleteById(id);
    }
}
