package com.example.bbsapp.Bean;

import cn.bmob.v3.BmobObject;

public class Reply extends BmobObject {
    String nickname;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    String content;
}
