package io.joinpa.joinpa;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by TAWEESOFT on 5/28/16 AD.
 */
public class FirebaseId extends FirebaseInstanceIdService {

    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.e("zxczxczxcxzc", "Refreshed token: " + refreshedToken);
    }
}
