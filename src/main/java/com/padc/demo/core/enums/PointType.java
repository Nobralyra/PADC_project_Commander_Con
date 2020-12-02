package com.padc.demo.core.enums;

public enum PointType
{
    ELIMINERING("Eliminering"),
    POINT("Point"),
    MILESTONE("Milestone"),
    ANDET("Andet");

    private final String displayValue;

    PointType(String displayValue)
    {
        this.displayValue = displayValue;
    }

    public String getDisplayValue()
    {
        return displayValue;
    }
}
