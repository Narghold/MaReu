package com.dubois.yann.mareu.model;

import org.joda.time.LocalDateTime;

import java.util.List;

public class Meeting {

    private String mMeetingTitle;
    private String mMeetingPlace;
    private LocalDateTime mDateTime;
    private int mColor;
    private List<String> mMeetingParticipants;

    public Meeting(String mMeetingTitle, String mMeetingPlace, LocalDateTime mDate, int mColor, List<String> mMeetingParticipants) {
        this.mMeetingTitle = mMeetingTitle;
        this.mMeetingPlace = mMeetingPlace;
        this.mDateTime = mDate;
        this.mColor = mColor;
        this.mMeetingParticipants = mMeetingParticipants;
    }

    public String getMeetingTitle() {
        return mMeetingTitle;
    }

    public void setMeetingTitle(String mMeetingTitle) {
        this.mMeetingTitle = mMeetingTitle;
    }

    public String getMeetingPlace() {
        return mMeetingPlace;
    }

    public void setMeetingPlace(String mMeetingPlace) { this.mMeetingPlace = mMeetingPlace; }

    public List<String> getMeetingParticipants() {
        return mMeetingParticipants;
    }

    public void setMeetingParticipants(List<String> mMeetingParticipants) { this.mMeetingParticipants = mMeetingParticipants; }

    public int getColor() { return mColor; }

    public void setColor(int mColor) { this.mColor = mColor; }

    public LocalDateTime getDateTime() {
        return mDateTime;
    }

    public void setDateTime(LocalDateTime mDateTime) {
        this.mDateTime = mDateTime;
    }
}
