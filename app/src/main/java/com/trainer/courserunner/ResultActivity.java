package com.trainer.courserunner;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {
    private TextView textViewMonthCalorie;
    private TextView textViewMonthDistance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        textViewMonthCalorie=findViewById(R.id.textView_month_calorie);
        textViewMonthDistance=findViewById(R.id.textView_month_distance);



    }
}