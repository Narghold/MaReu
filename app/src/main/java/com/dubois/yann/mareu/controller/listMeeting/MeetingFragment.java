package com.dubois.yann.mareu.controller.listMeeting;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dubois.yann.mareu.R;
import com.dubois.yann.mareu.event.FilterEvent;
import com.dubois.yann.mareu.model.Meeting;
import com.dubois.yann.mareu.service.DI;
import com.dubois.yann.mareu.service.MeetingApiService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.joda.time.Interval;
import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MeetingFragment extends Fragment {

    private MeetingApiService mApiService;

    private List<Meeting> mMeetingList = new ArrayList<>();

    private MeetingRecyclerViewAdapter mMeetingAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApiService = DI.getMeetingApiService();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_meeting, container, false);
        Context context = view.getContext();
        RecyclerView mRecyclerView = (RecyclerView) view;
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));
        mMeetingAdapter = new MeetingRecyclerViewAdapter();
        mRecyclerView.setAdapter(mMeetingAdapter);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Subscribe
    public void onFilteredList(FilterEvent event) {
        String mDateFilter = event.getDateChoice();
        String mPlaceFilter = event.getPlaceChoice();
        List<Meeting> meetingList = new ArrayList<>(mApiService.getMeetingList());

        boolean isDateFiltered = !mDateFilter.equals(getString(R.string.date_spinner_all));
        boolean isPlaceFiltered = !mPlaceFilter.equals(getString(R.string.place_spinner_all));

        if (isDateFiltered || isPlaceFiltered) {
            if (isDateFiltered) {
                meetingList = dateFilter(meetingList, mDateFilter);
            }
            if (isPlaceFiltered) {
                meetingList = placeFilter(meetingList, mPlaceFilter);
            }
        } else {
            meetingList = mApiService.getMeetingList();
        }
        //Update the filtered list
        mMeetingAdapter.setData(meetingList);
    }

    @Override
    public void onResume() {
        super.onResume();
        List<Meeting> meetingList = mApiService.getMeetingList();
        mMeetingAdapter.setData(meetingList);
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

    private List<Meeting> dateFilter(List<Meeting> meetingList, String date) {
        LocalDate dateFilter;
        Iterator<Meeting> iterator = meetingList.iterator();
        while(iterator.hasNext()){
            LocalDate meetingDate = iterator.next().getDateTime().toLocalDate();
            if (date.equals(getString(R.string.date_spinner_today))){
                dateFilter = LocalDate.now();
                if (!meetingDate.toDateTimeAtStartOfDay().equals(dateFilter.toDateTimeAtStartOfDay())) {
                    iterator.remove();
                }
            }else if (date.equals(getString(R.string.date_spinner_tomorrow))){
                dateFilter = LocalDate.now().plusDays(1);
                if (!meetingDate.toDateTimeAtStartOfDay().equals(dateFilter.toDateTimeAtStartOfDay())) {
                    iterator.remove();
                }
            }else if (date.equals(getString(R.string.date_spinner_this_week))){
                dateFilter = LocalDate.now().plusWeeks(1);
                LocalDate dateNow = LocalDate.now();
                Interval week = new Interval(dateNow.toDateTimeAtStartOfDay(), dateFilter.toDateTimeAtStartOfDay());
                if (!week.contains(meetingDate.toDateTimeAtStartOfDay())) {
                    iterator.remove();
                }
            }else if (date.equals(getString(R.string.date_spinner_next_week))) {
                LocalDate dateAfter = LocalDate.now().plusWeeks(2);
                LocalDate dateBefore = LocalDate.now().plusWeeks(1);
                Interval secondWeek = new Interval(dateBefore.toDateTimeAtStartOfDay(), dateAfter.toDateTimeAtStartOfDay());
                if (!secondWeek.contains(meetingDate.toDateTimeAtStartOfDay())) {
                    iterator.remove();
                }
            }else if (date.equals(getString(R.string.date_spinner_month))) {
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
}
