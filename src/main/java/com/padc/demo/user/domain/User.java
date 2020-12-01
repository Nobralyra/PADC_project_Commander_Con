package com.padc.demo.user.domain;

import com.padc.demo.core.auditing.Audition;
import com.padc.demo.core.enums.Role;
import com.padc.demo.deck.domain.Deck;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

//https://github.com/springframeworkguru/ssc-brewery/blob/db-project-lombok/src/main/java/guru/sfg/brewery/domain/security/User.java
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

    //ved ikke, om de virker i deres egen tabel
    @Column(table = "login_info")
    private String username;
    @Column(table = "login_info")
    private String password;

    //kun en rolle i dette system
    private Role role;

    @NotBlank(message = "Indtast venligst fornavn")
    private String firstName;

    @NotBlank(message = "Indtast venligst efternavn")
    private String lastName;

    @Column(table ="contact_info")
    private String emailAddress;

    @Column(table ="contact_info")
    private String phoneNumber;


    /**
     * mappedBy = "user" attribute specifies that the field "private User user;" in class Deck,
     * owns the relationship (is the owner), and therefore contains the foreign key to query findAll
     * decks for an user.
     * https://en.wikibooks.org/wiki/Java_Persistence/OneToMany
     */
    @OneToMany(cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY,
            mappedBy = "user")
    private List<Deck> listOfDecks;


    /**
     * For adding an entity from the relationship, we need to update both sides of the association.
     * Instead of doing it in the Service, it can be done with af utility method that updates both entities.
     *
     * https://thorben-janssen.com/best-practices-many-one-one-many-associations-mappings/
     * https://en.wikibooks.org/wiki/Java_Persistence/OneToMany
     * https://medium.com/@rajibrath20/the-best-way-to-map-a-onetomany-relationship-with-jpa-and-hibernate-dbbf6dba00d3
     * @param deck
     */
    public void addDeck(Deck deck)
    {
        this.listOfDecks.add(deck);
        if (deck.getUser() != this)
        {
            deck.setUser(this);
        }
    }

    /**
     * For removing an entity from the relationship, we need to update both sides of the association.
     * Instead of doing it in the Service, it can be done with af utility method that updates both entities.
     *
     * https://thorben-janssen.com/best-practices-many-one-one-many-associations-mappings/
     * https://en.wikibooks.org/wiki/Java_Persistence/OneToMany
     * https://medium.com/@rajibrath20/the-best-way-to-map-a-onetomany-relationship-with-jpa-and-hibernate-dbbf6dba00d3
     * @param deck
     */
    public void removeDeck(Deck deck)
    {
        this.listOfDecks.remove(deck);
        deck.setUser(null);
    }
}
