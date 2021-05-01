package com.trainer.courserunner;

import android.graphics.Color;
import android.location.Location;
import android.view.MenuItem;

import androidx.annotation.NonNull;

import com.naver.maps.map.NaverMap;
import com.trainer.courserunner.course.CourseConductorSketchBook;
import com.trainer.courserunner.map.drawer.NaverMapOptionActivity;
import com.trainer.courserunner.map.drawer.NavermapLocationActivity;

public class SketchBookActivity extends NaverMapOptionActivity {
    CourseConductorSketchBook courseConductorSketchBook;

    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {
        super.onMapReady(naverMap);
        courseConductorSketchBook = new CourseConductorSketchBook(this);
        naverMap.addOnLocationChangeListener((Location location) -> {
            courseConductorSketchBook.update(null, location);
        });
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.guidecolor_black:
                courseConductorSketchBook.setCurrentColor(Color.BLACK);
                return true;
            case R.id.guidecolor_blue:
                courseConductorSketchBook.setCurrentColor(Color.BLUE);
                return true;
            case R.id.guidecolor_red:
                courseConductorSketchBook.setCurrentColor(Color.RED);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
/*
    이벤트 발생시 전달순서
    SketchBookActivity -> courseOverseerUserRecord -> courseDrawerUserCourse
*/