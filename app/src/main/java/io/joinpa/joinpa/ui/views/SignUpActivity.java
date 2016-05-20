package io.joinpa.joinpa.ui.views;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.method.PasswordTransformationMethod;
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
import io.joinpa.joinpa.managers.FormValidator;
import io.joinpa.joinpa.managers.LoadService;
import io.joinpa.joinpa.models.Token;
import io.joinpa.joinpa.ui.adapters.UserAvatarAdapter;

import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity implements Observer {

    private String username;
    private String password;
    private String cpassword;
    private String email;
    private String avatar;

    private UserAvatarAdapter adapter;

    @BindView(R.id.choose_avatar_view)
    RecyclerView recyclerView;

    @BindView(R.id.username_wrapper)
    TextInputLayout usernameWrapper;

    @BindView(R.id.username_input)
    EditText usernameInput;

    @BindView(R.id.password_wrapper)
    TextInputLayout passwordWrapper;

    @BindView(R.id.password_input)
    EditText passwordInput;

    @BindView(R.id.cpassword_wrapper)
    TextInputLayout cpasswordWrapper;

    @BindView(R.id.cpassowrd_input)
    EditText cpasswordInput;

    @BindView(R.id.email_wrapper)
    TextInputLayout emailWrapper;

    @BindView(R.id.email_input)
    EditText emailInput;

    public boolean validateForm() {

        if (!FormValidator.validateUsername(username)) {
            usernameWrapper.setError("Username must contain only a-z and is longer than 3 characters");
        }
        else {
            usernameWrapper.setError(null);
        }

        if (!FormValidator.validateEmail(email)) {
            emailWrapper.setError("Please enter a valid email");
        }
        else {
            emailWrapper.setError(null);
        }

        if (!FormValidator.validatePassword(password, cpassword)) {
            cpasswordWrapper.setError("Password do not match");
        }
        else {
            cpasswordWrapper.setError(null);
        }

        return FormValidator.validateEmail(email)
                && FormValidator.validatePassword(password, cpassword);
    }

    @OnClick(R.id.sign_up_button)
    public void signUp() {

        username = usernameWrapper.getEditText().getText().toString();
        password = passwordWrapper.getEditText().getText().toString();
        cpassword = cpasswordWrapper.getEditText().getText().toString();
        email = emailWrapper.getEditText().getText().toString();
        avatar = adapter.getSelectedAvatar() + "";

        if (validateForm()) {
            Map<String,String> map = new HashMap<>();
            map.put("username", username);
            map.put("password" , password);
            map.put("email" , email);
            map.put("avatar", avatar);
            LoadService loadService = LoadService.newInstance();
            loadService.signUp(map,this);
        }



    }

    @OnClick(R.id.sign_up_tab)
    public void toSignInPage() {
        Intent intent = new Intent(SignUpActivity.this, SigninActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);

        LinearLayoutManager llm = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(llm);

        adapter = new UserAvatarAdapter();
        recyclerView.setAdapter(adapter);

        // Set EditText to password field input
        passwordInput.setTransformationMethod(new PasswordTransformationMethod());
        cpasswordInput.setTransformationMethod(new PasswordTransformationMethod());

        // Get value from user inputs


        // Check user inputs


        // Sign up


    }


    @Override
    public void update(Observable observable, Object data) {
        if (data == null) return;
        if (!data.getClass().equals(Response.class)) return;
        Response<Token> response = (Response<Token>)data;
        if (response.isSuccessful()) {
            Token token = response.body();
            Log.e("key : " , token.getKey());
        }
        else {
            // TODO: 5/14/16 AD handle error
            try {
                Log.e("error message", response.errorBody().string());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        toSignInPage();
    }
}
