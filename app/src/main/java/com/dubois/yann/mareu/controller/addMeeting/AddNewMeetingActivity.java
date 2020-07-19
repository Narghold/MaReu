package com.dubois.yann.mareu.controller.addMeeting;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.dubois.yann.mareu.R;
import com.dubois.yann.mareu.service.DI;
import com.dubois.yann.mareu.service.MeetingApiService;
import com.google.android.material.tabs.TabLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddNewMeetingActivity extends AppCompatActivity{

    private MeetingApiService mMeetingApiService;

    @BindView(R.id.tabs)
    TabLayout mTabLayout;
    @BindView(R.id.add_new_meeting_vp)
    ViewPager mViewPager;

    private AddNewMeetingPagerAdapter mPagerAdapter;

    @SuppressLint("NewApi")
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_meeting);
        ButterKnife.bind(this);
        mMeetingApiService = DI.getMeetingApiService();

        mPagerAdapter = new AddNewMeetingPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));
    }
}
