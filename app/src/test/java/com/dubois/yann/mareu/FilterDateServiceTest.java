package com.dubois.yann.mareu;

import com.dubois.yann.mareu.model.Meeting;
import com.dubois.yann.mareu.service.DI;
import com.dubois.yann.mareu.service.MeetingApiService;

import org.joda.time.Interval;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(JUnit4.class)
public class FilterDateServiceTest {

    MeetingApiService service;

    @Before
    public void setup() {
        service = DI.getNewInstanceMeetingApiService();
        service.addNewMeeting(new Meeting("Today", "Mario", LocalDateTime.now(), 1, new ArrayList<>()));
        service.addNewMeeting(new Meeting("Tomorrow", "Mario", LocalDateTime.now().plusDays(1), 2, new ArrayList<>()));
        service.addNewMeeting(new Meeting("Next Week", "Mario", LocalDateTime.now().plusDays(8), 3, new ArrayList<>()));
        service.addNewMeeting(new Meeting("Next Month", "Mario", LocalDateTime.now().plusMonths(1).plusDays(1), 4, new ArrayList<>()));
    }

    private List<Meeting> dateFilter(List<Meeting> meetingList, String date) {
        LocalDate dateFilter;
        Iterator<Meeting> iterator = meetingList.iterator();
        while(iterator.hasNext()){
            LocalDate meetingDate = iterator.next().getDateTime().toLocalDate();
            if (date.equals("Aujourd'hui")){
                dateFilter = LocalDate.now();
                if (!meetingDate.toDateTimeAtStartOfDay().equals(dateFilter.toDateTimeAtStartOfDay())) {
                    iterator.remove();
                }
            }else if (date.equals("Demain")){
                dateFilter = LocalDate.now().plusDays(1);
                if (!meetingDate.toDateTimeAtStartOfDay().equals(dateFilter.toDateTimeAtStartOfDay())) {
                    iterator.remove();
                }
            }else if (date.equals("Cette semaine")){
                dateFilter = LocalDate.now().plusWeeks(1);
                LocalDate dateNow = LocalDate.now();
                Interval week = new Interval(dateNow.toDateTimeAtStartOfDay(), dateFilter.toDateTimeAtStartOfDay());
                if (!week.contains(meetingDate.toDateTimeAtStartOfDay())) {
                    iterator.remove();
                }
            }else if (date.equals("La semaine prochaine")) {
                LocalDate dateAfter = LocalDate.now().plusWeeks(2);
                LocalDate dateBefore = LocalDate.now().plusWeeks(1);
                Interval secondWeek = new Interval(dateBefore.toDateTimeAtStartOfDay(), dateAfter.toDateTimeAtStartOfDay());
                if (!secondWeek.contains(meetingDate.toDateTimeAtStartOfDay())) {
                    iterator.remove();
                }
            }else if (date.equals("Ce mois-ci")) {
                LocalDate dateNow = LocalDate.now();
                dateFilter = LocalDate.now().plusMonths(1);
                Interval month = new Interval(dateNow.toDateTimeAtStartOfDay(), dateFilter.toDateTimeAtStartOfDay());
                if (!month.contains(meetingDate.toDateTimeAtStartOfDay())) {
                    iterator.remove();
                }
            }
        }
        return meetingList;
    }

    @Test
    public void filterTodayWithSuccess(){
        List<Meeting> filteredMeetingList = new ArrayList<>(service.getMeetingList());
        List<Meeting> expectedFilteredList = new ArrayList<>();
        expectedFilteredList.add(service.getMeetingList().get(0));
        filteredMeetingList = dateFilter(filteredMeetingList, "Aujourd'hui");
        assertTrue(filteredMeetingList.containsAll(expectedFilteredList));
        assertFalse(filteredMeetingList.contains(service.getMeetingList().get(1)));
        assertFalse(filteredMeetingList.contains(service.getMeetingList().get(2)));
        assertFalse(filteredMeetingList.contains(service.getMeetingList().get(3)));
    }

    @Test
    public void filterTomorrowWithSuccess(){
        List<Meeting> filteredMeetingList = new ArrayList<>(service.getMeetingList());
        List<Meeting> expectedFilteredList = new ArrayList<>();
        expectedFilteredList.add(service.getMeetingList().get(1));
        filteredMeetingList = dateFilter(filteredMeetingList, "Demain");
        assertTrue(filteredMeetingList.containsAll(expectedFilteredList));
        assertFalse(filteredMeetingList.contains(service.getMeetingList().get(0)));
        assertFalse(filteredMeetingList.contains(service.getMeetingList().get(2)));
        assertFalse(filteredMeetingList.contains(service.getMeetingList().get(3)));
    }

    @Test
    public void filterThisWeekWithSuccess(){
        List<Meeting> filteredMeetingList = new ArrayList<>(service.getMeetingList());
        List<Meeting> expectedFilteredList = new ArrayList<>();
        expectedFilteredList.add(service.getMeetingList().get(0));
        expectedFilteredList.add(service.getMeetingList().get(1));
        filteredMeetingList = dateFilter(filteredMeetingList, "Cette semaine");
        assertTrue(filteredMeetingList.containsAll(expectedFilteredList));
        assertFalse(filteredMeetingList.contains(service.getMeetingList().get(2)));
        assertFalse(filteredMeetingList.contains(service.getMeetingList().get(3)));
    }

    @Test
    public void filterNextWeekWithSuccess(){
        List<Meeting> filteredMeetingList = new ArrayList<>(service.getMeetingList());
        List<Meeting> expectedFilteredList = new ArrayList<>();
        expectedFilteredList.add(service.getMeetingList().get(2));
        filteredMeetingList = dateFilter(filteredMeetingList, "La semaine prochaine");
        assertTrue(filteredMeetingList.containsAll(expectedFilteredList));
        assertFalse(filteredMeetingList.contains(service.getMeetingList().get(0)));
        assertFalse(filteredMeetingList.contains(service.getMeetingList().get(1)));
        assertFalse(filteredMeetingList.contains(service.getMeetingList().get(3)));
    }

    @Test
    public void filterThisMonthWithSuccess(){
        List<Meeting> filteredMeetingList = new ArrayList<>(service.getMeetingList());
        List<Meeting> expectedFilteredList = new ArrayList<>();
        expectedFilteredList.add(service.getMeetingList().get(0));
        expectedFilteredList.add(service.getMeetingList().get(1));
        expectedFilteredList.add(service.getMeetingList().get(2));
        filteredMeetingList = dateFilter(filteredMeetingList, "Ce mois-ci");
        assertTrue(filteredMeetingList.containsAll(expectedFilteredList));
        assertFalse(filteredMeetingList.contains(service.getMeetingList().get(3)));
    }

}
