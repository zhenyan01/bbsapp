package com.example.bbsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.example.bbsapp.Adapter.SectionsAdapter;
import com.example.bbsapp.fragment.FragmentBBS;
import com.example.bbsapp.fragment.FragmentHome;
import com.example.bbsapp.fragment.FragmentProfile;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener, ViewPager.OnPageChangeListener {
    //comment
    //private TextView username, nickname;
    private ViewPager viewPager;
    private BottomNavigationBar bottomNavigationBar;
    private List<Fragment> fragmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.viewPager);
        bottomNavigationBar = findViewById(R.id.bottomNavigationBar);

        initView();

        /*username = findViewById(R.id.username);
        nickname = findViewById(R.id.nickname);

        User user = User.getCurrentUser(User.class);
        String id = user.getObjectId();
        BmobQuery<User> myUser = new BmobQuery<>();
        myUser.getObject(id, new QueryListener<User>() {
            @Override
            public void done(User user, BmobException e) {
                if(e == null){
                    username.setText(user.getUsername());
                    nickname.setText(user.getNickname());
                }else{
                    Toast.makeText(MainActivity.this, "query user fail", Toast.LENGTH_SHORT).show();
                }
            }
        });*/
    }

    private void initView() {
        initViewPager();
        initBottomNavigationBar();
    }

    private void initBottomNavigationBar() {
        bottomNavigationBar.setTabSelectedListener(this);
        bottomNavigationBar.clearAll();
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        bottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_DEFAULT);
        bottomNavigationBar.setBarBackgroundColor(R.color.white)
                           .setActiveColor(R.color.myDeepGreen)
                           .setInActiveColor(R.color.black);
        bottomNavigationBar.addItem(new BottomNavigationItem(R.drawable.homepage_fill, "Home").setInactiveIconResource(R.drawable.homepage))
                           .addItem(new BottomNavigationItem(R.drawable.interactive_fill, "BBS").setInactiveIconResource(R.drawable.interactive))
                           .addItem(new BottomNavigationItem(R.drawable.people_fill, "Profile").setInactiveIconResource(R.drawable.people))
                           .setFirstSelectedPosition(0).initialise();

    }

    private void initViewPager() {
        viewPager.setOffscreenPageLimit(3);

        fragmentList = new ArrayList<>();
        fragmentList.add(new FragmentHome());
        fragmentList.add(new FragmentBBS());
        fragmentList.add(new FragmentProfile());

        viewPager.setAdapter(new SectionsAdapter(getSupportFragmentManager(), fragmentList));
        viewPager.addOnPageChangeListener(this);
        viewPager.setCurrentItem(0);
    }

    @Override
    public void onTabSelected(int position) {
        viewPager.setCurrentItem(position);
    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        bottomNavigationBar.selectTab(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}