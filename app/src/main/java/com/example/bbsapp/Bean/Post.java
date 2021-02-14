package com.example.bbsapp.Bean;

import cn.bmob.v3.BmobObject;

public class Post extends BmobObject {

    private User author;
    private String title;
    private String content;
    private String nickname;

    public Community getBelongsTo() {
        return belongsTo;
    }

    public void setBelongsTo(Community belongsTo) {
        this.belongsTo = belongsTo;
    }

    private Community belongsTo;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;


    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
