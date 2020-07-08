package com.dubois.yann.mareu.service;

import com.dubois.yann.mareu.model.Meeting;

import java.util.List;

public interface FilterApiService {

    /**
     * Filter by place
     * @param meetingList
     * @param place
     * @return
     */
    List<Meeting> placeFilter(List<Meeting> meetingList, String place);

    /**
     * Filter by date
     * @param meetingList
     * @param date
     * @return
     */
    List<Meeting> dateFilter(List<Meeting> meetingList, String date);
}
