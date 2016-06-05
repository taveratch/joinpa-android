package ske.joinpa.joinpa.ui.adapters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.io.Serializable;

import ske.joinpa.joinpa.models.Event;
import ske.joinpa.joinpa.ui.fragments.WhoComingListFragment;

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
        WhoComingListFragment fragment = new WhoComingListFragment();
        Bundle bundle = new Bundle();
        switch (position){
            case 0:
                bundle.putSerializable("userList" , (Serializable) event.getJoinedList());
                break;
            case 1:
                bundle.putSerializable("userList" , (Serializable) event.getPendingList());
                break;
            case 2:
                bundle.putSerializable("userList" , (Serializable) event.getDeclinedList());
                break;
        }
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getCount() {
        return pageCount;
    }
}
