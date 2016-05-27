package io.joinpa.joinpa.ui.views;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.method.PasswordTransformationMethod;
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
import io.joinpa.joinpa.managers.Commands.ObjectResponse;
import io.joinpa.joinpa.managers.FormValidator;
import io.joinpa.joinpa.models.Message;
import io.joinpa.joinpa.models.SignUpResponse;
import io.joinpa.joinpa.models.User;
import io.joinpa.joinpa.ui.adapters.UserAvatarAdapter;

import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity implements Observer {

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

    private String username;
    private String password;
    private String cpassword;
    private String email;
    private String avatar;

    private UserAvatarAdapter adapter;
    private ProgressDialog progressDialog;
    private App app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        app = App.getInstance();
        ButterKnife.bind(this);

        LinearLayoutManager llm = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(llm);

        adapter = new UserAvatarAdapter();
        recyclerView.setAdapter(adapter);

        // Set EditText to password field input
        passwordInput.setTransformationMethod(new PasswordTransformationMethod());
        cpasswordInput.setTransformationMethod(new PasswordTransformationMethod());

    }

    @Override
    public void update(Observable observable, Object data) {
        if ( data == null ) return;
        if (!(data instanceof ObjectResponse)) return;
        ObjectResponse objectResponse = (ObjectResponse)data;
        if (objectResponse.isSuccess()) {
            Response<User> response = (Response<User>)objectResponse.getData();
            User user = response.body();
            app.setUser(user); //Save User to App
            navigateToMain();
        } else {
            // TODO: 5/20/16 AD handle error
            Gson gson = new Gson();
            Message message = gson.fromJson(objectResponse.getMessage() , Message.class);
            Log.e("error message1" , message.getMessage());
            Toast.makeText(this, message.getMessage(), Toast.LENGTH_SHORT).show();
        }
        progressDialog.dismiss();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        toSignInPage();
    }

    private boolean validateForm() {

        if (!FormValidator.validateUsername(username)) {
            usernameWrapper.setError("Username must contain only a-z and is longer than 3 characters");
        } else {
            usernameWrapper.setError(null);
        }

        if (!FormValidator.validateEmail(email)) {
            emailWrapper.setError("Please enter a valid email");
        } else {
            emailWrapper.setError(null);
        }

        if (!FormValidator.validatePassword(password, cpassword)) {
            cpasswordWrapper.setError("Password do not match");
        } else {
            cpasswordWrapper.setError(null);
        }

        return FormValidator.validateEmail(email)
                && FormValidator.validatePassword(password, cpassword);
    }

    @OnClick(R.id.sign_up_button)
    private void signUp() {

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

            SignUpResponse signUpResponse = new SignUpResponse(map,this);
            signUpResponse.addObserver(this);
            signUpResponse.execute();
            showLoadingDialog();
        }

    }

    @OnClick(R.id.sign_up_tab)
    public void toSignInPage() {
        Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
        startActivity(intent);
        finish();
    }



    private void showLoadingDialog() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(R.string.please_wait));
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    private void navigateToMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}
