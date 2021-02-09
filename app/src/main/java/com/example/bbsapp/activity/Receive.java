package com.example.bbsapp.activity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bbsapp.Bean.Post;
import com.example.bbsapp.R;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;

public class Receive extends AppCompatActivity {

    private TextView username, content, time;
    private ImageView back;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive);
        initView();
        initPost();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initPost() {


        Intent intent = getIntent();
        username.setText(intent.getStringExtra("username"));
        content.setText(intent.getStringExtra("content"));
        time.setText(intent.getStringExtra("time"));

        /*Post post = new Post();
        BmobQuery<Post> postBmobQuery = new BmobQuery<>();
        postBmobQuery.getObject(id, new QueryListener<Post>() {
            @Override
            public void done(Post post, BmobException e) {
                if(e == null){
                    username.setText(post.getName());
                    content.setText(post.getContent());
                    time.setText(post.getCreatedAt());
                }else{
                    Toast.makeText(Receive.this, "fetch failed", Toast.LENGTH_SHORT).show();
                }
            }
        });*/
    }

    private void initView() {
        username = findViewById(R.id.username);
        content = findViewById(R.id.content);
        time = findViewById(R.id.time);
        back = findViewById(R.id.back);
    }
}
