package com.dubois.yann.mareu.event;

import java.util.List;

public class AddMeetingEvent {

    private List<String> participantList;

    public AddMeetingEvent(List<String> participantList) {
        this.participantList = participantList;
    }

    public List<String> getParticipantList() {
        return participantList;
    }
}
