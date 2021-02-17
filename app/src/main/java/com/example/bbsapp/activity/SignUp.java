package com.example.bbsapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bbsapp.Bean.User;
import com.example.bbsapp.R;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class SignUp extends AppCompatActivity {

    private EditText username, password, nickname;
    private Button signUpButton;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        nickname = findViewById(R.id.nickname);
        signUpButton = findViewById(R.id.signUpButton);

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final User user = new User();
                String tempUsername = username.getText().toString().trim();
                String tempPassword = password.getText().toString().trim();
                String tempNickname = nickname.getText().toString().trim();

                if(tempUsername.length() == 0){
                    Toast.makeText(SignUp.this, "Error: Empty username", Toast.LENGTH_SHORT).show();
                }else if(tempPassword.length() == 0){
                    Toast.makeText(SignUp.this, "Error: Empty password", Toast.LENGTH_SHORT).show();
                }else if(tempNickname.length() == 0){
                    Toast.makeText(SignUp.this, "Error: Empty nickname", Toast.LENGTH_SHORT).show();
                }else{
                    user.setUsername(tempUsername);
                    user.setPassword(tempPassword);
                    user.setNickname(tempNickname);

                    user.signUp(new SaveListener<User>() {
                        @Override
                        public void done(User user, BmobException e) {
                            if(e == null) {
                                Toast.makeText(SignUp.this, "Register success", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(SignUp.this, MainActivity.class));
                            }else{
                                Toast.makeText(SignUp.this, "Register failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

    }
}
