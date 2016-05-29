package io.joinpa.joinpa.managers;

import android.app.Dialog;
import android.content.Context;

/**
 * Created by TAWEESOFT on 5/27/16 AD.
 */
public class NormalDialogFactory extends DialogFactory {

    public NormalDialogFactory(Context context) {
        super(context);
    }

    @Override
    public Dialog createDialog() {
        return null;
    }
}
