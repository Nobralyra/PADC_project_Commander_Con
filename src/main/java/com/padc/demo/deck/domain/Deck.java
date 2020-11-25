package com.padc.demo.deck.domain;

import com.padc.demo.core.auditing.Audition;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="deck")
@SecondaryTable(name="partner_commander")
public class Deck extends Audition
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long deckId;

    @NotBlank(message = "Skal udfyldes")
    private String commanderName;

    private String nameOfDeck;

    @NotBlank(message = "Skal udfyldes")
    private String deckType;

    @Column(table ="partner_commander")
    private String partnerCommanderName;

    public Deck()
    {
    }

    public long getDeckId()
    {
        return deckId;
    }

    public void setDeckId(long deckId)
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

    public String getDeckType()
    {
        return deckType;
    }

    public void setDeckType(String deckType)
    {
        this.deckType = deckType;
    }

    public String getPartnerCommanderName()
    {
        return partnerCommanderName;
    }

    public void setPartnerCommanderName(String partnerCommanderName)
    {
        this.partnerCommanderName = partnerCommanderName;
    }
}
