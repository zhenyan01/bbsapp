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

import com.example.bbsapp.Adapter.HomeAdapter;
import com.example.bbsapp.Bean.Post;
import com.example.bbsapp.Bean.User;
import com.example.bbsapp.R;

import org.w3c.dom.Text;

import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.QueryListener;

public class FragmentHome extends Fragment {

    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private TextView nickname;
    private HomeAdapter homeAdapter;
    List<Post> postList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragmenthome, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initView();

        Bmob.initialize(getActivity(), "491577816863ab34446202c63e40ad0a");

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

        getUserInfo();

        refresh();
    }

    private void getUserInfo() {
        User user = User.getCurrentUser(User.class);
        String id = user.getObjectId();
        BmobQuery<User> bmobQuery = new BmobQuery<>();
        bmobQuery.getObject(id, new QueryListener<User>() {
            @Override
            public void done(User user, BmobException e) {
                if(e == null){
                    nickname.setText(user.getNickname());
                }else{
                    Toast.makeText(getActivity(), "fetch user data failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void refresh() {
        BmobQuery<Post> postBmobQuery = new BmobQuery<>();

        postBmobQuery.order("-createdAt");
        postBmobQuery.setLimit(999);
        postBmobQuery.findObjects(new FindListener<Post>() {
            @Override
            public void done(List<Post> list, BmobException e) {
                swipeRefreshLayout.setRefreshing(false);
                if(e == null){
                    postList = list;
                    homeAdapter = new HomeAdapter(getActivity(), postList);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                    recyclerView.setAdapter(homeAdapter);
                }else{
                    Toast.makeText(getActivity(), "fetch data failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initView() {
        recyclerView = getActivity().findViewById(R.id.home_recyclerView);
        swipeRefreshLayout = getActivity().findViewById(R.id.home_swipeRefreshLayout);
        nickname = getActivity().findViewById(R.id.home_nickname);
        //homeGreeting = getActivity().findViewById(R.id.homeGreeting);
    }
}
