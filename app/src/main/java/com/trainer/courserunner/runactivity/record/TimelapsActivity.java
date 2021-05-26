package com.trainer.courserunner.runactivity.record;

import androidx.annotation.NonNull;

import com.naver.maps.map.NaverMap;
import com.trainer.courserunner.component.timelaps.CourseTimelaps;
import com.trainer.courserunner.Application.mapdrawer.NavermapActivity;

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
