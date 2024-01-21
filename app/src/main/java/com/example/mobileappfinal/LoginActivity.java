package com.example.mobileappfinal;

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

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    List<User> userList;
    EditText editTextPassword , editTextEmail;
    Button buttonLogin;
    ProgressBar progressBar;
    TextView registerNow;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setting();
    }

    private void setting() {
        progressBar = findViewById(R.id.progressBarLogin);

        userList = new ArrayList<>();
        editTextEmail = findViewById(R.id.editTextEmailLogin);
        editTextPassword = findViewById(R.id.editTextPasswordLogin);

        registerNow = findViewById(R.id.registerNow);
        registerNow.setOnClickListener(this);

        buttonLogin = findViewById(R.id.buttonLogin);
        buttonLogin.setOnClickListener(this);

        File file = new File(getFilesDir(), "user.json");
        if (file.exists()){
            try {
                InputStream inputStream = new FileInputStream(file);
                int size = inputStream.available();
                byte[] buffer = new byte[size];
                inputStream.read(buffer);
                inputStream.close();
                String json = new String(buffer, StandardCharsets.UTF_8);

                // Parse the existing JSON array
                JSONArray jsonArray = new JSONArray(json);

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String email = jsonObject.getString("email");
                    String password = jsonObject.getString("pass");
                    String name = jsonObject.getString("name");
                    userList.add(new User(email , password , name));
                }
            }
            catch (IOException | JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        int id = view.getId();
        if (id == R.id.registerNow) {
            intent = new Intent(getApplicationContext(), RegisterActivity.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.buttonLogin) {
            String email, password;
            email = String.valueOf(editTextEmail.getText());
            password = String.valueOf(editTextPassword.getText());

            if (TextUtils.isEmpty(email)) {
                Toast.makeText(LoginActivity.this, "Enter email", Toast.LENGTH_SHORT).show();
                return;
            }
            if (TextUtils.isEmpty(password)) {
                Toast.makeText(LoginActivity.this, "Enter password", Toast.LENGTH_SHORT).show();
                return;
            }

            String mess = checkCorrectIn4(email, password);
            if (mess == "ok") {
//                    Go to home page activity
//                    Toast.makeText(LoginActivity.this , "Login successful" , Toast.LENGTH_SHORT).show();
//
//                    intent = new Intent(getApplicationContext() , HomeActivity.class);
//                    intent.putExtra("email" , email);
//                    startActivity(intent);
//                    finish();
            } else
                Toast.makeText(LoginActivity.this, "Wrong email or password", Toast.LENGTH_SHORT).show();
        }
    }

    private String checkCorrectIn4(String email, String password) {
        for(User user : userList)
        {
            if(user.email.trim().equals(email.trim()) && user.password.trim().equals(password.trim()))
                return "ok";
        }
        return "fail";
    }
}