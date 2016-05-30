package io.joinpa.joinpa.ui.dialogs;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.joinpa.joinpa.R;
import io.joinpa.joinpa.managers.App;
import io.joinpa.joinpa.managers.EventManager;
import io.joinpa.joinpa.managers.ObservableDialog;
import io.joinpa.joinpa.models.Event;
import io.joinpa.joinpa.ui.adapters.EventIconAdapter;

/**
 * Created by TAWEESOFT on 5/30/16 AD.
 */
public class NewCustomEventDialog extends ObservableDialog {

    @BindView(R.id.rv)
    RecyclerView rv;

    @BindView(R.id.et_event_name)
    EditText etEventName;

    private EventIconAdapter adapter;
    private App app;

    public NewCustomEventDialog(Context context) {
        super(context);
        app = App.getInstance();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_new_custom_event_layout);
        ButterKnife.bind(this);
        initComponents();
    }

    public void initComponents() {
        adapter = new EventIconAdapter(getContext() , app.getInternalData().eventIcon);
        LinearLayoutManager llm = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        rv.setLayoutManager(llm);
        rv.setAdapter(adapter);
    }

    @OnClick(R.id.btn_save)
    public void save() {
        String eventName = etEventName.getText().toString();
        if(eventName.length() == 0) return;
        Event event = new Event();
        event.setName(eventName);
        event.setIcon(adapter.getSelectedItem());
        EventManager eventManager = app.getEventManager();
        eventManager.addEvent(event);
        app.saveInternalEvent(getContext());
        setChanged();
        notifyObservers();
        dismiss();
    }

    @OnClick(R.id.btn_cancel)
    public void cancel() {
        dismiss();
    }
}
