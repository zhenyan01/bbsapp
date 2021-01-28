package com.example.bbsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.bbsapp.Bean.User;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;

public class MainActivity extends AppCompatActivity {

    private TextView username, nickname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.username);
        nickname = findViewById(R.id.nickname);

        User user = User.getCurrentUser(User.class);
        String id = user.getObjectId();
        BmobQuery<User> myUser = new BmobQuery<>();
        myUser.getObject(id, new QueryListener<User>() {
            @Override
            public void done(User user, BmobException e) {
                if(e == null){
                    username.setText(user.getUsername());
                    nickname.setText(user.getNickname());
                }
            }
        });
    }
}