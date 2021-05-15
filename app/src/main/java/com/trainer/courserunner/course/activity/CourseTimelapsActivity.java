package com.trainer.courserunner.course.activity;

import androidx.annotation.NonNull;

import com.naver.maps.map.NaverMap;
import com.trainer.courserunner.course.CourseTimelaps;
import com.trainer.courserunner.map.drawer.NavermapActivity;

public class CourseTimelapsActivity extends NavermapActivity {
    CourseTimelaps courseTimelaps;
    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {
        super.onMapReady(naverMap);
        courseTimelaps=new CourseTimelaps(this,getIntent().getLongExtra("userCourseId",-1));
        courseTimelaps.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        courseTimelaps.stop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        courseTimelaps.stop();
    }
}