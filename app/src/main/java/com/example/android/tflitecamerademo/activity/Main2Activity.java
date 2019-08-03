package com.example.android.tflitecamerademo.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;

import com.example.android.tflitecamerademo.R;

public class Main2Activity extends Activity {
CardView exercise,timenoti;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        exercise = (CardView)findViewById(R.id.exercise);
        timenoti = (CardView)findViewById(R.id.timenoti);

        exercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main2Activity.this,CameraActivity.class);
                startActivity(intent);
            }
        });
        

    }



}
