package com.trainer.courserunner.course.activity;

import androidx.annotation.NonNull;

import com.naver.maps.map.NaverMap;
import com.trainer.courserunner.course.component.timelaps.CourseTimelaps;
import com.trainer.courserunner.map.drawer.NavermapActivity;

public class TimelapsActivity extends NavermapActivity {
    CourseTimelaps courseTimelaps;
    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {
        super.onMapReady(naverMap);
        long userCourseId=getIntent().getLongExtra("userCourseId",-1);
        courseTimelaps=new CourseTimelaps(userCourseId,this);
        courseTimelaps.execute();
    }
}
