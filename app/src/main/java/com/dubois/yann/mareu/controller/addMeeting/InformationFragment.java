package com.dubois.yann.mareu.controller.addMeeting;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;

import androidx.fragment.app.Fragment;

import com.dubois.yann.mareu.R;
import com.dubois.yann.mareu.event.AddMeetingEvent;
import com.dubois.yann.mareu.model.Meeting;
import com.dubois.yann.mareu.service.DI;
import com.dubois.yann.mareu.service.MeetingApiService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.joda.time.LocalDateTime;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InformationFragment extends Fragment{

    @BindView(R.id.add_new_title_input)
    EditText mTitleInput;
    @BindView(R.id.add_new_time_picker)
    TimePicker mTimePicker;
    @BindView(R.id.add_new_date_picker)
    DatePicker mDatePicker;
    @BindView(R.id.add_new_meeting_place_sp)
    Spinner mPlaceSpinner;
    @BindView(R.id.cancel_new_meeting_btn)
    Button mCancelButton;
    @BindView(R.id.confirm_new_meeting_btn)
    Button mConfirmButton;

    private String mPlaceChoice;
    private List<String> mParticipantList = new ArrayList<>();

    boolean isTitleInputEmpty = true;
    boolean isParticipantListEmpty = true;

    private MeetingApiService mMeetingApiService;

    public static Fragment newInstance() {
        return new InformationFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMeetingApiService = DI.getMeetingApiService();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_infomation, container, false);
        ButterKnife.bind(this, view);
        mConfirmButton.setEnabled(false);
        Context context = view.getContext();

        //Set TimePicker in 24h format
        mTimePicker.setIs24HourView(true);

        //Set Places Spinner
        ArrayAdapter<CharSequence> mPlaceSpinnerAdapter = ArrayAdapter.createFromResource(context, R.array.place_spinner_meeting, android.R.layout.simple_spinner_item);
        mPlaceSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mPlaceSpinner.setAdapter(mPlaceSpinnerAdapter);
        mPlaceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mPlaceChoice = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //Nothing
            }
        });

        //OnClickListener to cancel activity
        mCancelButton.setOnClickListener(v -> {
            Log.d("Cancel clicked", "btn has been clicked");
            getActivity().finish();
        });

        //Disable confirm btn if EditTexts && ParticipantList are empty
        mTitleInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                isTitleInputEmpty = mTitleInput.getText().toString().isEmpty();
                setEnabledConfirmBtn();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //OnClickListener to confirm meeting
        mConfirmButton.setOnClickListener(v -> {
            Log.d("Confirm clicked", "btn has been clicked");
            String mTitle = mTitleInput.getText().toString();
            int mHour = mTimePicker.getCurrentHour();
            int mMinutes = mTimePicker.getCurrentMinute();
            int mDay = mDatePicker.getDayOfMonth();
            int mMonth = mDatePicker.getMonth() + 1; //Because DatePicker returns 0 to 11
            int mYear = mDatePicker.getYear();
            LocalDateTime mDateTime = new LocalDateTime(mYear, mMonth, mDay, mHour, mMinutes);
            Random random = new Random();
            int mColor = Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256));
            Meeting mMeeting = new Meeting(mTitle, mPlaceChoice, mDateTime, mColor, mParticipantList);
            mMeetingApiService.addNewMeeting(mMeeting);
            getActivity().finish();
        });

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
    public void onAddMeeting(AddMeetingEvent event) {
        mParticipantList = event.getParticipantList();
        isParticipantListEmpty = mParticipantList.isEmpty();
        setEnabledConfirmBtn();
    }

    private void setEnabledConfirmBtn(){
        if (isParticipantListEmpty || isTitleInputEmpty){
            mConfirmButton.setEnabled(false);
        }else {
            mConfirmButton.setEnabled(true);
        }
    }

}
