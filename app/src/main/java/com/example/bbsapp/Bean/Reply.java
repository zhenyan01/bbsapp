package com.example.bbsapp.Bean;

import cn.bmob.v3.BmobObject;

public class Reply extends BmobObject {
    public User getReplyUser() {
        return replyUser;
    }

    public void setReplyUser(User replyUser) {
        this.replyUser = replyUser;
    }

    private User replyUser;
    private String nickname;

    public Post getBelongsTo() {
        return belongsTo;
    }

    public void setBelongsTo(Post belongsTo) {
        this.belongsTo = belongsTo;
    }

    private Post belongsTo;

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

    private String content;
}
