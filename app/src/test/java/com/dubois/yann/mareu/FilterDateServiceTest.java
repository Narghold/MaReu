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
public class FilterDateServiceTest {

    MeetingApiService meetingService;
    FilterApiService filterService;

    @Before
    public void setup() {
        meetingService = DI.getMeetingApiService();
        meetingService.addNewMeeting(new Meeting("Today", "Mario", LocalDateTime.now(), 1, new ArrayList<>()));
        meetingService.addNewMeeting(new Meeting("Tomorrow", "Mario", LocalDateTime.now().plusDays(1), 2, new ArrayList<>()));
        meetingService.addNewMeeting(new Meeting("Next Week", "Mario", LocalDateTime.now().plusDays(8), 3, new ArrayList<>()));
        meetingService.addNewMeeting(new Meeting("Next Month", "Mario", LocalDateTime.now().plusMonths(1).plusDays(1), 4, new ArrayList<>()));

        filterService = DI.getFilterApiService();
    }


    @Test
    public void filterTodayWithSuccess(){
        List<Meeting> filteredMeetingList = new ArrayList<>(meetingService.getMeetingList());
        List<Meeting> expectedFilteredList = new ArrayList<>();
        expectedFilteredList.add(meetingService.getMeetingList().get(0));
        filteredMeetingList = filterService.dateFilter(filteredMeetingList, "Aujourd'hui");
        assertTrue(filteredMeetingList.containsAll(expectedFilteredList));
        assertFalse(filteredMeetingList.contains(meetingService.getMeetingList().get(1)));
        assertFalse(filteredMeetingList.contains(meetingService.getMeetingList().get(2)));
        assertFalse(filteredMeetingList.contains(meetingService.getMeetingList().get(3)));
    }

    @Test
    public void filterTomorrowWithSuccess(){
        List<Meeting> filteredMeetingList = new ArrayList<>(meetingService.getMeetingList());
        List<Meeting> expectedFilteredList = new ArrayList<>();
        expectedFilteredList.add(meetingService.getMeetingList().get(1));
        filteredMeetingList = filterService.dateFilter(filteredMeetingList, "Demain");
        assertTrue(filteredMeetingList.containsAll(expectedFilteredList));
        assertFalse(filteredMeetingList.contains(meetingService.getMeetingList().get(0)));
        assertFalse(filteredMeetingList.contains(meetingService.getMeetingList().get(2)));
        assertFalse(filteredMeetingList.contains(meetingService.getMeetingList().get(3)));
    }

    @Test
    public void filterThisWeekWithSuccess(){
        List<Meeting> filteredMeetingList = new ArrayList<>(meetingService.getMeetingList());
        List<Meeting> expectedFilteredList = new ArrayList<>();
        expectedFilteredList.add(meetingService.getMeetingList().get(0));
        expectedFilteredList.add(meetingService.getMeetingList().get(1));
        filteredMeetingList = filterService.dateFilter(filteredMeetingList, "Cette semaine");
        assertTrue(filteredMeetingList.containsAll(expectedFilteredList));
        assertFalse(filteredMeetingList.contains(meetingService.getMeetingList().get(2)));
        assertFalse(filteredMeetingList.contains(meetingService.getMeetingList().get(3)));
    }

    @Test
    public void filterNextWeekWithSuccess(){
        List<Meeting> filteredMeetingList = new ArrayList<>(meetingService.getMeetingList());
        List<Meeting> expectedFilteredList = new ArrayList<>();
        expectedFilteredList.add(meetingService.getMeetingList().get(2));
        filteredMeetingList = filterService.dateFilter(filteredMeetingList, "La semaine prochaine");
        assertTrue(filteredMeetingList.containsAll(expectedFilteredList));
        assertFalse(filteredMeetingList.contains(meetingService.getMeetingList().get(0)));
        assertFalse(filteredMeetingList.contains(meetingService.getMeetingList().get(1)));
        assertFalse(filteredMeetingList.contains(meetingService.getMeetingList().get(3)));
    }

    @Test
    public void filterThisMonthWithSuccess(){
        List<Meeting> filteredMeetingList = new ArrayList<>(meetingService.getMeetingList());
        List<Meeting> expectedFilteredList = new ArrayList<>();
        expectedFilteredList.add(meetingService.getMeetingList().get(0));
        expectedFilteredList.add(meetingService.getMeetingList().get(1));
        expectedFilteredList.add(meetingService.getMeetingList().get(2));
        filteredMeetingList = filterService.dateFilter(filteredMeetingList, "Ce mois-ci");
        assertTrue(filteredMeetingList.containsAll(expectedFilteredList));
        assertFalse(filteredMeetingList.contains(meetingService.getMeetingList().get(3)));
    }

}
