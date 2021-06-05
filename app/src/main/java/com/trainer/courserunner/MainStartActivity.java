package com.trainer.courserunner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.trainer.courserunner.runactivity.record.RecordListActivity;
import com.trainer.courserunner.runactivity.set.RunningActivity;
import com.trainer.courserunner.runactivity.set.RunningProjectRecordActivity;
import com.trainer.courserunner.runactivity.set.RunningSetting;

public class MainStartActivity extends AppCompatActivity {
    private Button mBtnLoginLogout;
    private Button mBtnNewStart;
    private Button mBtnUserLog;
    private Button mBtnLoad;
    private Button mBtnExerciseInfo;
    private Button mBtnMission;
    private Boolean isLogin;

    private int REQUEST_LOGIN=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_start);

        isLogin=false;

        mBtnLoginLogout=findViewById(R.id.btn_loginlogout);
        mBtnNewStart=findViewById(R.id.btn_newstart);
        mBtnLoad=findViewById(R.id.btn_load);
        mBtnUserLog=findViewById(R.id.btn_userlog);
        mBtnExerciseInfo=findViewById(R.id.btn_exersicecal);
        mBtnMission=findViewById(R.id.btn_mission);

        mBtnLoginLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isLogin) {
                    Intent intent=new Intent(MainStartActivity.this,LoginActivity.class);
                    startActivityForResult(intent,REQUEST_LOGIN);
                }
                else{
                    isLogin=false;
                }
                updateLoginLogoutText();
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
                startActivity(new Intent(getApplicationContext(), RunningActivity.class));
            }
        });

        mBtnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), RunningProjectRecordActivity.class));
            }
        });

        mBtnExerciseInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),ResultActivity.class));
            }
        });
        mBtnMission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),MissionActivity.class));
            }
        });
        updateLoginLogoutText();
    }

    void updateLoginLogoutText(){
        if(isLogin==true){
            mBtnLoginLogout.setText("로그아웃");
        }
        else{
            mBtnLoginLogout.setText("로그인");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQUEST_LOGIN){
            if(resultCode==RESULT_OK) {
                isLogin = true;
            }
        }
        updateLoginLogoutText();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_mainstart, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.menu_noticesite:
                Intent home3Intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://cafe.naver.com/drawingrunners"));
                startActivity(home3Intent);
                return true;
            case R.id.menu_setting: //설정 이동
                Intent homeIntent2 = new Intent(this, SettingActivity.class);
                startActivity(homeIntent2);
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

}