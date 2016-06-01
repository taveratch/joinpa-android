package io.joinpa.joinpa.ui.views;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.daasuu.ahp.AnimateHorizontalProgressBar;

import java.util.Observable;
import java.util.Observer;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.joinpa.joinpa.R;
import io.joinpa.joinpa.managers.App;
import io.joinpa.joinpa.managers.commands.ObjectResponse;
import io.joinpa.joinpa.managers.commands.VerifyResponse;
import io.joinpa.joinpa.models.User;
import retrofit2.Response;

public class SplashScreenActivity extends AppCompatActivity implements Observer {

    @BindView(R.id.progressBar)
    AnimateHorizontalProgressBar progressBar;

    private App app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        ButterKnife.bind(this);
        app = App.getInstance();
        initComponents();
        new Handler().postDelayed(new Runnable() {
            public void run() {
                // TODO: 5/14/16 AD remove these lines and load token in splash screen
                app.loadToken(SplashScreenActivity.this);
                if(app.isAuthenticated()) {
                    VerifyResponse verifyResponse = new VerifyResponse(app.getToken().getKey());
                    verifyResponse.addObserver(SplashScreenActivity.this);
                    verifyResponse.execute();
                }else{
                    navigateToSignIn();
                }
                finish();
            }
        },2000);
    }

    public void initComponents() {
        progressBar.setMax(2000);
        progressBar.setProgress(0);
        progressBar.setProgressWithAnim(2000);
    }

    public void navigateToMain() {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void navigateToSignIn() {
        Intent intent = new Intent(this,SignInActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void update(Observable observable, Object data) {
        if (data == null) return;
        if (!(data instanceof ObjectResponse)) return;
        ObjectResponse objectResponse = (ObjectResponse)data;
        if(objectResponse.isSuccess()) {
            Response<User> response = (Response<User>)objectResponse.getData();
            User user = response.body();
            app.setUser(user);
            app.getEventManager().loadInternalEvent(SplashScreenActivity.this);
            app.getPlaceManager().loadInternalPlace(SplashScreenActivity.this);
            navigateToMain();
        }else{
            Log.e("error" , objectResponse.getMessage());
            navigateToSignIn();
        }
    }
}
