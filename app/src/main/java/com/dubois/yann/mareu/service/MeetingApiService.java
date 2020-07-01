package com.dubois.yann.mareu.service;

import com.dubois.yann.mareu.model.Meeting;

import java.util.List;

/**
 *  Meeting API Client
 */
public interface MeetingApiService {


    /**
     *  Get all my meeting
     *  @return {@link List}
     */
    List<Meeting> getMeetingList();

    /**
     *  Add a new meeting to my list
     *  @param meeting
     */
    void addNewMeeting(Meeting meeting);

    /**
     *  Delete a meeting from my list
     *  @param meeting
     */
    void deleteMeeting(Meeting meeting);
}
