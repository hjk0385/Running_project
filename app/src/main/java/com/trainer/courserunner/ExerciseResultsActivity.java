package com.trainer.courserunner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ExerciseResultsActivity extends AppCompatActivity {
    TextView v1; //총 운동 거리
    TextView v2; //총 운동 시간
    TextView v3; //총 소비 칼로리

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_results);

        v1=(TextView) findViewById(R.id.input_calorie);
        v2=(TextView) findViewById(R.id.input_distance);
        v3=(TextView) findViewById(R.id.input_time);
    }

}