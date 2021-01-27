package com.example.bbsapp.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bbsapp.Bean.User;
import com.example.bbsapp.MainActivity;
import com.example.bbsapp.R;

import java.util.Timer;
import java.util.TimerTask;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;

public class Splash extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Timer timer = new Timer();
        timer.schedule(timerTask, 2000);

        Bmob.initialize(this, "491577816863ab34446202c63e40ad0a");
    }

    TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            //startActivity(new Intent(Splash.this, MainActivity.class));
            User user = User.getCurrentUser(User.class);
            if(user != null){
                startActivity(new Intent(Splash.this, MainActivity.class));
            }else{
                startActivity(new Intent(Splash.this, Login.class));
            }
        }
    };

}
