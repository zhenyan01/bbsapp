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
import com.example.bbsapp.Bean.User;
import com.example.bbsapp.R;
import com.google.gson.Gson;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class CreatePost extends AppCompatActivity {

    private EditText p_content, p_title;
    private ImageView back;
    private Button post;
    private Intent intent;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitvity_create_post);
        initView();

        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(p_content.getText().toString().isEmpty() || p_title.getText().toString().isEmpty()){
                    Toast.makeText(CreatePost.this, "Post content and title cannot be empty", Toast.LENGTH_SHORT).show();
                }else{
                    User user = User.getCurrentUser(User.class);
                    Post post = new Post();
                    post.setName(user.getUsername());
                    post.setAuthor(user);
                    post.setContent(p_content.getText().toString());
                    post.setTitle(p_title.getText().toString());
                    Gson gson = new Gson();
                    post.setBelongsTo(gson.fromJson(intent.getStringExtra("gsonCommunity"), Community.class));
                    post.save(new SaveListener<String>() {
                        @Override
                        public void done(String s, BmobException e) {
                            if(e == null){
                                p_content.setText("");
                                p_title.setText("");
                                Toast.makeText(CreatePost.this, "Post success", Toast.LENGTH_SHORT).show();
                                finish();
                            }else{
                                Toast.makeText(CreatePost.this, "Post failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }

    private void initView() {
        p_content = findViewById(R.id.acp_post_content);
        post = findViewById(R.id.acp_post_button);
        back = findViewById(R.id.acp_back);
        p_title = findViewById(R.id.acp_post_title);
        intent = getIntent();
    }
}
