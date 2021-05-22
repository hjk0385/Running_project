package com.trainer.courserunner.settingrun.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.trainer.courserunner.R;
import com.trainer.courserunner.record.ProjectRecordActivity;
import com.trainer.courserunner.settingrun.RunningSetting;
import com.trainer.courserunner.settingrun.normal.NormalImageSelectionActivity;

public class ProjectRunningActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_running);

        Button btnNewProject=findViewById(R.id.new_project);
        btnNewProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ProjectImageSelectionActivity.class);
                intent.putExtra("runningSetting",new RunningSetting());
                startActivity(intent);
            }
        });

        Button btnResumeProject=findViewById(R.id.project);
        btnResumeProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ProjectRecordActivity.class);
                startActivity(intent);
            }
        });

    }
}