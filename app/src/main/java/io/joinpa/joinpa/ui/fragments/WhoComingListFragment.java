package io.joinpa.joinpa.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.joinpa.joinpa.R;
import io.joinpa.joinpa.models.Friend;
import io.joinpa.joinpa.ui.adapters.WhoComingAdapter;

/**
 * Created by TAWEESOFT on 6/1/16 AD.
 */
public class WhoComingListFragment extends Fragment {

    @BindView(R.id.rv)
    RecyclerView rv;

    private WhoComingAdapter adapter;
    private List<Friend> userList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_who_coming,container, false);
        ButterKnife.bind(this,view);
        Bundle bundle = getArguments();
        this.userList = (List<Friend>)bundle.getSerializable("userList");
        initComponents();
        return view;
    }

    public void initComponents() {
        adapter = new WhoComingAdapter(getContext(),userList);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.setAdapter(adapter);
    }
}
