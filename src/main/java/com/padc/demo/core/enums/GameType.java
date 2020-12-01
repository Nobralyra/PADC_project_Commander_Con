package com.padc.demo.core.enums;

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
