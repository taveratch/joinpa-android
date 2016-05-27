package io.joinpa.joinpa.managers;

import android.app.Dialog;
import android.content.Context;

/**
 * Created by TAWEESOFT on 5/27/16 AD.
 */
public class CustomDialogFactory extends DialogFactory{

    public CustomDialogFactory(Context context) {
        super(context);
    }

    @Override
    public Dialog createDialog() {
        return null;
    }
}
