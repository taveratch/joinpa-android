package io.joinpa.joinpa.managers;

import android.app.Dialog;
import android.content.Context;

import java.util.Observer;

/**
 * Created by TAWEESOFT on 5/30/16 AD.
 */
public class ObservableDialog extends Dialog {

    class Observable extends java.util.Observable {
        public void setChanged() {
            super.setChanged();
        }
    }

    private Observable observable;

    public ObservableDialog(Context context) {
        super(context);
        observable = new Observable();
    }

    public void addObserver(Observer observer) {
        observable.addObserver(observer);
    }

    public void notifyObservers() {
        observable.notifyObservers();
    }

    public void setChanged() {
        observable.setChanged();
    }
}
