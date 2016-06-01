package io.joinpa.joinpa.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by TAWEESOFT on 6/1/16 AD.
 */
public class DialogMessageUtil {

    public static void showToast(Context context , String message){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
