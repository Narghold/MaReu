package com.dubois.yann.mareu.event;

public class FilterEvent {

    private String dateChoice;
    private String placeChoice;

    public FilterEvent(String dateChoice, String placeChoice) {
        this.dateChoice = dateChoice;
        this.placeChoice = placeChoice;
    }

    public String getDateChoice() {
        return dateChoice;
    }

    public String getPlaceChoice() {
        return placeChoice;
    }
}
