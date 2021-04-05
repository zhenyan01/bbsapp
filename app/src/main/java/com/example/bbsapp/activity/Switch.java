package com.example.bbsapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bbsapp.MainActivity;
import com.example.bbsapp.R;

public class Switch extends AppCompatActivity {
    Button b1;
    Button b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switch);
        Log.i("I/info","Is jump to swtich?");

        b1 = findViewById(R.id.button1);
        b2 = findViewById(R.id.button2);

        // set listener to Auto-pairing
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.i("Todo", "right response!");
                startActivity(new Intent(Switch.this, Chatting.class));
            }
        });
        //
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Switch.this, MainActivity.class));
            }
        });
    }
}
