package ske.joinpa.joinpa.ui.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.maps.model.LatLng;

import ske.joinpa.joinpa.R;
import ske.joinpa.joinpa.models.Place;
import ske.joinpa.joinpa.ui.fragments.MapFragment;
import ske.joinpa.joinpa.util.ProgressDialogUtil;

public class ShowMapActivity extends AppCompatActivity {

    private MapFragment mapFragment;
    private Place place;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_map);
        ProgressDialogUtil.dismiss();
        place = (Place)getIntent().getSerializableExtra("place");
        mapFragment = (MapFragment)getSupportFragmentManager().findFragmentById(R.id.map);
        initComponents();
    }

    public void initComponents() {
        LatLng latLng = new LatLng(place.getLat(),place.getLon());
        mapFragment.onMapLongClick(latLng);
        mapFragment.initCamera(latLng);
        mapFragment.setZoomCurrentLocation(false);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (mapFragment != null) {
            this.getSupportFragmentManager().beginTransaction()
                    .remove(mapFragment)
                    .commit();
        }
    }
}
