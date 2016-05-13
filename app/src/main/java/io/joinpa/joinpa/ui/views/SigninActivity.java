package io.joinpa.joinpa.ui.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Observable;
import java.util.Observer;

import butterknife.BindView;
import butterknife.OnClick;
import io.joinpa.joinpa.R;

public class SigninActivity extends AppCompatActivity implements Observer{

    @BindView(R.id.et_username)
    EditText etUsername;

    @BindView(R.id.et_password)
    EditText etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
    }

    @OnClick(R.id.btnSignin)
    public void signin() {
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();
        //todo
        // Do sign in
    }

    @Override
    public void update(Observable observable, Object o) {
        //// TODO: 5/13/16 AD do next thing after received the response from server
    }
}
