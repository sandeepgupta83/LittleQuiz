package com.fun.guptas.littlequiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by guptas on 12/18/2017.
 */

public class Finish_Quiz extends AppCompatActivity {
    TextView t1,t2,t3,t4;
    Button b1;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.finish_quiz);

        t1=(TextView)findViewById(R.id.textView1);
        t2=(TextView)findViewById(R.id.textView2);
        t3=(TextView)findViewById(R.id.textView3);
        t4=(TextView)findViewById(R.id.textView4);
        b1=(Button)findViewById(R.id.button1);
        t1.setText(getIntent().getExtras().getString("totalquestion"));
        t2.setText(getIntent().getExtras().getString("totalcurrect"));
        t3.setText(getIntent().getExtras().getString("totalSkeep"));
        t4.setText(getIntent().getExtras().getString("totalWrong"));
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Finish_Quiz.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
