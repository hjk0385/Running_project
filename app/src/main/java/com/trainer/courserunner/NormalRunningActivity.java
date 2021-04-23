package com.trainer.courserunner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.trainer.courserunner.course.CourseGuideActivity;

public class NormalRunningActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal_running);

        //버튼 리스너 등록
        Button km2_btn=(Button) findViewById(R.id.km2_btn);
        km2_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //엑티비티 인텐드
                Intent intent =new Intent(getBaseContext(), CourseGuideActivity.class);
                startActivity(intent);
            }
        });

    }
}