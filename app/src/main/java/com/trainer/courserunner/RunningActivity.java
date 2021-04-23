package com.trainer.courserunner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

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
            Intent intent = new Intent(getApplicationContext(), MissionActivity.class);
            startActivity(intent);
        });
    }
}