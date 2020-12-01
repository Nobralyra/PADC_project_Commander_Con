package com.padc.demo.deck.domain;

public enum GameType
{
    EDH("EDH"),
    CEDH("cEDH"),
    ANDET("Andet");

    private final String displayValue;

    GameType(String displayValue)
    {
        this.displayValue = displayValue;
    }

    public String getDisplayValue()
    {
        return displayValue;
    }
}
