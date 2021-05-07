package com.trainer.courserunner;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.trainer.courserunner.course.activity.CourseConductorSketchBookActivity;

public class RunningActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_running);

        Button imageButton = (Button) findViewById((R.id.nornal_running_btn));
        imageButton.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), NormalRunningActivity.class);
            startActivity(intent);
        });

        Button imageButton1 = (Button) findViewById((R.id.project_running_btn));
        imageButton1.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), ProjectRunningActivity.class);
            startActivity(intent);
        });

        Button imageButton2 = (Button) findViewById((R.id.Sketchbook_running_btn));
        imageButton2.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), CourseConductorSketchBookActivity.class);
            intent.putExtra("CreateType","New");
            startActivity(intent);
        });
    }
}