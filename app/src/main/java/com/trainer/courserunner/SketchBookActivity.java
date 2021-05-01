package com.trainer.courserunner;

import android.location.Location;

import androidx.annotation.NonNull;

import com.naver.maps.map.NaverMap;
import com.trainer.courserunner.course.CourseConductorSketchBook;
import com.trainer.courserunner.map.drawer.NavermapLocationActivity;

public class SketchBookActivity extends NavermapLocationActivity {
    CourseConductorSketchBook courseConductorSketchBook;

    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {
        super.onMapReady(naverMap);
        courseConductorSketchBook = new CourseConductorSketchBook(this);
        naverMap.addOnLocationChangeListener((Location location) -> {
            courseConductorSketchBook.update(null, location);
        });
    }
}
/*
    이벤트 발생시 전달순서
    SketchBookActivity -> courseOverseerUserRecord -> courseDrawerUserCourse
*/