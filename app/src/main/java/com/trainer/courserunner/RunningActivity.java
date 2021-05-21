package com.trainer.courserunner;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.trainer.courserunner.Application.enumtype.StartType;
import com.trainer.courserunner.ProjectRunningActivity;
import com.trainer.courserunner.R;
import com.trainer.courserunner.course.activity.SketchBookRunnerActivity;
import com.trainer.courserunner.settingrun.DistanceActivity;
import com.trainer.courserunner.settingrun.ImageSelectionActivity;
import com.trainer.courserunner.settingrun.RunningSetting;

public class RunningActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_running);

        Button imageButton = (Button) findViewById((R.id.nornal_running_btn));
        imageButton.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), ImageSelectionActivity.class);
            intent.putExtra("runningSetting",new RunningSetting());
            startActivity(intent);
        });

        Button imageButton1 = (Button) findViewById((R.id.project_running_btn));
        imageButton1.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), ProjectRunningActivity.class);
            startActivity(intent);
        });

        Button imageButton2 = (Button) findViewById((R.id.Sketchbook_running_btn));
        imageButton2.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), SketchBookRunnerActivity.class);
            intent.putExtra("startType", StartType.NEW);
            startActivity(intent);
        });
    }
}