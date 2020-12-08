package com.padc.demo.deck.domain;

import com.padc.demo.core.auditing.Audition;
import com.padc.demo.core.enums.GameType;
import com.padc.demo.user.domain.User;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="deck")
@SecondaryTable(name="partner_commander")
public class Deck extends Audition
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long deckId;

    @NotBlank(message = "Skal udfyldes")
    private String commanderName;

    private String nameOfDeck;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "enum('EDH','CEDH', 'ANDET')")
    @NotNull(message = "Skal udfyldes")
    private GameType gameType;

    @Column(table ="partner_commander")
    private String partnerCommanderName;

    /**
     * Child (owner)
     * Specifies that the Deck table does not have an user column, but
     * instead an id column with a foreign key. It also creates a join to
     * lazy fetch user.
     * https://en.wikibooks.org/wiki/Java_Persistence/ManyToOne
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id")
    private User user;

    public Deck()
    {
    }

    public Long getDeckId()
    {
        return deckId;
    }

    public void setDeckId(Long deckId)
    {
        this.deckId = deckId;
    }

    public String getNameOfDeck()
    {
        return nameOfDeck;
    }

    public void setNameOfDeck(String nameOfDeck)
    {
        this.nameOfDeck = nameOfDeck;
    }

    public String getCommanderName()
    {
        return commanderName;
    }

    public void setCommanderName(String commanderName)
    {
        this.commanderName = commanderName;
    }

    public GameType getGameType()
    {
        return gameType;
    }

    public void setGameType(GameType gameType)
    {
        this.gameType = gameType;
    }

    public String getPartnerCommanderName()
    {
        return partnerCommanderName;
    }

    public void setPartnerCommanderName(String partnerCommanderName)
    {
        this.partnerCommanderName = partnerCommanderName;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    /**
     * Can't rely on a natural identifier for equality checks, so instead we use the entity identifier.
     * The equality has to be consistent across all entity state transitions.
     *
     * To understand why to use equals and hashCode:
     * https://medium.com/@rajibrath20/the-best-way-to-map-a-onetomany-relationship-with-jpa-and-hibernate-dbbf6dba00d3
     * https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
     * https://vladmihalcea.com/the-best-way-to-map-a-onetomany-association-with-jpa-and-hibernate/
     * https://stackoverflow.com/questions/9560522/hibernate-how-to-properly-organize-relation-a-one-to-many-with-annotations
     *
     * @param object - the object to be compared
     * @return boolean
     */
    @Override
    public boolean equals(Object object)
    {
        if (this == object)
        {
            return true;
        }

        if (!(object instanceof Deck))
        {
            return false;
        }

        return deckId != null && deckId.equals(((Deck)object).getDeckId());
    }

    @Override
    public int hashCode()
    {
        return getClass().hashCode();
    }
}
