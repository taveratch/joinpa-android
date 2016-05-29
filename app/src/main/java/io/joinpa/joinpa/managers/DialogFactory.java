package io.joinpa.joinpa.managers;

import android.app.Dialog;
import android.content.Context;

/**
 * Created by TAWEESOFT on 5/27/16 AD.
 */
public abstract class DialogFactory {

    Context context;

    public DialogFactory(Context context) {
        this.context = context;
    }

    abstract Dialog createDialog();
}
