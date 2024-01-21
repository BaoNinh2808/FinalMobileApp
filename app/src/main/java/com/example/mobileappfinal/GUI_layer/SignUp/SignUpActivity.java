package com.example.mobileappfinal.GUI_layer.SignUp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobileappfinal.DTO.User;
import com.example.mobileappfinal.GUI_layer.SignIn.SignInActivity;
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
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class SignUpActivity extends AppCompatActivity {

    EditText editTextName , editTextPassword , editTextEmail , editTextConfirmPassword;
    TextView tvEmptyEmail, tvEmptyPassword, tvEmptyConfirmPassword;
    TextView tvInvalidEmail, tvInvalidPassword, tvWrongConfirmPassword;
    Button buttonRegister;
    ProgressBar progressBar;
    TextView loginNow;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mAuth = FirebaseAuth.getInstance();

        setAndGetAllView();
        setEventClickButtonSignUp();
        setEventClickTextViewSignIn();
    }

    private void setEventClickTextViewSignIn() {
        loginNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void setEventClickButtonSignUp() {
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email, password, confirmPassword;

                email = editTextEmail.getText().toString();
                password = editTextPassword.getText().toString();
                confirmPassword = editTextConfirmPassword.getText().toString();

                HandlerWrongInput(email, password, confirmPassword, tvEmptyEmail, tvEmptyPassword, tvEmptyConfirmPassword, tvInvalidEmail, tvInvalidPassword, tvWrongConfirmPassword);

                if (isSuccessSignUp(email,password,confirmPassword)) {
                    mAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(SignUpActivity.this, "Account created",
                                                Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
                                        startActivity(intent);
                                        finish();
                                    } else {
                                        Toast.makeText(SignUpActivity.this, "Authentication failed.",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                } else {
                    Toast.makeText(SignUpActivity.this, "Sign Up failed.",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setAndGetAllView() {
        editTextName = findViewById(R.id.editTextInputName);
        editTextEmail = findViewById(R.id.editTextInputEmail);
        editTextPassword = findViewById(R.id.editTextInputPassword);
        editTextConfirmPassword = findViewById(R.id.editTextInputConfirmPassword);
        buttonRegister = findViewById(R.id.buttonCreateAccount);
        loginNow = findViewById(R.id.loginNow);
        tvEmptyEmail = findViewById(R.id.tvEmptyEmailNotificationSignup);
        tvEmptyPassword = findViewById(R.id.tvEmptyPasswordNotificationSignup);
        tvEmptyConfirmPassword = findViewById(R.id.tvEmptyConfirmPasswordNotificationSignup);
        tvInvalidEmail = findViewById(R.id.tvInvalidEmailNotificationSignup);
        tvInvalidPassword = findViewById(R.id.tvInvalidPasswordNotificationSignup);
        tvWrongConfirmPassword = findViewById(R.id.tvWrongConfirmPasswordNotificationSignup);
    }

    public boolean isSuccessSignUp(String email, String password, String confirmPassword){
        return (isValidEmail(email) && isValidPassword(password) && confirmPassword.equals(password));
    }

    private void HandlerWrongInput(String stringEmail, String stringPassword, String stringConfirmPassword, TextView tvEmptyEmail, TextView tvEmptyPassword, TextView tvEmptyConfirmPassword, TextView tvInvalidEmail, TextView tvInvalidPassword, TextView tvWrongConfirmPassword) {
        if (!stringEmail.isEmpty()){
            tvEmptyEmail.setVisibility(View.GONE);
        }
        if (!stringPassword.isEmpty()){
            tvEmptyPassword.setVisibility(View.GONE);
        }
        if (!stringConfirmPassword.isEmpty()){
            tvEmptyConfirmPassword.setVisibility(View.GONE);
        }
        if (stringEmail.isEmpty()){
            tvEmptyEmail.setVisibility(View.VISIBLE);
        }
        if (stringPassword.isEmpty()){
            tvEmptyPassword.setVisibility(View.VISIBLE);
        }
        if (stringConfirmPassword.isEmpty()){
            tvEmptyConfirmPassword.setVisibility(View.VISIBLE);
        }
        if (!isValidEmail(stringEmail) && !stringEmail.isEmpty()){
            tvInvalidEmail.setVisibility(View.VISIBLE);
        }
        if (isValidEmail(stringEmail)){
            tvInvalidEmail.setVisibility(View.GONE);
        }
        if (!isValidPassword(stringPassword) && !stringPassword.isEmpty()){
            tvInvalidPassword.setVisibility(View.VISIBLE);
        }
        if (isValidPassword(stringPassword)){
            tvInvalidPassword.setVisibility(View.GONE);
        }
        if (!stringConfirmPassword.equals(stringPassword) && !stringConfirmPassword.isEmpty()){
            tvWrongConfirmPassword.setVisibility(View.VISIBLE);
        }
        if (stringConfirmPassword.equals(stringPassword) && !stringConfirmPassword.isEmpty()){
            tvWrongConfirmPassword.setVisibility(View.GONE);
        }
    }

    private boolean isValidEmail(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean isValidPassword(String password) {
        if (password.length() < 8) {
            return false;
        }

        if (!containsUppercase(password)) {
            return false;
        }

        if (!containsSpecialCharacter(password)) {
            return false;
        }

        return true;
    }

    private boolean containsSpecialCharacter(String password) {
        Pattern specialCharPattern = Pattern.compile("[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]");
        return specialCharPattern.matcher(password).find();
    }

    private boolean containsUppercase(String password) {
        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                return true;
            }
        }
        return false;
    }

}