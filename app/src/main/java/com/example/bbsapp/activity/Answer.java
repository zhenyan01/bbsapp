package com.example.bbsapp.activity;

public class Answer {

    private String answerId;

    private String answer_content;

    private int ans_state;

    public int getAns_state() {
        return ans_state;
    }
    public void setAns_state(int ans_state) {
        this.ans_state = ans_state;
    }
    public String getAnswerId() {
        return answerId;
    }
    public void setAnswerId(String answerId) {
        this.answerId = answerId;
    }
    public String getAnswer_content() {
        return answer_content;
    }
    public void setAnswer_content(String answer_content) {
        this.answer_content = answer_content;
    }
}
