package com.example.bbsapp.Bean;

import cn.bmob.v3.BmobUser;

public class User extends BmobUser {

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    private String nickname;

}
