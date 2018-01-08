package com.fun.guptas.littlequiz;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    // Member variables accessible in all the methods of the MainActivity:
    Button javaQuiz;
    Button cQuiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        javaQuiz = (Button) findViewById(R.id.button1);
        cQuiz = (Button) findViewById(R.id.button2);

        javaQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Java_Quiz.class);
                startActivity(intent);
                finish();
            }
        });


    }
}
