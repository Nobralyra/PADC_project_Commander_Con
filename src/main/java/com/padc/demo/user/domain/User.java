package com.padc.demo.user.domain;

import com.padc.demo.core.auditing.Audition;
import com.padc.demo.core.enums.Role;
import com.padc.demo.deck.domain.Deck;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

/**
 * Lombok is a dependency that creates constructors, mutators and accessors with annotations
 * //https://github.com/springframeworkguru/ssc-brewery/blob/db-project-lombok/src/main/java/guru/sfg/brewery/domain/security/User.java
 * This is a entity = table in the database. It consists 3 tables, because contact_info and login_info are
 * separate secondary tables due to normalization forms
 * NotBlank annotations makes sure that fields has input
 * The user has only one role in this system
 */

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="user")
@SecondaryTable(name="contact_info")
@SecondaryTable(name="login_info")
public class User extends Audition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Indtast venligst brugernavn")
    @Column(table = "login_info")
    private String username;
    @NotBlank(message = "Indtast venligst kodeord")
    @Column(table = "login_info")
    private String password;

    private Role role;

    @NotBlank(message = "Indtast venligst fornavn")
    private String firstName;

    @NotBlank(message = "Indtast venligst efternavn")
    private String lastName;

    @NotBlank(message = "Indtast venligst e-mail")
    @Column(table ="contact_info")
    private String emailAddress;

    @Column(table ="contact_info")
    private String phoneNumber;


    /**
     * mappedBy = "user" attribute specifies that the field "private User user;" in class Deck,
     * owns the relationship (is the owner), and therefore contains the foreign key to query findAll
     * decks for an user.
     *
     * https://en.wikibooks.org/wiki/Java_Persistence/OneToMany
     */
    @OneToMany(cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY,
            mappedBy = "user")
    private List<Deck> listOfDecks = new ArrayList<>();


    /**
     * For adding an entity from the relationship, we need to update both sides of the association.
     * Instead of doing all the steps in DeckService, we call this utility method that updates both entities, and
     * setUser in deck, so it knows who created it.
     *
     * https://thorben-janssen.com/best-practices-many-one-one-many-associations-mappings/
     * https://en.wikibooks.org/wiki/Java_Persistence/OneToMany
     * https://medium.com/@rajibrath20/the-best-way-to-map-a-onetomany-relationship-with-jpa-and-hibernate-dbbf6dba00d3
     * @param deck - object of Deck
     */

    public void addDeck(Deck deck)
    {
        listOfDecks = new ArrayList<>();
        this.listOfDecks.add(deck);
        if (deck.getUser() != this)
        {
            deck.setUser(this);
        }
    }

    /**
     * Not needed here, because we actually delete the entire deck and not just the user in deck
     * For removing an entity from the relationship, we need to update both sides of the association.
     * Instead of doing it in the Service, it can be done with af utility method that updates both entities.
     *
     * https://thorben-janssen.com/best-practices-many-one-one-many-associations-mappings/
     * https://en.wikibooks.org/wiki/Java_Persistence/OneToMany
     * https://medium.com/@rajibrath20/the-best-way-to-map-a-onetomany-relationship-with-jpa-and-hibernate-dbbf6dba00d3
     * @param deck - object of Deck
     */
//    public void removeDeck(Deck deck)
//    {
//        this.listOfDecks.remove(deck);
//        deck.setUser(null);
//    }
}
