package io.joinpa.joinpa.ui.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;

import com.google.android.gms.maps.SupportMapFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import io.joinpa.joinpa.R;
import io.joinpa.joinpa.ui.fragments.MapFragment;

/**
 * Created by TAWEESOFT on 5/31/16 AD.
 */
public class NewLocationDialog extends Dialog{

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

    public NewLocationDialog(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_new_location_layout);
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
}
