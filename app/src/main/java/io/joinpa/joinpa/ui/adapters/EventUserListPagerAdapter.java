package io.joinpa.joinpa.ui.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import io.joinpa.joinpa.models.Event;
import io.joinpa.joinpa.ui.fragments.WhoComingListFragment;

/**
 * Created by TAWEESOFT on 6/1/16 AD.
 */
public class EventUserListPagerAdapter extends FragmentPagerAdapter {

    private int pageCount;
    private Event event;
    public EventUserListPagerAdapter(FragmentManager fm, int pageCount, Event event) {
        super(fm);
        this.event = event;
        this.pageCount = pageCount;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new WhoComingListFragment(event.getJoinedList());
            case 1:
                return new WhoComingListFragment(event.getPendingList());
            case 2:
                return new WhoComingListFragment(event.getDeclinedList());
        }
        return null;
    }

    @Override
    public int getCount() {
        return pageCount;
    }
}
