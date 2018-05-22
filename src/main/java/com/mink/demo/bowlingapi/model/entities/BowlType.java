package com.mink.demo.bowlingapi.model.entities;

public enum BowlType {
    STRIKE("X"), SPARE("/"), OPEN("-"), IN_PROGRESS(" ");

    private String displayValue;
    
    BowlType(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }

}
