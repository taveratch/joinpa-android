package io.joinpa.joinpa.ui.views;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.joinpa.joinpa.R;
import io.joinpa.joinpa.managers.App;
import io.joinpa.joinpa.managers.LoadService;
import io.joinpa.joinpa.models.ObjectResponse;
import io.joinpa.joinpa.models.SignInResponse;
import io.joinpa.joinpa.models.Token;
import io.joinpa.joinpa.models.User;
import retrofit2.Response;

public class SigninActivity extends AppCompatActivity implements Observer{

    @BindView(R.id.et_username)
    EditText etUsername;

    @BindView(R.id.et_password)
    EditText etPassword;

    private ProgressDialog progressDialog;
    private App app;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        app = App.getInstance();
        // TODO: 5/14/16 AD remove this line and load token in splash screen
        app.loadToken(this);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnSignin)
    public void signin() {
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();
        Map<String,String> map = new HashMap<>();
        map.put("username", username);
        map.put("password" , password);
        SignInResponse signInResponse = new SignInResponse(map,this);
        signInResponse.addObserver(this);
        signInResponse.execute();
        showLoadingDialog();
    }

    @Override
    public void update(Observable observable, Object o) {
        if ( o == null ) return;
        if (!(o instanceof ObjectResponse)) return;
        ObjectResponse objectResponse = (ObjectResponse)o;
        if(objectResponse.isSuccess()) {
            Response<User> response = (Response<User>)objectResponse.getData();
            Log.e("email",response.body().getEmail());
            navigateToMain();
        }else{
            Log.e("error message1" , objectResponse.getMessage());
        }
        progressDialog.dismiss();
    }

    @OnClick(R.id.layout_bottom)
    public void navigateToSignUp() {
        Intent intent = new Intent(this , SignUpActivity.class);
        startActivity(intent);
        finish();
    }

    public void showLoadingDialog() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(R.string.please_wait));
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    public void navigateToMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
