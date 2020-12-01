package com.padc.demo.deck.domain;

import com.padc.demo.core.auditing.Audition;
import com.padc.demo.core.enums.GameType;

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
    private long deckId;

    @NotBlank(message = "Skal udfyldes")
    private String commanderName;

    private String nameOfDeck;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "enum('EDH','CEDH', 'ANDET')")
    @NotNull(message = "Skal udfyldes")
    private GameType gameType;

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
}
