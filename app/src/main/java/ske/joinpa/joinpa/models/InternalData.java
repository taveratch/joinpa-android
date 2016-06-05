package ske.joinpa.joinpa.models;

import java.util.ArrayList;
import java.util.List;

import ske.joinpa.joinpa.R;

/**
 * Created by TAWEESOFT on 5/14/16 AD.
 */
public class InternalData {

    private static InternalData internalData;

    public Token token = null;
    public User user = null;
    public int[] avatarNormal = {
            R.drawable.avatar_1,
            R.drawable.avatar_2,
            R.drawable.avatar_3,
            R.drawable.avatar_4,
            R.drawable.avatar_5,
            R.drawable.avatar_6,
            R.drawable.avatar_7,
            R.drawable.avatar_8,
            R.drawable.avatar_9,
            R.drawable.avatar_10,
            R.drawable.avatar_11,
            R.drawable.avatar_12
    };

    public int[] eventIcon = {
            R.drawable.event_1,
            R.drawable.event_2,
            R.drawable.event_3,
            R.drawable.event_4,
            R.drawable.event_5,
            R.drawable.event_6,
            R.drawable.event_7,
            R.drawable.event_8,
            R.drawable.event_9,
            R.drawable.event_10,
            R.drawable.event_11,
            R.drawable.event_12
    };

    public int[] eventIconLarge = {
            R.drawable.event_1_large,
            R.drawable.event_2_large,
            R.drawable.event_3_large,
            R.drawable.event_4_large,
            R.drawable.event_5_large,
            R.drawable.event_6_large,
            R.drawable.event_7_large,
            R.drawable.event_8_large,
            R.drawable.event_9_large,
            R.drawable.event_10_large,
            R.drawable.event_11_large,
            R.drawable.event_12_large
    };

    public List<Event> events = null;
    public List<Place> places = null;

    private InternalData() {
        events = new ArrayList<>();
        places = new ArrayList<>();
    }

    public static InternalData getInstance() {
        if (internalData == null) internalData = new InternalData();
        return internalData;
    }


}
