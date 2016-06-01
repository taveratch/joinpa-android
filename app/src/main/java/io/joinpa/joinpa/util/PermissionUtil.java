package io.joinpa.joinpa.util;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;

/**
 * Created by TAWEESOFT on 6/1/16 AD.
 */
public class PermissionUtil {

    public static void canAccessLocation(Activity activity) {
        final int REQUEST_CODE_ASK_PERMISSIONS = 123;
        int hasWriteContactsPermission = activity.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION);
        if (hasWriteContactsPermission != PackageManager.PERMISSION_GRANTED) {
            activity.requestPermissions(new String[] {Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_CODE_ASK_PERMISSIONS);
        }
    }
}
