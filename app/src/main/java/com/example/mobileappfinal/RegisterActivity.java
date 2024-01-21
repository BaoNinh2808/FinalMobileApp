package com.example.mobileappfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
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
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    EditText editTextName , editTextPassword , editTextEmail , editTextConfirmPassword;
    Button buttonRegister;
    ProgressBar progressBar;
    TextView loginNow;
    List<User> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        setting();
    }

    private void setting() {
        progressBar = findViewById(R.id.progressBarRegister);
        Log.d("setting: " , "oke");

        userList = new ArrayList<>();
        editTextName = findViewById(R.id.editTextInputName);
        editTextEmail = findViewById(R.id.editTextInputEmail);
        editTextPassword = findViewById(R.id.editTextInputPassword);
        editTextConfirmPassword = findViewById(R.id.editTextInputConfirmPassword);

        loginNow = findViewById(R.id.loginNow);
        loginNow.setOnClickListener(this);

        buttonRegister = findViewById(R.id.buttonCreateAccount);
        buttonRegister.setOnClickListener(this);

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
        else
        {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            writeIntoFile();
        }


    }

    private void writeIntoFile() {
        File file = new File(getFilesDir(), "user.json");
        try {
            JSONArray jsonArray = new JSONArray();

            for (User user : userList) {
                JSONObject userObject = new JSONObject();
                userObject.put("email", user.email);
                userObject.put("pass", user.password);
                userObject.put("name", user.name);
                jsonArray.put(userObject);
            }

            // Write the JSON array to the file
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(jsonArray.toString().getBytes());
            fos.close();
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.buttonCreateAccount) {//                progressBar.setVisibility(View.VISIBLE);

            String email, password, name, confirmPassword;
            name = String.valueOf(editTextName.getText());
            email = String.valueOf(editTextEmail.getText());
            password = String.valueOf(editTextPassword.getText());
            confirmPassword = String.valueOf(editTextConfirmPassword.getText());

            if (TextUtils.isEmpty(email)) {
                Toast.makeText(RegisterActivity.this, "Enter email", Toast.LENGTH_SHORT).show();
                return;
            }
            if (TextUtils.isEmpty(password)) {
                Toast.makeText(RegisterActivity.this, "Enter password", Toast.LENGTH_SHORT).show();
                return;
            }
            if (TextUtils.isEmpty(name)) {
                Toast.makeText(RegisterActivity.this, "Enter name", Toast.LENGTH_SHORT).show();
                return;
            }
            if (!password.trim().equals(confirmPassword.trim())) {
                Log.d("checkinfor: ", password + " " + confirmPassword);
                Toast.makeText(RegisterActivity.this, "Confirm password doesn't match", Toast.LENGTH_SHORT).show();
                return;
            }

            String mess = checkCorrectIn4(email, password, name);
            if (mess == "ok") {
                Toast.makeText(RegisterActivity.this, "Account created", Toast.LENGTH_SHORT).show();
                userList.add(new User(email, password, name));
                writeIntoFile();

                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();

            } else
                Toast.makeText(RegisterActivity.this, mess, Toast.LENGTH_SHORT).show();
        } else if (id == R.id.loginNow) {
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private String checkCorrectIn4(String email, String password, String name) {
        for(User user : userList)
        {
            if(user.email.trim().equals(email.trim()))
                return "Email exist";
            if(user.password.trim().equals(password.trim()))
                return "Password Exist";
            if(user.name.trim().equals(name.trim()))
                return "Name Exist";
        }
        return "ok";
    }
}