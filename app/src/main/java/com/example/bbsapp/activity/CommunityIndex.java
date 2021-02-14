package com.example.bbsapp.activity;

import android.content.Intent;
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

import com.example.bbsapp.Adapter.CommunityIndexAdapter;
import com.example.bbsapp.Adapter.HomeAdapter;
import com.example.bbsapp.Adapter.ReplyAdapter;
import com.example.bbsapp.Bean.Community;
import com.example.bbsapp.Bean.Post;
import com.example.bbsapp.Bean.Reply;
import com.example.bbsapp.Bean.User;
import com.example.bbsapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobPointer;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.QueryListener;

public class CommunityIndex extends AppCompatActivity {

    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private TextView c_name;
    private CommunityIndexAdapter communityIndexAdapter;
    private ImageView back;
    private FloatingActionButton floatingActionButtonAdd, floatingActionButtonCreate, floatingActionButtonSearch;
    private String c_id;
    Intent intent;
    List<Post> postList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community_index);

        initView();

        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_green_light,
                android.R.color.holo_red_light,
                android.R.color.holo_blue_light);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        floatingActionButtonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(floatingActionButtonSearch.getVisibility() == View.GONE) {
                    floatingActionButtonAdd.setImageResource(R.drawable.addition_fill);
                    floatingActionButtonCreate.setVisibility(View.VISIBLE);
                    floatingActionButtonSearch.setVisibility(View.VISIBLE);
                }else{
                    floatingActionButtonAdd.setImageResource(R.drawable.addition);
                    floatingActionButtonCreate.setVisibility(View.GONE);
                    floatingActionButtonSearch.setVisibility(View.GONE);
                }
            }
        });

        floatingActionButtonCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent createPostIntent = new Intent(CommunityIndex.this, CreatePost.class);
                createPostIntent.putExtra("gsonCommunity", intent.getStringExtra("gsonCommunity"));
                createPostIntent.putExtra("c_id", c_id);
                startActivity(createPostIntent);
            }
        });

        refresh();
    }

    private void refresh() {
        Community community = new Community();
        community.setObjectId(c_id);
        BmobQuery<Post> postBmobQuery = new BmobQuery<>();
        postBmobQuery.addWhereEqualTo("belongsTo",new BmobPointer(community));
        postBmobQuery.setLimit(999);
        postBmobQuery.order("-createdAt");

        postBmobQuery.findObjects(new FindListener<Post>() {
            @Override
            public void done(List<Post> list, BmobException e) {
                swipeRefreshLayout.setRefreshing(false);
                if(e == null){
                    postList = list;
                    communityIndexAdapter = new CommunityIndexAdapter(CommunityIndex.this, postList);
                    recyclerView.setLayoutManager(new LinearLayoutManager(CommunityIndex.this));
                    recyclerView.setAdapter(communityIndexAdapter);
                }else{
                    Toast.makeText(CommunityIndex.this, "fetch data failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initView() {
        recyclerView = findViewById(R.id.ci_recyclerView);
        swipeRefreshLayout = findViewById(R.id.ci_swipeRefreshLayout);
        c_name = findViewById(R.id.community_name_for_greeting);
        intent = getIntent();
        c_name.setText(intent.getStringExtra("c_name"));
        c_id = intent.getStringExtra("id");
        back = findViewById(R.id.aci_back);
        floatingActionButtonAdd = findViewById(R.id.aci_floatingActionButton_addition);
        floatingActionButtonCreate = findViewById(R.id.aci_floatingActionButton_create);
        floatingActionButtonSearch = findViewById(R.id.aci_floatingActionButton_search);
        //homeGreeting = getActivity().findViewById(R.id.homeGreeting);
    }
}
