package ske.joinpa.joinpa.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Peter on 5/30/2016 AD.
 */
public class DateUtil {

    private static final String TIME_FORMAT = "H:mm";
    private static final String DATE_FORMAT = "EEE, d MMM";

    private static DateFormat format;

    public static String getDate(Date date) {
        format = new SimpleDateFormat(DATE_FORMAT, Locale.getDefault());
        return format.format(date);
    }

    public static String getTime(Date date) {
        format = new SimpleDateFormat(TIME_FORMAT, Locale.getDefault());
        return format.format(date);
    }
}
