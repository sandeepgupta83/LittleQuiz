package com.fun.guptas.littlequiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IntegerRes;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * Created by guptas on 12/18/2017.
 */

public class Java_Quiz extends AppCompatActivity {
    TextView setque;
    RadioGroup rg;
    Button back, next, finish;
    RadioButton rb1, rb2, rb3, rb4;


    int qCounter = 0;
    int index = 0;
    Random randomNumberGenerator = new Random();
    int maximum = 17;
    int minimum = 9;
    int range = maximum - minimum + 1;
    int number = randomNumberGenerator.nextInt(range) + minimum;

    String MyCAns, UserCAns;
    int totalCurrent, totalQues = 4, totalSkeep, totalWrong;
    ArrayList<HashMap<String, String>> MyArrList = new ArrayList<HashMap<String, String>>();
    HashMap<String, String> map;


    //Load Stings XML data
    ArrayList<LoadData> mDataArrayList = new ArrayList<LoadData>();

    void t(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.java_quiz);

        setque = (TextView) findViewById(R.id.textView1);
        rg = (RadioGroup) findViewById(R.id.radioGroup1);
        back = (Button) findViewById(R.id.button1);
        next = (Button) findViewById(R.id.button2);
        finish = (Button) findViewById(R.id.button3);

        rb1 = (RadioButton) findViewById(R.id.radio0);
        rb2 = (RadioButton) findViewById(R.id.radio1);
        rb3 = (RadioButton) findViewById(R.id.radio2);
        rb4 = (RadioButton) findViewById(R.id.radio3);
        rb1.setChecked(false);
        rb2.setChecked(false);
        rb3.setChecked(false);
        rb4.setChecked(false);

        back.setVisibility(View.GONE);
        finish.setVisibility(View.GONE);
        set_Your_Ques();
        set_Quest_One(number);

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = (RadioButton) findViewById(checkedId);
                Log.d("LQUIZ", "Checked id =" + rb);
                UserCAns = rb.getText().toString().trim();
//                if(rb.get)
//                rb.setChecked(true);
//                t("LQUIZ : " + "Your answer is " + UserCAns);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (UserCAns.equals("")) {
                    totalSkeep++;
                } else if (UserCAns.equals(MyCAns)) {
                    totalCurrent++;
                } else {
                    totalWrong++;
                }
                UserCAns = "";
                rb1.setChecked(false);
                rb2.setChecked(false);
                rb3.setChecked(false);
                rb4.setChecked(false);
                back();
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back.setVisibility(View.VISIBLE);
                Log.d("LQUIZ" , "UserCAns="+ UserCAns + "MyCAns="+ MyCAns);
                if (UserCAns.equals("")) {
                    totalSkeep++;
                    Log.d("LQUIZ" , "if UserCAns="+ UserCAns + "MyCAns="+ MyCAns);
                } else if (UserCAns.equals(MyCAns)) {
                    totalCurrent++;
                    Log.d("LQUIZ" , "elseif UserCAns="+ UserCAns + "MyCAns="+ MyCAns);
                } else {
                    totalWrong++;
                    Log.d("LQUIZ" , "else UserCAns="+ UserCAns + "MyCAns="+ MyCAns);
                }
                UserCAns = "";
                rb1.setChecked(false);
                rb2.setChecked(true);
                rb3.setChecked(false);
                rb4.setChecked(false);
                next();
                //Log.d("LQUIZ", "totalSkeep= " + totalSkeep + "totalWrong= " + totalWrong + "totalCurrent= " + totalCurrent);
            }
        });

        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Java_Quiz.this, Finish_Quiz.class);
                intent.putExtra("totalquestion", "Total Ques : " + totalQues);
                intent.putExtra("totalcurrect", "Total Current : " + totalCurrent);
                intent.putExtra("totalSkeep", "Total Skeep : " + totalSkeep);
                intent.putExtra("totalWrong", "Total Wrong : " + totalWrong);
                startActivity(intent);
                finish();
            }
        });

    }

    public void back() {
        if (qCounter == 0) {
            back.setVisibility(View.GONE);
            finish.setVisibility(View.GONE);
        } else {
            index--;
            qCounter--;
            int indexTwo = 0, indexThree = 0, indexFour = 0;

            if (index >= 0 && index <= totalQues) {
                indexTwo = index + 1;
                indexThree = indexTwo + 1;
                indexFour = totalQues - indexThree + 1;
            }
            else if (index < 0) {
                index = 0;
                indexTwo = index + 1;
                indexThree = indexTwo + 1;
                indexFour = indexThree + 1;
            }
            else {
                indexTwo = index + 1;
                indexThree = indexTwo + 1;
                if (totalQues < index) {
                    indexFour = indexThree + 1;
                }
                else {
                    indexFour = totalQues - indexThree + 1;
                }
            }

            setque.setText(mDataArrayList.get(index).getQuestion().toString().trim());
            rb1.setText(mDataArrayList.get(index).getAnswer().toString().trim());
            rb2.setText(mDataArrayList.get(indexTwo).getAnswer().toString().trim());
            rb3.setText(mDataArrayList.get(indexThree).getAnswer().toString().trim());
            rb4.setText(mDataArrayList.get(indexFour).getAnswer().toString().trim());
            MyCAns = mDataArrayList.get(index).getAnswer().toString().trim();
        }
    }

    public void next() {
        if (qCounter == 3) {
            next.setVisibility(View.GONE);
            back.setVisibility(View.GONE);
            finish.setVisibility(View.VISIBLE);
        } else {
            index++;
            qCounter++;
            Log.d("LQUIZ", "NextIndex=" + index);
            int indexTwo = 0, indexThree = 0, indexFour = 0;

            if (index >= 0 && index <= totalQues) {
                indexTwo = index + 1;
                indexThree = indexTwo + 1;
                indexFour = totalQues - indexThree + 1;
            }
            else if (index < 0) {
                index = 0;
                indexTwo = index + 1;
                indexThree = indexTwo + 1;
                indexFour = indexThree + 1;
            }
            else {
//                indexTwo = index + 1;
//                indexThree = indexTwo + 1;
//                indexFour = totalQues - indexThree + 1;
                indexTwo = index + 1;
                indexThree = indexTwo + 1;
                if (totalQues < index) {
                    indexFour = indexThree + 1;
                }
                else {
                    indexFour = totalQues - indexThree + 1;
                }
            }

            setque.setText(mDataArrayList.get(index).getQuestion().toString().trim());
            rb1.setText(mDataArrayList.get(index).getAnswer().toString().trim());
            rb2.setText(mDataArrayList.get(indexTwo).getAnswer().toString().trim());
            rb3.setText(mDataArrayList.get(indexThree).getAnswer().toString().trim());
            rb4.setText(mDataArrayList.get(indexFour).getAnswer().toString().trim());
            MyCAns = mDataArrayList.get(index).getAnswer().toString().trim();
        }
    }

    public void set_Quest_One(int number) {
        index = this.number;
        int indexTwo = 0, indexThree = 0, indexFour = 0;

        if (index >= 0 && index <= totalQues) {
            indexTwo = index + 1;
            indexThree = indexTwo + 1;
            indexFour = totalQues - indexThree + 1;
        }
        else if (index < 0) {
            index = 0;
            indexTwo = index + 1;
            indexThree = indexTwo + 1;
            indexFour = indexThree + 1;
        }
        else {
            indexTwo = index + 1;
            indexThree = indexTwo + 1;
            if (totalQues < index) {
                indexFour = indexThree + 1;
            }
            else {
                indexFour = totalQues - indexThree + 1;
            }
        }
        Log.d("LQUIZ",index +" " + indexTwo + " " + indexThree + " " + indexFour);

        setque.setText(mDataArrayList.get(index).getQuestion().toString().trim());
        rb1.setText(mDataArrayList.get(index).getAnswer().toString().trim());
        rb2.setText(mDataArrayList.get(indexTwo).getAnswer().toString().trim());
        rb3.setText(mDataArrayList.get(indexThree).getAnswer().toString().trim());
        rb4.setText(mDataArrayList.get(indexFour).getAnswer().toString().trim());
        MyCAns = mDataArrayList.get(index).getAnswer().toString().trim();

        Log.d("LQUIZ", "SIZE" + String.valueOf(mDataArrayList.size()));
        Log.d("LQUIZ", "Question Value " + mDataArrayList.get(index).getQuestion());
        Log.d("LQUIZ", "Answer Value " + mDataArrayList.get(index).getAnswer());
        Log.d("LQUIZ", "Answer Value " + mDataArrayList.get(indexTwo).getAnswer());
        Log.d("LQUIZ", "Answer Value " + mDataArrayList.get(indexThree).getAnswer());
        Log.d("LQUIZ", "Answer Value " + mDataArrayList.get(indexFour).getAnswer());
        Log.d("LQUIZ", "Hint Value " + mDataArrayList.get(1).getHint());
        Log.d("LQUIZ", "Wiki Value " + mDataArrayList.get(1).getWiki());
    }


    public void set_Your_Ques() {
        int mqLength = getResources().getStringArray(R.array.questions).length;

        Log.d("LQUIZ", "LENGTH SIZE" + mqLength);

        for (int i = 0; i < mqLength; i++) {
            mDataArrayList.add(new LoadData(
                    (getResources().getStringArray(R.array.questions))[i],
                    (getResources().getStringArray(R.array.answers))[i],
                    (getResources().getStringArray(R.array.hints))[i],
                    (getResources().getStringArray(R.array.wikes))[i]
            ));
        }
    }
}