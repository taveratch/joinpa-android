package io.joinpa.joinpa.ui.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.maps.model.LatLng;

import io.joinpa.joinpa.R;
import io.joinpa.joinpa.models.Place;
import io.joinpa.joinpa.ui.fragments.MapFragment;

public class ShowMapActivity extends AppCompatActivity {

    private MapFragment mapFragment;
    private Place place;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_map);
        place = (Place)getIntent().getSerializableExtra("place");
        mapFragment = (MapFragment)getSupportFragmentManager().findFragmentById(R.id.map);
        initComponents();
    }

    public void initComponents() {
        LatLng latLng = new LatLng(place.getLat(),place.getLon());
        mapFragment.onMapLongClick(latLng);
        mapFragment.initCamera(latLng);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mapFragment != null) {
            this.getSupportFragmentManager().beginTransaction()
                    .remove(mapFragment)
                    .commit();
        }
    }
}
