package com.trainer.courserunner.rooms;

import android.graphics.Color;
import android.location.Location;
import android.view.MenuItem;

import androidx.annotation.NonNull;

import com.naver.maps.map.NaverMap;
import com.trainer.courserunner.R;
import com.trainer.courserunner.course.CourseConductor;
import com.trainer.courserunner.map.drawer.NaverMapOptionActivity;

//팩토리 패턴 활용
public abstract class RunningMapActivity extends NaverMapOptionActivity {
    CourseConductor courseConductor;

    protected abstract CourseConductor createCourseConductor();

    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {
        super.onMapReady(naverMap);
        courseConductor = createCourseConductor();
        naverMap.addOnLocationChangeListener((Location location) -> {
            courseConductor.update(null, location);
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.guidecolor_black:
                courseConductor.setCurrentColor(Color.BLACK);
                return true;
            case R.id.guidecolor_blue:
                courseConductor.setCurrentColor(Color.BLUE);
                return true;
            case R.id.guidecolor_red:
                courseConductor.setCurrentColor(Color.RED);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
