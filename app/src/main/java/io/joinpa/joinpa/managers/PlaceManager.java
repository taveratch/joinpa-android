package io.joinpa.joinpa.managers;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import io.joinpa.joinpa.models.Event;
import io.joinpa.joinpa.models.Place;

/**
 * Created by TAWEESOFT on 5/30/16 AD.
 */
public class PlaceManager {
    private List<Place> places;
    private Gson gson = new Gson();

    public PlaceManager(List<Place> places) {
        this.places = places;
    }

    public void addPlace(Place place) {
        places.add(place);
    }

    public void setPlaces(List<Place> places) {
        clear();
        this.places.addAll(places);
    }

    public List<Place> getPlaces() {
        return places;
    }

    public void clear() {
        places.clear();
    }

    public void loadInternalPlace(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(Constants.SP_KEY , context.MODE_PRIVATE);
        String eventsJson = sharedPreferences.getString(Constants.SP_PLACE_KEY , "[]");
        Type type = new TypeToken<List<Place>>(){}.getType();
        setPlaces((List<Place>)gson.fromJson(eventsJson,type));
    }

    public void saveInternalPlace(Context context) {
        SharedPreferences.Editor editor = context.getSharedPreferences(Constants.SP_KEY , context.MODE_PRIVATE).edit();
        String eventsJson = gson.toJson(getPlaces());
        editor.putString(Constants.SP_PLACE_KEY , eventsJson);
        editor.apply();
    }
}
