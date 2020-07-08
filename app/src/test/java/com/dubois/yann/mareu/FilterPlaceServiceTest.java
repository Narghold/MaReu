package com.dubois.yann.mareu;

import com.dubois.yann.mareu.model.Meeting;
import com.dubois.yann.mareu.service.DI;
import com.dubois.yann.mareu.service.FilterApiService;
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
public class FilterPlaceServiceTest {

    MeetingApiService meetingService;
    FilterApiService filterService;

    @Before
    public void setup() {
        meetingService = DI.getMeetingApiService();
        meetingService.addNewMeeting(new Meeting("Mario", "Mario", LocalDateTime.now(), 1, new ArrayList<>()));
        meetingService.addNewMeeting(new Meeting("Luigi", "Luigi", LocalDateTime.now(), 2, new ArrayList<>()));
        meetingService.addNewMeeting(new Meeting("Peach", "Peach", LocalDateTime.now(), 3, new ArrayList<>()));
        meetingService.addNewMeeting(new Meeting("Bowser", "Bowser", LocalDateTime.now(), 4, new ArrayList<>()));
        meetingService.addNewMeeting(new Meeting("Wario", "Wario", LocalDateTime.now(), 5, new ArrayList<>()));
        meetingService.addNewMeeting(new Meeting("Waluigi", "Waluigi", LocalDateTime.now(), 6, new ArrayList<>()));
        meetingService.addNewMeeting(new Meeting("Daisy", "Daisy", LocalDateTime.now(), 7, new ArrayList<>()));
        meetingService.addNewMeeting(new Meeting("Donkey Kong", "Donkey Kong", LocalDateTime.now(), 8, new ArrayList<>()));
        meetingService.addNewMeeting(new Meeting("Yoshi", "Yoshi", LocalDateTime.now(), 9, new ArrayList<>()));
        meetingService.addNewMeeting(new Meeting("Toad", "Toad", LocalDateTime.now(), 10, new ArrayList<>()));

        filterService = DI.getFilterApiService();
    }

    @Test
    public void filterMarioWithSuccess(){
        List<Meeting> filteredMeetingList = new ArrayList<>(meetingService.getMeetingList());
        List<Meeting> expectedRemovedList = new ArrayList<>();
        for (Meeting meeting: meetingService.getMeetingList()) {
            if (!meeting.getMeetingPlace().equals("Mario")){
                expectedRemovedList.add(meeting);
            }
        }
        filteredMeetingList = filterService.placeFilter(filteredMeetingList, "Mario");
        assertTrue(filteredMeetingList.contains(meetingService.getMeetingList().get(0)));
        assertFalse(filteredMeetingList.containsAll(expectedRemovedList));
    }

    @Test
    public void filterLuigiWithSuccess(){
        List<Meeting> filteredMeetingList = new ArrayList<>(meetingService.getMeetingList());
        List<Meeting> expectedRemovedList = new ArrayList<>();
        for (Meeting meeting: meetingService.getMeetingList()) {
            if (!meeting.getMeetingPlace().equals("Luigi")){
                expectedRemovedList.add(meeting);
            }
        }
        filteredMeetingList = filterService.placeFilter(filteredMeetingList, "Luigi");
        assertTrue(filteredMeetingList.contains(meetingService.getMeetingList().get(1)));
        assertFalse(filteredMeetingList.containsAll(expectedRemovedList));
    }

    @Test
    public void filterPeachWithSuccess(){
        List<Meeting> filteredMeetingList = new ArrayList<>(meetingService.getMeetingList());
        List<Meeting> expectedRemovedList = new ArrayList<>();
        for (Meeting meeting: meetingService.getMeetingList()) {
            if (!meeting.getMeetingPlace().equals("Peach")){
                expectedRemovedList.add(meeting);
            }
        }
        filteredMeetingList = filterService.placeFilter(filteredMeetingList, "Peach");
        assertTrue(filteredMeetingList.contains(meetingService.getMeetingList().get(2)));
        assertFalse(filteredMeetingList.containsAll(expectedRemovedList));
    }

    @Test
    public void filterBowserWithSuccess(){
        List<Meeting> filteredMeetingList = new ArrayList<>(meetingService.getMeetingList());
        List<Meeting> expectedRemovedList = new ArrayList<>();
        for (Meeting meeting: meetingService.getMeetingList()) {
            if (!meeting.getMeetingPlace().equals("Bowser")){
                expectedRemovedList.add(meeting);
            }
        }
        filteredMeetingList = filterService.placeFilter(filteredMeetingList, "Bowser");
        assertTrue(filteredMeetingList.contains(meetingService.getMeetingList().get(3)));
        assertFalse(filteredMeetingList.containsAll(expectedRemovedList));
    }

    @Test
    public void filterWarioWithSuccess(){
        List<Meeting> filteredMeetingList = new ArrayList<>(meetingService.getMeetingList());
        List<Meeting> expectedRemovedList = new ArrayList<>();
        for (Meeting meeting: meetingService.getMeetingList()) {
            if (!meeting.getMeetingPlace().equals("Wario")){
                expectedRemovedList.add(meeting);
            }
        }
        filteredMeetingList = filterService.placeFilter(filteredMeetingList, "Wario");
        assertTrue(filteredMeetingList.contains(meetingService.getMeetingList().get(4)));
        assertFalse(filteredMeetingList.containsAll(expectedRemovedList));
    }

    @Test
    public void filterWaluigiWithSuccess(){
        List<Meeting> filteredMeetingList = new ArrayList<>(meetingService.getMeetingList());
        List<Meeting> expectedRemovedList = new ArrayList<>();
        for (Meeting meeting: meetingService.getMeetingList()) {
            if (!meeting.getMeetingPlace().equals("Waluigi")){
                expectedRemovedList.add(meeting);
            }
        }
        filteredMeetingList = filterService.placeFilter(filteredMeetingList, "Waluigi");
        assertTrue(filteredMeetingList.contains(meetingService.getMeetingList().get(5)));
        assertFalse(filteredMeetingList.containsAll(expectedRemovedList));
    }

    @Test
    public void filterDaisyWithSuccess(){
        List<Meeting> filteredMeetingList = new ArrayList<>(meetingService.getMeetingList());
        List<Meeting> expectedRemovedList = new ArrayList<>();
        for (Meeting meeting: meetingService.getMeetingList()) {
            if (!meeting.getMeetingPlace().equals("Daisy")){
                expectedRemovedList.add(meeting);
            }
        }
        filteredMeetingList = filterService.placeFilter(filteredMeetingList, "Daisy");
        assertTrue(filteredMeetingList.contains(meetingService.getMeetingList().get(6)));
        assertFalse(filteredMeetingList.containsAll(expectedRemovedList));
    }

    @Test
    public void filterDonkeyKongWithSuccess(){
        List<Meeting> filteredMeetingList = new ArrayList<>(meetingService.getMeetingList());
        List<Meeting> expectedRemovedList = new ArrayList<>();
        for (Meeting meeting: meetingService.getMeetingList()) {
            if (!meeting.getMeetingPlace().equals("Donkey Kong")){
                expectedRemovedList.add(meeting);
            }
        }
        filteredMeetingList = filterService.placeFilter(filteredMeetingList, "Donkey Kong");
        assertTrue(filteredMeetingList.contains(meetingService.getMeetingList().get(7)));
        assertFalse(filteredMeetingList.containsAll(expectedRemovedList));
    }

    @Test
    public void filterYoshiWithSuccess(){
        List<Meeting> filteredMeetingList = new ArrayList<>(meetingService.getMeetingList());
        List<Meeting> expectedRemovedList = new ArrayList<>();
        for (Meeting meeting: meetingService.getMeetingList()) {
            if (!meeting.getMeetingPlace().equals("Yoshi")){
                expectedRemovedList.add(meeting);
            }
        }
        filteredMeetingList = filterService.placeFilter(filteredMeetingList, "Yoshi");
        assertTrue(filteredMeetingList.contains(meetingService.getMeetingList().get(8)));
        assertFalse(filteredMeetingList.containsAll(expectedRemovedList));
    }

    @Test
    public void filterToadWithSuccess(){
        List<Meeting> filteredMeetingList = new ArrayList<>(meetingService.getMeetingList());
        List<Meeting> expectedRemovedList = new ArrayList<>();
        for (Meeting meeting: meetingService.getMeetingList()) {
            if (!meeting.getMeetingPlace().equals("Toad")){
                expectedRemovedList.add(meeting);
            }
        }
        filteredMeetingList = filterService.placeFilter(filteredMeetingList, "Toad");
        assertTrue(filteredMeetingList.contains(meetingService.getMeetingList().get(9)));
        assertFalse(filteredMeetingList.containsAll(expectedRemovedList));
    }
}
