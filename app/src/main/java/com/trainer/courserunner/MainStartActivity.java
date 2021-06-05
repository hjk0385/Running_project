package com.trainer.courserunner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.trainer.courserunner.runactivity.record.RecordListActivity;
import com.trainer.courserunner.runactivity.set.RunningSetting;

public class MainStartActivity extends AppCompatActivity {
    private Button mBtnLoginLogout;
    private Button mBtnNewStart;
    private Button mBtnUserLog;
    private Button mBtnLoad;
    private Button mBtnSetting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_start);

        mBtnLoginLogout=findViewById(R.id.btn_loginlogout);
        mBtnNewStart=findViewById(R.id.btn_newstart);
        mBtnLoad=findViewById(R.id.btn_load);
        mBtnUserLog=findViewById(R.id.btn_userlog);
        mBtnSetting=findViewById(R.id.btn_setting);

        mBtnLoginLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
            }
        });

        mBtnUserLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), RecordListActivity.class));
            }
        });

        mBtnNewStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });

        mBtnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), RunningSetting.class));
            }
        });

    }
}