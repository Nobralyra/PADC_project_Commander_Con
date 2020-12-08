package com.padc.demo.core.enums;

public enum PointType
{
    /**
     * Enum with string values (displayValue)
     */
    ELIMINERING("Eliminering"),
    POINT("Point"),
    MILESTONE("Milestone"),
    ANDET("Andet");

    /**
     * Field of string value (displayValue)
     */
    private final String displayValue;

    PointType(String displayValue)
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
