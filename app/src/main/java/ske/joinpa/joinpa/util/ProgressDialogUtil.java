package ske.joinpa.joinpa.util;

import android.app.ProgressDialog;
import android.content.Context;

import ske.joinpa.joinpa.R;

/**
 * Created by Peter on 5/31/2016 AD.
 */
public class ProgressDialogUtil {

    private static ProgressDialog dialog;

    public static void show(Context context, String message) {
        dialog = new ProgressDialog(context);
        dialog.setMessage(message);
        dialog.setCancelable(false);
        dialog.show();
    }

    public static void dismiss() {
        if (dialog == null) return;
        if (dialog.isShowing()) dialog.dismiss();
    }
}
