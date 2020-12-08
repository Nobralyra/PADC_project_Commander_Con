package com.padc.demo.core.enums;

public enum GameType
{
    /**
     * Enum with string values (displayValue)
     */
    EDH("EDH"),
    CEDH("cEDH"),
    ANDET("Andet");

    /**
     * Field of string value (displayValue)
     */
    private final String displayValue;

    GameType(String displayValue)
    {
        this.displayValue = displayValue;
    }

    /**
     * Get the enum value
     * @return String - displayValue
     */
    public String getDisplayValue()
    {
        return displayValue;
    }
}
