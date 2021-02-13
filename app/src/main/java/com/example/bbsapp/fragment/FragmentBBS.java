package com.example.bbsapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.bbsapp.Adapter.CommunityAdapter;
import com.example.bbsapp.Adapter.HomeAdapter;
import com.example.bbsapp.Bean.Community;
import com.example.bbsapp.Bean.Post;
import com.example.bbsapp.Bean.User;
import com.example.bbsapp.R;

import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.QueryListener;

public class FragmentBBS extends Fragment {

    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private CommunityAdapter communityAdapter;
    List<Community> communityList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_bbs, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initView();

        //refresh();

        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_green_light,
                android.R.color.holo_red_light,
                android.R.color.holo_blue_light);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
            }
        });

        //getUserInfo();

        refresh();
    }


    private void refresh() {
        BmobQuery<Community> communityBmobQuery = new BmobQuery<>();
        communityBmobQuery.setLimit(999);
        communityBmobQuery.order("-createdAt");
        communityBmobQuery.findObjects(new FindListener<Community>() {
            @Override
            public void done(List<Community> list, BmobException e) {
                swipeRefreshLayout.setRefreshing(false);
                if(e == null){
                    communityList = list;
                    communityAdapter = new CommunityAdapter(getActivity(), communityList);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                    recyclerView.setAdapter(communityAdapter);
                }else{
                    Toast.makeText(getActivity(), "fetch data failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initView() {
        recyclerView = getActivity().findViewById(R.id.c_recyclerView);
        swipeRefreshLayout = getActivity().findViewById(R.id.c_swipeRefreshLayout);
    }
}
