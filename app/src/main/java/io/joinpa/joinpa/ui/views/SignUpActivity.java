package io.joinpa.joinpa.ui.views;

import android.graphics.Typeface;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.method.PasswordTransformationMethod;
import android.view.WindowManager;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.joinpa.joinpa.R;

public class SignUpActivity extends AppCompatActivity {

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);

        LinearLayoutManager llm = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(llm);

        // Set EditText to password field input
        passwordInput.setTransformationMethod(new PasswordTransformationMethod());
        cpasswordInput.setTransformationMethod(new PasswordTransformationMethod());


    }
}
