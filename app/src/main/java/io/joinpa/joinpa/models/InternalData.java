package io.joinpa.joinpa.models;

import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import io.joinpa.joinpa.R;

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
            R.drawable.event_12,
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
