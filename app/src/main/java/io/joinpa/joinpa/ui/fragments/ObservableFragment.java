package io.joinpa.joinpa.ui.fragments;

import android.support.v4.app.Fragment;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by TAWEESOFT on 5/15/16 AD.
 */
public abstract class ObservableFragment extends Fragment {
    class Observable extends java.util.Observable {
        public void setChanged() {
            super.setChanged();
        }
    }

    private Observable observable;

    public ObservableFragment() {
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
