package io.joinpa.joinpa.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.joinpa.joinpa.R;
import io.joinpa.joinpa.managers.App;
import io.joinpa.joinpa.managers.EventManager;
import io.joinpa.joinpa.managers.commands.GetInvitedEventResponse;
import io.joinpa.joinpa.managers.commands.ObjectResponse;
import io.joinpa.joinpa.models.Event;
import io.joinpa.joinpa.models.EventElement;
import io.joinpa.joinpa.models.Message;
import io.joinpa.joinpa.ui.adapters.ExploreEventAdapter;
import io.joinpa.joinpa.ui.adapters.InvitedEventAdapter;
import io.joinpa.joinpa.util.ProgressDialogUtil;
import retrofit2.Response;

/**
 * Created by TAWEESOFT on 5/15/16 AD.
 */
public class InvitesFragment extends ObservableFragment implements Observer {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.invited_event_recyclerView)
    RecyclerView recyclerView;

    private InvitedEventAdapter adapter;
    private App app;
    public EventManager eventManager;

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        eventManager.clearTempList();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_invites, container, false);
        ButterKnife.bind(this, view);

        app = App.getInstance();
        eventManager = app.getEventManager();

        initComponents();

        GetInvitedEventResponse response = new GetInvitedEventResponse();
        response.addObserver(this);
        response.execute();

        return view;
    }

    @Override
    public void update(java.util.Observable observable, Object data) {

        if (data == null) return;
        if (!(data instanceof ObjectResponse)) return;

        ObjectResponse objectResponse = (ObjectResponse) data;

        if (objectResponse.isSuccess()) {
            if (((Response)objectResponse.getData()).body().getClass() == EventElement.class) {
                Response<EventElement> response = (Response<EventElement>) objectResponse.getData();
                List<Event> events = response.body().getEventList();
                eventManager.setTempEventList(events);
                adapter.notifyDataSetChanged();
            } else {
                Response<Message> response = (Response<Message>) objectResponse.getData();
                ProgressDialogUtil.dismiss();
                Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_LONG).show();
            }

        } else {
            Log.e("error", objectResponse.getMessage());
            ProgressDialogUtil.dismiss();
        }
    }

    private void initComponents() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setChanged();
                notifyObservers();
            }
        });

        LinearLayoutManager llm = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(llm);
        adapter = new InvitedEventAdapter(getContext(), eventManager.getTempEventList());
        adapter.setObserver(this);
        recyclerView.setAdapter(adapter);

    }
}
