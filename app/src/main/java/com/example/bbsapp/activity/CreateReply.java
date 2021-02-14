package com.example.bbsapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bbsapp.Bean.Community;
import com.example.bbsapp.Bean.Post;
import com.example.bbsapp.Bean.Reply;
import com.example.bbsapp.Bean.User;
import com.example.bbsapp.R;
import com.google.gson.Gson;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class CreateReply extends AppCompatActivity {

    private EditText r_content;
    private ImageView back;
    private Button reply;
    private Intent intent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_reply);
        initView();

        reply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(r_content.getText().toString().isEmpty()){
                    Toast.makeText(CreateReply.this, "Post content and title cannot be empty", Toast.LENGTH_SHORT).show();
                }else{
                    User user = User.getCurrentUser(User.class);
                    Reply reply = new Reply();
                    Gson gson = new Gson();
                    reply.setBelongsTo(gson.fromJson(intent.getStringExtra("gsonPost"), Post.class));
                    reply.setReplyUser(user);
                    reply.setNickname(user.getNickname());
                    reply.setContent(r_content.getText().toString());
                    reply.save(new SaveListener<String>() {
                        @Override
                        public void done(String s, BmobException e) {
                            if(e == null){
                                r_content.setText("");
                                Toast.makeText(CreateReply.this, "Reply success", Toast.LENGTH_SHORT).show();
                                finish();
                            }else{
                                Toast.makeText(CreateReply.this, "Reply failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initView() {
        intent = getIntent();
        reply = findViewById(R.id.acr_reply_button);
        back = findViewById(R.id.acr_back);
        r_content = findViewById(R.id.acr_reply_content);
    }
}
