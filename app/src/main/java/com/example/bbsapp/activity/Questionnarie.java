package com.example.bbsapp.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bbsapp.MainActivity;
import com.example.bbsapp.R;

import java.util.ArrayList;

public class Questionnarie extends AppCompatActivity {
    TextView ques_view;
    TextView optionA;
    TextView optionB;

    ArrayList<String> answerA = new ArrayList<>();
    ArrayList<String> answerB = new ArrayList<>();
    ArrayList<String> questions = new ArrayList<>();

    Button A;
    Button B;
    Button goButton;

    String result = "";

    int EORI = 0;
    int SORN = 0;
    int TORF = 0;
    int JORP = 0;
    int index = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("info", "enter the Questionnarie_activity");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionnaire);

        ques_view = findViewById(R.id.questionText);
        optionA = findViewById(R.id.answerA);
        optionB = findViewById(R.id.answerB);
        A = findViewById(R.id.buttonA);
        B = findViewById(R.id.buttonB);
        goButton = findViewById(R.id.goButton);

        // set start
        goButton.setVisibility(View.VISIBLE);
        // hide other widgets for a while
        ques_view.setVisibility(View.INVISIBLE);
        optionA.setVisibility(View.INVISIBLE);
        optionB.setVisibility(View.INVISIBLE);

        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start();
            }
        });
        Log.i("I/info", "tail of the activity");
    }

    public void start(){
        Log.i("I/info", "I'm passed the start!111");
        init();
        Log.i("I/info", "I'm passed the start!");
        goButton.setVisibility(View.INVISIBLE);
        // hide other widgets for a while
        ques_view.setVisibility(View.VISIBLE);
        optionA.setVisibility(View.VISIBLE);
        optionB.setVisibility(View.VISIBLE);

        for(int i=0;i<19;i++) {
            Log.i("I/info", "I'm in the loop");
            ques_view.setText(questions.get(i));
            optionA.setText(answerA.get(i));
            optionB.setText(answerB.get(i));


                index = i;
                A.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (index == 0 || index == 4 || index == 8 || index == 12 || index == 16) {
                            EORI++;

                        } else if (index == 1 || index == 5 || index == 9 || index == 13 || index == 17) {
                            SORN++;
                        } else if (index == 2 || index == 6 || index == 10 || index == 14 || index == 18) {
                            TORF++;
                        } else if (index == 3 || index == 7 || index == 11 || index == 15 || index == 19) {
                            JORP++;
                        }
                    }

                });

                B.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (index == 0 || index == 4 || index == 8 || index == 12 || index == 16) {
                            EORI--;
                        } else if (index == 1 || index == 5 || index == 9 || index == 13 || index == 17) {
                            SORN--;
                        } else if (index == 2 || index == 6 || index == 10 || index == 14 || index == 18) {
                            TORF--;
                        } else if (index == 3 || index == 7 || index == 11 || index == 15 || index == 19) {
                            JORP--;
                        }
                    }
                });

                //question2();
            }
        result();

    }
//    public void question2(){
//        ques_view.setText(questions.get(1));
//        optionA.setText(answerA.get(1));
//        optionB.setText(answerB.get(1));
//
//        A.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(index == 0 || index ==4 || index == 8 || index == 12 || index == 16){
//                    EORI++;
//
//                }else if(index == 1 || index ==5 || index == 9 || index == 13 || index == 17){
//                    SORN++;
//                }else if(index == 2 || index ==6 || index == 10 || index == 14 || index == 18){
//                    TORF++;
//                }else if(index == 3 || index ==7 || index == 11 || index == 15 || index == 19){
//                    JORP++;
//                }
//            }
//
//        });
//
//        B.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(index == 0 || index ==4 || index == 8 || index == 12 || index == 16){
//                    EORI--;
//                }else if(index == 1 || index ==5 || index == 9 || index == 13 || index == 17){
//                    SORN--;
//                }else if(index == 2 || index ==6 || index == 10 || index == 14 || index == 18){
//                    TORF--;
//                }else if(index == 3 || index ==7 || index == 11 || index == 15 || index == 19){
//                    JORP--;
//                }
//            }
//        });
//        index++;
//        question3();
//    }
//
//    public void question3(){
//        ques_view.setText(questions.get(2));
//        optionA.setText(answerA.get(2));
//        optionB.setText(answerB.get(2));
//
//        A.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(index == 0 || index ==4 || index == 8 || index == 12 || index == 16){
//                    EORI++;
//
//                }else if(index == 1 || index ==5 || index == 9 || index == 13 || index == 17){
//                    SORN++;
//                }else if(index == 2 || index ==6 || index == 10 || index == 14 || index == 18){
//                    TORF++;
//                }else if(index == 3 || index ==7 || index == 11 || index == 15 || index == 19){
//                    JORP++;
//                }
//            }
//
//        });
//
//        B.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(index == 0 || index ==4 || index == 8 || index == 12 || index == 16){
//                    EORI--;
//                }else if(index == 1 || index ==5 || index == 9 || index == 13 || index == 17){
//                    SORN--;
//                }else if(index == 2 || index ==6 || index == 10 || index == 14 || index == 18){
//                    TORF--;
//                }else if(index == 3 || index ==7 || index == 11 || index == 15 || index == 19){
//                    JORP--;
//                }
//            }
//        });
//        index++;
//        question4();
//    }
//
//    public void question4(){
//        ques_view.setText(questions.get(3));
//        optionA.setText(answerA.get(3));
//        optionB.setText(answerB.get(3));
//
//        A.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(index == 0 || index ==4 || index == 8 || index == 12 || index == 16){
//                    EORI++;
//
//                }else if(index == 1 || index ==5 || index == 9 || index == 13 || index == 17){
//                    SORN++;
//                }else if(index == 2 || index ==6 || index == 10 || index == 14 || index == 18){
//                    TORF++;
//                }else if(index == 3 || index ==7 || index == 11 || index == 15 || index == 19){
//                    JORP++;
//                }
//            }
//
//        });
//
//        B.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(index == 0 || index ==4 || index == 8 || index == 12 || index == 16){
//                    EORI--;
//                }else if(index == 1 || index ==5 || index == 9 || index == 13 || index == 17){
//                    SORN--;
//                }else if(index == 2 || index ==6 || index == 10 || index == 14 || index == 18){
//                    TORF--;
//                }else if(index == 3 || index ==7 || index == 11 || index == 15 || index == 19){
//                    JORP--;
//                }
//            }
//        });
//        index++;
//        result();
//    }



    public void result(){
        if(EORI >0){
            result += "E";
        }else{
            result += "I";
        }

        if(SORN >0){
            result += "S";
        }else{
            result += "N";
        }

        if(TORF >0){
            result += "T";
        }else{
            result += "F";
        }

        if(JORP >0){
            result += "J";
        }else{
            result += "P";
        }

        Log.i("I/info","My testing result is: " + result);
        startActivity(new Intent(Questionnarie.this, Switch.class));
    }

    public void init(){
        // Questions loading...
        questions.add("1) When you are with a group of people, would you usually rather");
        questions.add("2) Do you usually get along better with");
        questions.add("3) Which word in the pair appeals to you more?");
        questions.add("4) Does following a schedule");
        questions.add("5) When you have to meet strangers, do you find it");
        questions.add("6) If you were a teacher, would you rather teach");
        questions.add("7) Which word in the pair appeals to you more?");
        questions.add("8) Do you prefer to");
        questions.add("9) Are you");
        questions.add("10) Is it higher praise to say someone has");
        questions.add("11) Which word in the pair appeals to you more?");
        questions.add("12) Does the idea of making a list of what you should get");
        questions.add("13) Do you tend to have");
        questions.add("14) Would you rather have as a friend someone who");
        questions.add("15) Which word in the pair appeals to you more?");
        questions.add("16) When it is settled well in advance that you will do a");
        questions.add("17) At parties, do you");
        questions.add("18) Would you rather be considered");
        questions.add("19) Is it a higher compliment to be called");
        questions.add("20) Is it harder for you to adapt to");
        // Answer A loading ...
        answerA.add("a) Join in the talk of the group, or");
        answerA.add("a) Realistic people, or");
        answerA.add("a) Analyze");
        answerA.add("a) Appeal to you, or");
        answerA.add("a) Pleasant, or at lease easy, or");
        answerA.add("a) Fact courses, or");
        answerA.add("a) Foresight");
        answerA.add("a) Arrange dates, parties, etc., well in advance, or");
        answerA.add("a) Easy to get to know, or");
        answerA.add("a) Common sense, or");
        answerA.add("a) Firm");
        answerA.add("a) Appeal to you, or");
        answerA.add("a) Broad friendships with many different people, or");
        answerA.add("a) Has both feet on the ground, or");
        answerA.add("a) Thinking");
        answerA.add("a) Nice to be able to plan accordingly, or");
        answerA.add("a) Always have fun, or");
        answerA.add("a) A practical person, or");
        answerA.add("a) A consistently reasonable person, or");
        answerA.add("a) Constant change, or");
        // Answer B loading ...
        answerB.add("b) Talk individually with people you know well?");
        answerB.add("b) Imaginative people?");
        answerB.add("b) Sympathize");
        answerB.add("b) Cramp you?");
        answerB.add("b) Something that takes a good deal of effort?");
        answerB.add("b) Courses involving theory?");
        answerB.add("b) Compassion");
        answerB.add("b) Be free to do whatever looks like fun when the time comes?");
        answerB.add("b) Hard to get to know?");
        answerB.add("b) Vision?");
        answerB.add("b) Gentle");
        answerB.add("b) Leave you cold");
        answerB.add("b) Deep friendships with a very few people?");
        answerB.add("b) Is always coming up with new ideas?");
        answerB.add("b) Feeling");
        answerB.add("b) A little unpleasant to be tied down?");
        answerB.add("b) Sometimes get bored?");
        answerB.add("b) An ingenious person?");
        answerB.add("b) A person of real feeling?");
        answerB.add("b) Routine?");

    }

}
