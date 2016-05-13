package io.joinpa.joinpa.ui.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.joinpa.joinpa.R;
import io.joinpa.joinpa.managers.APIService;
import io.joinpa.joinpa.managers.HttpManager;
import io.joinpa.joinpa.managers.LoadService;
import io.joinpa.joinpa.models.Token;
import okhttp3.RequestBody;
import retrofit2.Response;

public class SigninActivity extends AppCompatActivity implements Observer{

    @BindView(R.id.et_username)
    EditText etUsername;

    @BindView(R.id.et_password)
    EditText etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnSignin)
    public void signin() {
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();
        Map<String,String> map = new HashMap<>();
        map.put("username", username);
        map.put("password" , password);
        LoadService loadService = LoadService.newInstance();
        loadService.signIn(map,this);
    }

    @Override
    public void update(Observable observable, Object o) {
        if ( o == null ) return;
        if (!o.getClass().equals(Response.class)) return;
        Response<Token> response = (Response<Token>)o;
        if(response.isSuccessful()) {
            Token token = response.body();
            Log.e("key : " , token.getKey());
        }else{
            //todo handle errors
        }
    }
}
