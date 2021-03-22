package com.example.bbsapp.activity;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bbsapp.Bean.User;
import com.example.bbsapp.MainActivity;
import com.example.bbsapp.R;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class Login extends AppCompatActivity {
    private EditText username, password;
    private Button loginButton, signUpButton;
    private String Tag = "Login";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        loginButton = findViewById(R.id.loginButton);
        signUpButton = findViewById(R.id.signUpButton);
        Log.i("info","onCreate in Login triggered");
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final User user = new User();
                user.setUsername(username.getText().toString().trim());
                user.setPassword(password.getText().toString().trim());

                user.login(new SaveListener<User>() {
                    @Override
                    public void done(User user, BmobException e) {
                        if(e == null) {
                            // 02-24-2021
                            Toast.makeText(Login.this, "Login success", Toast.LENGTH_SHORT).show();
                            //startActivity(new Intent(Login.this, MainActivity.class));
                            Log.i("I/info","Before start?");
                            startActivity(new Intent(Login.this, Switch.class));
                        }else{
                            Toast.makeText(Login.this, "Login failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, SignUp.class));
            }
        });
    }

    // for testing


    @Override
    protected void onStart() {
        super.onStart();
        Log.i(Tag,"onStart triggered");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(Tag,"onStop triggered");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(Tag,"onResume triggered");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(Tag,"onPause triggered");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(Tag,"onDestroy triggered");
    }
}
