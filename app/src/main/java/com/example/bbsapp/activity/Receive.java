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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.bbsapp.Adapter.CommunityAdapter;
import com.example.bbsapp.Adapter.ReplyAdapter;
import com.example.bbsapp.Bean.Community;
import com.example.bbsapp.Bean.Post;
import com.example.bbsapp.Bean.Reply;
import com.example.bbsapp.R;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobPointer;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.QueryListener;

public class Receive extends AppCompatActivity{

    private TextView username, content, time;
    private ImageView back;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ReplyAdapter replyAdapter;
    private Intent intent;
    List<Reply> replyList;

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

        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_green_light,
                android.R.color.holo_red_light,
                android.R.color.holo_blue_light);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
            }
        });

        refresh();


    }

    private void refresh() {
        intent = getIntent();
        Post post = new Post();
        post.setObjectId(intent.getStringExtra("id"));
        BmobQuery<Reply> replyBmobQuery = new BmobQuery<>();
        replyBmobQuery.addWhereEqualTo("belongsTo",new BmobPointer(post));
        replyBmobQuery.setLimit(999);
        replyBmobQuery.order("-createdAt");
        replyBmobQuery.findObjects(new FindListener<Reply>() {
            @Override
            public void done(List<Reply> list, BmobException e) {
                swipeRefreshLayout.setRefreshing(false);
                if(e == null){
                    replyList = list;
                    replyAdapter = new ReplyAdapter(Receive.this, replyList);
                    recyclerView.setLayoutManager(new LinearLayoutManager(Receive.this));
                    recyclerView.setAdapter(replyAdapter);
                }else{
                    Toast.makeText(Receive.this, "fetch data failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
        /*replyBmobQuery.findObjects(new FindListener<Reply>() {
            @Override
            public void done(List<Community> list, BmobException e) {
                swipeRefreshLayout.setRefreshing(false);
                if(e == null){
                    replyList = list;
                    communityAdapter = new CommunityAdapter(getActivity(), communityList);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                    recyclerView.setAdapter(communityAdapter);
                }else{
                    Toast.makeText(getActivity(), "fetch data failed", Toast.LENGTH_SHORT).show();
                }
            }
        });*/
    }

    private void initPost() {
        intent = getIntent();
        username.setText(intent.getStringExtra("username"));
        content.setText(intent.getStringExtra("content"));
        time.setText(intent.getStringExtra("time"));
    }

    private void initView() {
        username = findViewById(R.id.username);
        content = findViewById(R.id.content);
        time = findViewById(R.id.time);
        back = findViewById(R.id.back);
        recyclerView = findViewById(R.id.reply_recyclerView);
        swipeRefreshLayout = findViewById(R.id.reply_swipeRefreshLayout);
        intent = getIntent();
    }
}
