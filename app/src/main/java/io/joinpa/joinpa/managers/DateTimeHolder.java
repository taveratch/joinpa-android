package io.joinpa.joinpa.managers;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by TAWEESOFT on 5/31/16 AD.
 */
public class DateTimeHolder {

    private final String DATE_PATTERN = "EEE, d MMM";
    private final String TIME_PATTERN = "H:mm";

    private TextView tvDate, tvTime;
    private Context context;
    private Date date;
    private DateFormat formatter;

    public DateTimeHolder(Context context, TextView tvDate, TextView tvTime) {
        this.tvDate = tvDate;
        this.tvTime = tvTime;
        this.context = context;
        date = new Date();
        date.setTime(System.currentTimeMillis());
    }

    public void openDateDialog() {
        DatePickerDialog dialog = new DatePickerDialog(
                context,
                dateSetListener,
                date.getYear() + 1900,
                date.getMonth(),
                date.getDate());

        dialog.show();
    }

    public void openTimeDialog() {
        TimePickerDialog dialog = new TimePickerDialog(
                context,
                timeSetListener,
                date.getHours(),
                date.getMinutes(), true);

        dialog.show();
    }

    private DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            date.setYear(year-1900);
            date.setMonth(monthOfYear);
            date.setDate(dayOfMonth);
            formatter = new SimpleDateFormat(DATE_PATTERN);
            tvDate.setText(formatter.format(date));
        }
    };

    private TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            date.setHours(hourOfDay);
            date.setMinutes(minute);
            formatter = new SimpleDateFormat(TIME_PATTERN);
            tvTime.setText(formatter.format(date));
        }
    };

    public Date getDate() {
        return date;
    }
}
