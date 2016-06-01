package io.joinpa.joinpa.ui.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.joinpa.joinpa.R;
import io.joinpa.joinpa.models.Event;

/**
 * Created by Peter on 6/1/2016 AD.
 */
public class EditNameDialog extends Dialog {

    @BindView(R.id.et_name)
    EditText etName;

    private Event event;
    private TextView tv;


    public EditNameDialog(Context context, Event event, TextView tv) {
        super(context, R.style.dialogStyle);
        this.event = event;
        this.tv = tv;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_edit_name);
        ButterKnife.bind(this);
        etName.setText(event.getName());
    }

    @OnClick(R.id.btn_save)
    public void save() {
        event.setName(etName.getText().toString());
        tv.setText(event.getName());
        dismiss();
    }

    @OnClick(R.id.btn_cancel)
    public void cancel() {
        dismiss();
    }

}
