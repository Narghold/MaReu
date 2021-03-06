package com.dubois.yann.mareu.controller.addMeeting;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.dubois.yann.mareu.R;
import com.google.android.material.tabs.TabLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddNewMeetingActivity extends AppCompatActivity{

    @BindView(R.id.tabs)
    TabLayout mTabLayout;
    @BindView(R.id.add_new_meeting_vp)
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_meeting);
        ButterKnife.bind(this);

        AddNewMeetingPagerAdapter mMPagerAdapter = new AddNewMeetingPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mMPagerAdapter);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));
    }
}
