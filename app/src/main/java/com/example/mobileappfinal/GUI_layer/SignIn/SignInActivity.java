package com.example.mobileappfinal.GUI_layer.SignIn;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobileappfinal.DTO.User;
import com.example.mobileappfinal.GUI_layer.Home.HomeActivity;
import com.example.mobileappfinal.GUI_layer.SignUp.SignUpActivity;
import com.example.mobileappfinal.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class SignInActivity extends AppCompatActivity {
    EditText editTextPassword , editTextEmail;
    TextView tvForgotPassword, tvEmptyEmail, tvEmptyPassword, tvWrongEmail, tvWrongPassword;
    Button buttonLogin;
    TextView registerNow;

    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        mAuth = FirebaseAuth.getInstance();

        setAndGetAllWin();
        setEventClickButtonSignIn();
        setEventClickTextViewSignUp();
    }

    private void setEventClickTextViewSignUp() {
        registerNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignInActivity.this, SignUpActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void setEventClickButtonSignIn() {
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editTextEmail.getText().toString();
                String password = editTextPassword.getText().toString();


                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    Toast.makeText(SignInActivity.this, "Sign In Success.",
                                            Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(SignInActivity.this, HomeActivity.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    handleSignInError(email, password, tvEmptyEmail,tvEmptyPassword,tvWrongEmail,tvWrongPassword);
                                    Toast.makeText(SignInActivity.this, "Sign In failed.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

            }
        });
    }

    public void handleSignInError(String email, String password, TextView tvEmptyEmail, TextView tvEmptyPassword, TextView tvWrongEmail, TextView tvWrongPassword) {
        if (email.isEmpty() && !password.isEmpty()){
            tvWrongEmail.setVisibility(View.GONE);
            tvWrongPassword.setVisibility(View.GONE);
            tvEmptyPassword.setVisibility(View.GONE);
            tvEmptyEmail.setVisibility(View.VISIBLE);
        } else if (password.isEmpty() && !email.isEmpty()){
            tvWrongEmail.setVisibility(View.GONE);
            tvWrongPassword.setVisibility(View.GONE);
            tvEmptyEmail.setVisibility(View.GONE);
            tvEmptyPassword.setVisibility(View.VISIBLE);
        } else if (email.isEmpty() && password.isEmpty()){
            tvWrongEmail.setVisibility(View.GONE);
            tvWrongPassword.setVisibility(View.GONE);
            tvEmptyEmail.setVisibility(View.VISIBLE);
            tvEmptyPassword.setVisibility(View.VISIBLE);
        } else if (!email.isEmpty() && !password.isEmpty()){
            tvEmptyEmail.setVisibility(View.GONE);
            tvEmptyPassword.setVisibility(View.GONE);
            tvWrongEmail.setVisibility(View.VISIBLE);
            tvWrongPassword.setVisibility(View.VISIBLE);
        } else{
            tvEmptyEmail.setVisibility(View.GONE);
            tvEmptyPassword.setVisibility(View.GONE);
            tvWrongEmail.setVisibility(View.GONE);
            tvWrongPassword.setVisibility(View.GONE);
        }
    }

    private void setAndGetAllWin() {
        editTextEmail = findViewById(R.id.editTextEmailLogin);
        editTextPassword = findViewById(R.id.editTextPasswordLogin);
        tvEmptyEmail = findViewById(R.id.tvEmptyEmailNotification);
        tvWrongEmail = findViewById(R.id.tvWrongEmailNotification);
        tvEmptyPassword = findViewById(R.id.tvEmptyPasswordNotification);
        tvWrongPassword = findViewById(R.id.tvWrongPasswordNotification);
        tvForgotPassword = findViewById(R.id.tvForgotPassword);
        registerNow = findViewById(R.id.registerNow);
        buttonLogin = findViewById(R.id.buttonLogin);
    }


}