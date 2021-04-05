package com.example.bbsapp.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.bbsapp.MainActivity;
import com.example.bbsapp.R;

import java.util.ArrayList;

public class Questionnarie extends AppCompatActivity {
    LocationManager locationManager;
    LocationListener locationListener;

    TextView ques_view;
    TextView optionA;
    TextView optionB;
    TextView position;

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
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,locationListener);
                }
            }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("info", "enter the Questionnarie_activity");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionnaire);

        ques_view = findViewById(R.id.questionText);
        optionA = findViewById(R.id.answerA);
        optionB = findViewById(R.id.answerB);
        position = findViewById(R.id.location);
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
        // GPS using
        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                Log.i("Location", location.toString());
                position.setText(location.toString());
            }
        };
        // Permission verifying
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);
        }else{
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,locationListener);
        }


    }

    public void checkOptionA(){
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

    public void checkOptionB(){
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

    public void start(){
        //Log.i("I/info", "I'm passed the start!111");
        init();
        //Log.i("I/info", "I'm passed the start!");
        goButton.setVisibility(View.INVISIBLE);
        // hide other widgets for a while
        ques_view.setVisibility(View.VISIBLE);
        optionA.setVisibility(View.VISIBLE);
        optionB.setVisibility(View.VISIBLE);

        //int i =0;

        ques_view.setText(questions.get(0));

        optionA.setText(answerA.get(0));
        optionB.setText(answerB.get(0));

        A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkOptionA();
                index++;
                question2();
            }

        });

        B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkOptionB();
                index++;
                question2();
            }
        });

//            Log.i("I/info", "I'm in the loop");
//            ques_view.setText(questions.get(index));
//
//            optionA.setText(answerA.get(index));
//            optionB.setText(answerB.get(index));


    }
    public void question2(){
        ques_view.setText(questions.get(1));
        optionA.setText(answerA.get(1));
        optionB.setText(answerB.get(1));
        A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkOptionA();
                index++;
                question3();
            }

        });

        B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkOptionB();
                index++;
                question3();
            }
        });


    }

    public void question3(){
        ques_view.setText(questions.get(2));
        optionA.setText(answerA.get(2));
        optionB.setText(answerB.get(2));

        A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkOptionA();
                index++;
                question4();
            }

        });

        B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkOptionB();
                index++;
                question4();
            }
        });

    }

    public void question4(){
        ques_view.setText(questions.get(3));
        optionA.setText(answerA.get(3));
        optionB.setText(answerB.get(3));

        A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkOptionA();
                index++;
                question5();
            }

        });

        B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkOptionB();
                index++;
                question5();
            }
        });

    }
    public void question5(){
        ques_view.setText(questions.get(4));
        optionA.setText(answerA.get(4));
        optionB.setText(answerB.get(4));

        A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkOptionA();
                index++;
                question6();
            }

        });

        B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkOptionB();
                index++;
                question6();
            }
        });

    }
    public void question6(){
        ques_view.setText(questions.get(5));
        optionA.setText(answerA.get(5));
        optionB.setText(answerB.get(5));

        A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkOptionA();
                index++;
                question7();
            }

        });

        B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkOptionB();
                index++;
                question7();
            }
        });

    }
    public void question7(){
        ques_view.setText(questions.get(6));
        optionA.setText(answerA.get(6));
        optionB.setText(answerB.get(6));

        A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkOptionA();
                index++;
                question8();
            }

        });

        B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkOptionB();
                index++;
                question8();
            }
        });

    }
    public void question8(){
        ques_view.setText(questions.get(7));
        optionA.setText(answerA.get(7));
        optionB.setText(answerB.get(7));

        A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkOptionA();
                index++;
                question9();
            }

        });

        B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkOptionB();
                index++;
                question9();
            }
        });

    }
    public void question9(){
        ques_view.setText(questions.get(8));
        optionA.setText(answerA.get(8));
        optionB.setText(answerB.get(8));

        A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkOptionA();
                index++;
                question10();
            }

        });

        B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkOptionB();
                index++;
                question10();
            }
        });

    }
    public void question10(){
        ques_view.setText(questions.get(9));
        optionA.setText(answerA.get(9));
        optionB.setText(answerB.get(9));

        A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkOptionA();
                index++;
                question11();
            }

        });

        B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkOptionB();
                index++;
                question11();
            }
        });

    }
    public void question11(){
        ques_view.setText(questions.get(10));
        optionA.setText(answerA.get(10));
        optionB.setText(answerB.get(10));

        A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkOptionA();
                index++;
                question12();
            }

        });

        B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkOptionB();
                index++;
                question12();
            }
        });

    }
    public void question12(){
        ques_view.setText(questions.get(11));
        optionA.setText(answerA.get(11));
        optionB.setText(answerB.get(11));

        A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkOptionA();
                index++;
                question13();
            }

        });

        B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkOptionB();
                index++;
                question13();
            }
        });

    }
    public void question13(){
        ques_view.setText(questions.get(12));
        optionA.setText(answerA.get(12));
        optionB.setText(answerB.get(12));

        A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkOptionA();
                index++;
                question14();
            }

        });

        B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkOptionB();
                index++;
                question14();
            }
        });

    }
    public void question14(){
        ques_view.setText(questions.get(13));
        optionA.setText(answerA.get(13));
        optionB.setText(answerB.get(13));

        A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkOptionA();
                index++;
                question15();
            }

        });

        B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkOptionB();
                index++;
                question15();
            }
        });

    }
    public void question15(){
        ques_view.setText(questions.get(14));
        optionA.setText(answerA.get(14));
        optionB.setText(answerB.get(14));

        A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkOptionA();
                index++;
                question16();
            }

        });

        B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkOptionB();
                index++;
                question16();
            }
        });

    }
    public void question16(){
        ques_view.setText(questions.get(15));
        optionA.setText(answerA.get(15));
        optionB.setText(answerB.get(15));

        A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkOptionA();
                index++;
                question17();
            }

        });

        B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkOptionB();
                index++;
                question17();
            }
        });

    }
    public void question17(){
        ques_view.setText(questions.get(16));
        optionA.setText(answerA.get(16));
        optionB.setText(answerB.get(16));

        A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkOptionA();
                index++;
                question18();
            }

        });

        B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkOptionB();
                index++;
                question18();
            }
        });

    }
    public void question18(){
        ques_view.setText(questions.get(17));
        optionA.setText(answerA.get(17));
        optionB.setText(answerB.get(17));

        A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkOptionA();
                index++;
                question19();
            }

        });

        B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkOptionB();
                index++;
                question19();
            }
        });

    }

    public void question19(){
        ques_view.setText(questions.get(18));
        optionA.setText(answerA.get(18));
        optionB.setText(answerB.get(18));

        A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkOptionA();
                index++;
                question20();
            }

        });

        B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkOptionB();
                index++;
                question20();
            }
        });

    }

    public void question20(){
        ques_view.setText(questions.get(19));
        optionA.setText(answerA.get(19));
        optionB.setText(answerB.get(19));

        A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkOptionA();
                index++;
                result();
            }

        });

        B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkOptionB();
                index++;
                result();
            }
        });

    }




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
        // set start
        goButton.setText("Your type is: " + result);
        goButton.setVisibility(View.VISIBLE);
        // Set a timer for user to check the result
        new CountDownTimer(5000,1000){
            public void onTick(long millisecondUntilDone){
                Log.i("Seconds Left!", String.valueOf(millisecondUntilDone/1000));
            }

            public void onFinish(){
                Log.i("Info","Terminated!");
            }
        }.start();
        startActivity(new Intent(Questionnarie.this, Chatting.class));
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
