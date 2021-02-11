package com.example.bbsapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.bbsapp.Bean.User;
import com.example.bbsapp.R;
import com.example.bbsapp.activity.Login;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;

public class FragmentProfile extends Fragment {

    private TextView nickname, username;
    private Button logout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();

        getUserInfo();

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User.logOut();
                startActivity(new Intent(getActivity(), Login.class));
                getActivity().finish();
            }
        });
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
                    username.setText(user.getUsername());
                }else{
                    Toast.makeText(getActivity(), "fetch user data failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initView() {
        nickname = getActivity().findViewById(R.id.nickname);
        username = getActivity().findViewById(R.id.username);
        logout = getActivity().findViewById(R.id.logout);
    }
}
