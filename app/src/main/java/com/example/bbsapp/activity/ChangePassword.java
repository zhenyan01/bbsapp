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

import com.example.bbsapp.Bean.User;
import com.example.bbsapp.MainActivity;
import com.example.bbsapp.R;
import com.google.android.material.snackbar.Snackbar;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

public class ChangePassword extends AppCompatActivity {

    private EditText oldPassword, newPassword, confirmPassword;
    private Button changePassword;
    private ImageView back;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        initView();

        User currentUser = User.getCurrentUser(User.class);

        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String oldPwd = oldPassword.getText().toString().trim();
                String newPwd = newPassword.getText().toString().trim();
                String confirm = confirmPassword.getText().toString().trim();
                if(newPwd.equals(confirm)) {

                    currentUser.updateCurrentUserPassword(oldPwd, newPwd, new UpdateListener() {
                        @Override
                        public void done(BmobException e) {
                            if (e == null) {
                                Toast.makeText(ChangePassword.this, "success", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(ChangePassword.this, MainActivity.class));
                            } else {
                                Toast.makeText(ChangePassword.this, "update failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }else{
                    Toast.makeText(ChangePassword.this, "confirm your new password again", Toast.LENGTH_SHORT).show();
                }
            }
        });



        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ChangePassword.this, MainActivity.class));
            }
        });
    }

    private void initView() {
        oldPassword = findViewById(R.id.acp1_password);
        newPassword = findViewById(R.id.acp1_new_password);
        confirmPassword = findViewById(R.id.acp1_confirm);
        changePassword = findViewById(R.id.acp1_change_password);
        back = findViewById(R.id.acp1_back);
    }
}
