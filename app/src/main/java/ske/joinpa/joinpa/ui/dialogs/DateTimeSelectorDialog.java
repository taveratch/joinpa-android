package ske.joinpa.joinpa.ui.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import butterknife.ButterKnife;
import butterknife.OnClick;
import ske.joinpa.joinpa.R;
import ske.joinpa.joinpa.managers.DateTimeHolder;

/**
 * Created by Peter on 6/1/2016 AD.
 */
public class DateTimeSelectorDialog extends Dialog {

    private DateTimeHolder dateTimeHolder;

    public DateTimeSelectorDialog(Context context, DateTimeHolder dateTimeHolder) {
        super(context, R.style.dialogStyle);
        this.dateTimeHolder = dateTimeHolder;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_datetime_selector);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_date)
    public void editDate() {
        dateTimeHolder.openDateDialog();
        dismiss();
    }

    @OnClick(R.id.btn_time)
    public void editTime() {
        dateTimeHolder.openTimeDialog();
        dismiss();
    }

    @OnClick(R.id.btn_close)
    public void close() {
        dismiss();
    }


}
