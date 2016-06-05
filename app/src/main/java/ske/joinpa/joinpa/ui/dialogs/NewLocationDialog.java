package ske.joinpa.joinpa.ui.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import ske.joinpa.joinpa.R;
import ske.joinpa.joinpa.managers.App;
import ske.joinpa.joinpa.managers.Notifier;
import ske.joinpa.joinpa.models.Place;
import ske.joinpa.joinpa.ui.fragments.MapFragment;

/**
 * Created by TAWEESOFT on 5/31/16 AD.
 */
public class NewLocationDialog extends Dialog {

    @BindView(R.id.et_location_name)
    EditText etLocationName;

    @BindView(R.id.sw_enable_map)
    Switch swEnableMap;

    @BindView(R.id.sw_layout)
    LinearLayout swLayout;

    @BindView(R.id.btn_save)
    Button btnSave;

    @BindView(R.id.map_layout)
    RelativeLayout mapLayout;

    private static MapFragment mapFragment;
    private Context context;
    private App app;
    private Notifier notifier;

    public NewLocationDialog(Context context, Notifier notifier) {
        super(context,R.style.dialogStyle);
        this.context = context;
        this.notifier = notifier;
        app = App.getInstance();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_new_location);
        ButterKnife.bind(this);
        mapFragment = (MapFragment)((AppCompatActivity)context).getSupportFragmentManager().findFragmentById(R.id.map);
    }

    @OnCheckedChanged(R.id.sw_enable_map)
    public void showMap(boolean isChecked) {
        if (isChecked) {
            mapLayout.setVisibility(View.VISIBLE);
            RelativeLayout.LayoutParams rl1 = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT
            );
            rl1.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            rl1.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
            btnSave.setLayoutParams(rl1);
        }
        else{
            RelativeLayout.LayoutParams rl2 = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT
            );
            rl2.addRule(RelativeLayout.ALIGN_PARENT_END);
            rl2.addRule(RelativeLayout.BELOW , swLayout.getId());
            btnSave.setLayoutParams(rl2);
            mapLayout.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void dismiss() {
        super.dismiss();
        if (mapFragment != null) {
            ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction()
                    .remove(mapFragment)
                    .commit();
        }
    }

    @OnClick(R.id.btn_save)
    public void save() {
        String locationName = etLocationName.getText().toString().trim();
        if(locationName.length() == 0) return;
        double lat = mapFragment.getLat();
        double lon = mapFragment.getLon();
        Place place = new Place(locationName,lat,lon);
        place.setUseMap(swEnableMap.isChecked());
        app.getPlaceManager().addPlace(getContext(),place);
        notifier.setChanged();
        notifier.notifyObservers();
        dismiss();
    }
}
