package com.dubois.yann.mareu.controller.listMeeting;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dubois.yann.mareu.R;
import com.dubois.yann.mareu.model.Meeting;
import com.dubois.yann.mareu.service.DI;
import com.dubois.yann.mareu.service.MeetingApiService;

import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

class MeetingRecyclerViewAdapter extends RecyclerView.Adapter<MeetingRecyclerViewAdapter.ViewHolder>{

    private List<Meeting> mMeetingList;
    private MeetingApiService mApiService;
    private String mDateFilter;
    private String mPlaceFilter;

    public MeetingRecyclerViewAdapter() {
        mMeetingList = new ArrayList<>();
    }

    @NonNull
    @Override
    public MeetingRecyclerViewAdapter.ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_meeting, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        mApiService = DI.getMeetingApiService();
        Meeting meeting = mMeetingList.get(position);
        holder.mMeetingTitle.setText(meeting.getMeetingTitle());
        holder.mMeetingDetails.setText(setMeetingDetails(meeting));
        List<String> mParticipantList = meeting.getMeetingParticipants();
        holder.mMeetingParticipants.setText(TextUtils.join(", " , mParticipantList));
        holder.mMeetingCircle.setColorFilter(meeting.getColor());

        holder.mMeetingDeleteBtn.setOnClickListener(v -> {
            mApiService.deleteMeeting(meeting);
            setData(mMeetingList);
        });
    }

    @Override
    public int getItemCount() {
        return mMeetingList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.meeting_title)
        TextView mMeetingTitle;
        @BindView(R.id.meeting_details)
        TextView mMeetingDetails;
        @BindView(R.id.meeting_participants)
        TextView mMeetingParticipants;
        @BindView(R.id.meeting_delete_button)
        ImageView mMeetingDeleteBtn;
        @BindView(R.id.meeting_square)
        ImageView mMeetingCircle;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    /**
     * Method to set the description of the meeting
     * @param meeting
     * @return
     */
    private String setMeetingDetails(Meeting meeting){
        LocalDateTime localDate = meeting.getDateTime();
        DateTimeFormatter formatter = DateTimeFormat.forPattern("dd MMMM yyyy - HH:mm");
        String datetime = localDate.toString(formatter);
        String place = meeting.getMeetingPlace();
        return datetime + " - " + place;
    }

    /**
     * Update the meeting list
     * @param list
     */
    void setData(List<Meeting> list){
        this.mMeetingList = list;
        notifyDataSetChanged();
    }
}
