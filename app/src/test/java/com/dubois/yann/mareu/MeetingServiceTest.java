package com.dubois.yann.mareu;

import com.dubois.yann.mareu.model.Meeting;
import com.dubois.yann.mareu.service.DI;
import com.dubois.yann.mareu.service.MeetingApiService;

import org.joda.time.LocalDateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(JUnit4.class)
public class MeetingServiceTest {

    MeetingApiService service;

    @Before
    public void setup(){
        service = DI.getMeetingApiService();
    }

    @Test
    public void serviceListShouldBeEmptyAtStart(){
        List<Meeting> meetingList = service.getMeetingList();
        assertTrue(meetingList.isEmpty());
    }

    @Test
    public void addMeetingWithSuccess(){
        Meeting meetingToAdd = new Meeting("Title","Mario", LocalDateTime.now(),1, new ArrayList<>());
        service.addNewMeeting(meetingToAdd);
        assertTrue(service.getMeetingList().contains(meetingToAdd));
    }

    @Test
    public void deleteMeetingWithSuccess(){
        Meeting meetingToDelete = new Meeting("Title","Mario", LocalDateTime.now(),1, new ArrayList<>());
        addMeetingWithSuccess();
        service.deleteMeeting(meetingToDelete);
        assertFalse(service.getMeetingList().contains(meetingToDelete));
    }
}
