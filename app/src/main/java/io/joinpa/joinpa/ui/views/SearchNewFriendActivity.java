package io.joinpa.joinpa.ui.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.joinpa.joinpa.R;
import io.joinpa.joinpa.managers.Commands.ObjectResponse;
import io.joinpa.joinpa.managers.Commands.SearchFriendResponse;
import io.joinpa.joinpa.models.Element;
import io.joinpa.joinpa.models.Friend;
import io.joinpa.joinpa.ui.adapters.SearchFriendAdapter;
import retrofit2.Response;

public class SearchNewFriendActivity extends AppCompatActivity implements Observer {

    @BindView(R.id.et_search)
    EditText etSearch;

    @BindView(R.id.rv)
    RecyclerView rv;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_new_friend);
        ButterKnife.bind(this);
        initComponents();
    }

    public void initComponents() {
        rv.setLayoutManager(new LinearLayoutManager(this));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @OnClick(R.id.img_search)
    public void search() {
        if(etSearch.getText().toString().length() == 0) return;

        SearchFriendResponse response = new SearchFriendResponse(etSearch.getText().toString());
        response.addObserver(this);
        response.execute();
    }

    @Override
    public void update(Observable observable, Object data) {
        if(data == null) return;
        if(!(data instanceof ObjectResponse)) return;
        ObjectResponse objectResponse = (ObjectResponse)data;
        if(objectResponse.isSuccess()) {
            Response<Element> response = (Response<Element>)objectResponse.getData();
            List<Friend> result = response.body().getSearchList();
            showSearchResult(result);
        }else{
            // TODO: 5/21/16 AD handle error
            Log.e("error" , objectResponse.getMessage());
        }
    }

    public void showSearchResult(List<Friend> result) {
        SearchFriendAdapter adapter = new SearchFriendAdapter(this,result);
        rv.setAdapter(adapter);
    }
}
