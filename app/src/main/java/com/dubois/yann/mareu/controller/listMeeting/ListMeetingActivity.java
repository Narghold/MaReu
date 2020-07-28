package com.dubois.yann.mareu.controller.listMeeting;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import com.dubois.yann.mareu.R;
import com.dubois.yann.mareu.controller.addMeeting.AddNewMeetingActivity;
import com.dubois.yann.mareu.event.FilterEvent;
import com.dubois.yann.mareu.model.Meeting;
import com.dubois.yann.mareu.service.DI;
import com.dubois.yann.mareu.service.MeetingApiService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListMeetingActivity extends AppCompatActivity {

    private boolean isFilter = false;

    //UI Components
    @BindView(R.id.activity_list_meeting_fab_add)
    FloatingActionButton mAddNewMeetingBtn;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.card_filter)
    CardView mSpinnerLayout;
    @BindView(R.id.date_spinner)
    Spinner mDateSpinnerFilter;
    @BindView(R.id.place_spinner)
    Spinner mPlaceSpinnerFilter;

    private MeetingApiService mApiService;

    private List<Meeting> mMeetingList = new ArrayList<>();
    private String mDateChoice = String.valueOf(R.string.date_spinner_all);
    private String mPlaceChoice = String.valueOf(R.string.place_spinner_all);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_meeting);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        mApiService = DI.getMeetingApiService();
        setFilter();

        mAddNewMeetingBtn.setOnClickListener(v -> {
            Intent addNewMeetingActivity = new Intent(getApplicationContext() , AddNewMeetingActivity.class);
            startActivity(addNewMeetingActivity);
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_filter, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_filter) {
            if (mMeetingList.size() != 0) {
                setFilter();
                setupFilterSpinner();
            } else {
                Toast.makeText(this, "Aucune réunion à filtrer.", Toast.LENGTH_LONG).show();
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        mMeetingList = mApiService.getMeetingList();
        isFilter = false;
        setFilter();
        super.onResume();
    }

    /**
     *  Set the filters if users click on the filter menu
     */
    private void setFilter(){
        if (isFilter){
            mSpinnerLayout.setVisibility(View.VISIBLE);
            isFilter = false;
        }else {
            mSpinnerLayout.setVisibility(View.GONE);
            isFilter = true;
        }
    }

    /**
     *  Setup the different spinners to filter meetings
     */
    public void setupFilterSpinner(){
        ArrayAdapter<CharSequence> mDateAdapter = ArrayAdapter.createFromResource(this, R.array.date_spinner_choice, android.R.layout.simple_spinner_item);
        mDateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mDateSpinnerFilter.setAdapter(mDateAdapter);
        mDateSpinnerFilter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mDateChoice = parent.getItemAtPosition(position).toString();
                EventBus.getDefault().post(new FilterEvent(mDateChoice , mPlaceChoice));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //Nothing
            }
        });

        ArrayAdapter<CharSequence> mPlaceAdapter = ArrayAdapter.createFromResource(this, R.array.place_spinner_choice, android.R.layout.simple_spinner_item);
        mPlaceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mPlaceSpinnerFilter.setAdapter(mPlaceAdapter);
        mPlaceSpinnerFilter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mPlaceChoice = parent.getItemAtPosition(position).toString();
                EventBus.getDefault().post(new FilterEvent(mDateChoice , mPlaceChoice));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //Nothing
            }
        });
    }
}
