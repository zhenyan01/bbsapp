package com.example.bbsapp.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bbsapp.Bean.Community;
import com.example.bbsapp.Bean.User;
import com.example.bbsapp.R;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class CreateCommunity extends AppCompatActivity {

    private EditText c_name, c_info;
    private ImageView back;
    private Button c_create;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitvity_create_community);
        initView();

        c_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(c_name.getText().toString().isEmpty()){
                    Toast.makeText(CreateCommunity.this, "Community name cannot be empty", Toast.LENGTH_SHORT).show();
                }else{
                    User user = User.getCurrentUser(User.class);
                    Community community = new Community();
                    community.setName(c_name.getText().toString());
                    community.setInfo(c_info.getText().toString());
                    community.setOwner(user);
                    community.setOwnerNickname(user.getNickname());
                    community.save(new SaveListener<String>() {
                        @Override
                        public void done(String s, BmobException e) {
                            if(e == null){
                                c_name.setText("");
                                c_info.setText("");
                                Toast.makeText(CreateCommunity.this, "Create community success", Toast.LENGTH_SHORT).show();
                                finish();
                            }else{
                                Toast.makeText(CreateCommunity.this, "Create community failed", Toast.LENGTH_SHORT).show();
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
        c_name = findViewById(R.id.acc_community_name);
        c_info = findViewById(R.id.acc_community_info);
        back = findViewById(R.id.acc_back);
        c_create = findViewById(R.id.acc_create_button);
    }
}
