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
public class FilterPlaceServiceTest {

    MeetingApiService service;

    @Before
    public void setup() {
        service = DI.getNewInstanceMeetingApiService();
        service.addNewMeeting(new Meeting("Mario", "Mario", LocalDateTime.now(), 1, new ArrayList<>()));
        service.addNewMeeting(new Meeting("Luigi", "Luigi", LocalDateTime.now(), 2, new ArrayList<>()));
        service.addNewMeeting(new Meeting("Peach", "Peach", LocalDateTime.now(), 3, new ArrayList<>()));
        service.addNewMeeting(new Meeting("Bowser", "Bowser", LocalDateTime.now(), 4, new ArrayList<>()));
        service.addNewMeeting(new Meeting("Wario", "Wario", LocalDateTime.now(), 5, new ArrayList<>()));
        service.addNewMeeting(new Meeting("Waluigi", "Waluigi", LocalDateTime.now(), 6, new ArrayList<>()));
        service.addNewMeeting(new Meeting("Daisy", "Daisy", LocalDateTime.now(), 7, new ArrayList<>()));
        service.addNewMeeting(new Meeting("Donkey Kong", "Donkey Kong", LocalDateTime.now(), 8, new ArrayList<>()));
        service.addNewMeeting(new Meeting("Yoshi", "Yoshi", LocalDateTime.now(), 9, new ArrayList<>()));
        service.addNewMeeting(new Meeting("Toad", "Toad", LocalDateTime.now(), 10, new ArrayList<>()));
    }

    private List<Meeting> placeFilter(List<Meeting> meetingList, String place) {
        List<Meeting> toRemoveList = new ArrayList<>();
        for (Meeting meeting : meetingList) {
            if (!meeting.getMeetingPlace().equals(place)) {
                toRemoveList.add(meeting);
            }
        }
        meetingList.removeAll(toRemoveList);
        return meetingList;
    }

    @Test
    public void filterMarioWithSuccess(){
        List<Meeting> filteredMeetingList = new ArrayList<>(service.getMeetingList());
        List<Meeting> expectedRemovedList = new ArrayList<>();
        for (Meeting meeting:service.getMeetingList()) {
            if (!meeting.getMeetingPlace().equals("Mario")){
                expectedRemovedList.add(meeting);
            }
        }
        filteredMeetingList = placeFilter(filteredMeetingList, "Mario");
        assertTrue(filteredMeetingList.contains(service.getMeetingList().get(0)));
        assertFalse(filteredMeetingList.containsAll(expectedRemovedList));
    }

    @Test
    public void filterLuigiWithSuccess(){
        List<Meeting> filteredMeetingList = new ArrayList<>(service.getMeetingList());
        List<Meeting> expectedRemovedList = new ArrayList<>();
        for (Meeting meeting:service.getMeetingList()) {
            if (!meeting.getMeetingPlace().equals("Luigi")){
                expectedRemovedList.add(meeting);
            }
        }
        filteredMeetingList = placeFilter(filteredMeetingList, "Luigi");
        assertTrue(filteredMeetingList.contains(service.getMeetingList().get(1)));
        assertFalse(filteredMeetingList.containsAll(expectedRemovedList));
    }

    @Test
    public void filterPeachWithSuccess(){
        List<Meeting> filteredMeetingList = new ArrayList<>(service.getMeetingList());
        List<Meeting> expectedRemovedList = new ArrayList<>();
        for (Meeting meeting:service.getMeetingList()) {
            if (!meeting.getMeetingPlace().equals("Peach")){
                expectedRemovedList.add(meeting);
            }
        }
        filteredMeetingList = placeFilter(filteredMeetingList, "Peach");
        assertTrue(filteredMeetingList.contains(service.getMeetingList().get(2)));
        assertFalse(filteredMeetingList.containsAll(expectedRemovedList));
    }

    @Test
    public void filterBowserWithSuccess(){
        List<Meeting> filteredMeetingList = new ArrayList<>(service.getMeetingList());
        List<Meeting> expectedRemovedList = new ArrayList<>();
        for (Meeting meeting:service.getMeetingList()) {
            if (!meeting.getMeetingPlace().equals("Bowser")){
                expectedRemovedList.add(meeting);
            }
        }
        filteredMeetingList = placeFilter(filteredMeetingList, "Bowser");
        assertTrue(filteredMeetingList.contains(service.getMeetingList().get(3)));
        assertFalse(filteredMeetingList.containsAll(expectedRemovedList));
    }

    @Test
    public void filterWarioWithSuccess(){
        List<Meeting> filteredMeetingList = new ArrayList<>(service.getMeetingList());
        List<Meeting> expectedRemovedList = new ArrayList<>();
        for (Meeting meeting:service.getMeetingList()) {
            if (!meeting.getMeetingPlace().equals("Wario")){
                expectedRemovedList.add(meeting);
            }
        }
        filteredMeetingList = placeFilter(filteredMeetingList, "Wario");
        assertTrue(filteredMeetingList.contains(service.getMeetingList().get(4)));
        assertFalse(filteredMeetingList.containsAll(expectedRemovedList));
    }

    @Test
    public void filterWaluigiWithSuccess(){
        List<Meeting> filteredMeetingList = new ArrayList<>(service.getMeetingList());
        List<Meeting> expectedRemovedList = new ArrayList<>();
        for (Meeting meeting:service.getMeetingList()) {
            if (!meeting.getMeetingPlace().equals("Waluigi")){
                expectedRemovedList.add(meeting);
            }
        }
        filteredMeetingList = placeFilter(filteredMeetingList, "Waluigi");
        assertTrue(filteredMeetingList.contains(service.getMeetingList().get(5)));
        assertFalse(filteredMeetingList.containsAll(expectedRemovedList));
    }

    @Test
    public void filterDaisyWithSuccess(){
        List<Meeting> filteredMeetingList = new ArrayList<>(service.getMeetingList());
        List<Meeting> expectedRemovedList = new ArrayList<>();
        for (Meeting meeting:service.getMeetingList()) {
            if (!meeting.getMeetingPlace().equals("Daisy")){
                expectedRemovedList.add(meeting);
            }
        }
        filteredMeetingList = placeFilter(filteredMeetingList, "Daisy");
        assertTrue(filteredMeetingList.contains(service.getMeetingList().get(6)));
        assertFalse(filteredMeetingList.containsAll(expectedRemovedList));
    }

    @Test
    public void filterDonkeyKongWithSuccess(){
        List<Meeting> filteredMeetingList = new ArrayList<>(service.getMeetingList());
        List<Meeting> expectedRemovedList = new ArrayList<>();
        for (Meeting meeting:service.getMeetingList()) {
            if (!meeting.getMeetingPlace().equals("Donkey Kong")){
                expectedRemovedList.add(meeting);
            }
        }
        filteredMeetingList = placeFilter(filteredMeetingList, "Donkey Kong");
        assertTrue(filteredMeetingList.contains(service.getMeetingList().get(7)));
        assertFalse(filteredMeetingList.containsAll(expectedRemovedList));
    }

    @Test
    public void filterYoshiWithSuccess(){
        List<Meeting> filteredMeetingList = new ArrayList<>(service.getMeetingList());
        List<Meeting> expectedRemovedList = new ArrayList<>();
        for (Meeting meeting:service.getMeetingList()) {
            if (!meeting.getMeetingPlace().equals("Yoshi")){
                expectedRemovedList.add(meeting);
            }
        }
        filteredMeetingList = placeFilter(filteredMeetingList, "Yoshi");
        assertTrue(filteredMeetingList.contains(service.getMeetingList().get(8)));
        assertFalse(filteredMeetingList.containsAll(expectedRemovedList));
    }

    @Test
    public void filterToadWithSuccess(){
        List<Meeting> filteredMeetingList = new ArrayList<>(service.getMeetingList());
        List<Meeting> expectedRemovedList = new ArrayList<>();
        for (Meeting meeting:service.getMeetingList()) {
            if (!meeting.getMeetingPlace().equals("Toad")){
                expectedRemovedList.add(meeting);
            }
        }
        filteredMeetingList = placeFilter(filteredMeetingList, "Toad");
        assertTrue(filteredMeetingList.contains(service.getMeetingList().get(9)));
        assertFalse(filteredMeetingList.containsAll(expectedRemovedList));
    }
}
