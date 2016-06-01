package io.joinpa.joinpa.ui.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.joinpa.joinpa.R;
import io.joinpa.joinpa.managers.App;
import io.joinpa.joinpa.models.Message;
import io.joinpa.joinpa.managers.commands.ObjectResponse;
import io.joinpa.joinpa.managers.commands.SignInResponse;
import io.joinpa.joinpa.models.User;
import io.joinpa.joinpa.util.ProgressDialogUtil;
import retrofit2.Response;

public class SignInActivity extends AppCompatActivity implements Observer {

    @BindView(R.id.et_username)
    EditText etUsername;

    @BindView(R.id.et_password)
    EditText etPassword;

    private App app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        app = App.getInstance();
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnSignin)
    public void signIn() {

        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();

        Map<String,String> map = new HashMap<>();
        map.put("username", username);
        map.put("password", password);

        SignInResponse signInResponse = new SignInResponse(map, this);
        signInResponse.addObserver(this);
        signInResponse.execute();

        ProgressDialogUtil.show(this, "Signing in");
    }

    @Override
    public void update(Observable observable, Object o) {
        if ( o == null ) return;
        if (!(o instanceof ObjectResponse)) return;
        ObjectResponse objectResponse = (ObjectResponse)o;
        if (objectResponse.isSuccess()) {
            Response<User> response = (Response<User>)objectResponse.getData();
            User user = response.body();
            app.setUser(user); //Save User to App
            navigateToMain();
        } else {
            // TODO: 5/20/16 AD handle error
            Gson gson = new Gson();
            Message message = gson.fromJson(objectResponse.getMessage(), Message.class);
            Log.e("error message1" , message.getMessage());
            Toast.makeText(this, message.getMessage(), Toast.LENGTH_SHORT).show();
        }
        ProgressDialogUtil.dismiss();
    }

    @OnClick(R.id.layout_bottom)
    public void navigateToSignUp() {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
        finish();
    }



    private void navigateToMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}