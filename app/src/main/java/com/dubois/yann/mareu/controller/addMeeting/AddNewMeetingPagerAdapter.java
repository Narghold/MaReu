package com.dubois.yann.mareu.controller.addMeeting;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class AddNewMeetingPagerAdapter extends FragmentPagerAdapter {

    public AddNewMeetingPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    /**
     * getItem is called to instantiate the fragment for the given page.
     * @param position
     * @return
     */
    @Override
    public Fragment getItem(int position){
        switch (position){
            case 0 :
                return InformationFragment.newInstance();
            case 1:
                return ParticipantFragment.newInstance();
            default :
                return null;
        }
    }
    /**
     * Get the number of pages
     * @return
     */
    @Override
    public int getCount() {
        return 2;
    }
}
