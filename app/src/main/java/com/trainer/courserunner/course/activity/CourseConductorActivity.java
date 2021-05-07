package com.trainer.courserunner.course.activity;

import android.graphics.Color;
import android.location.Location;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;

import com.naver.maps.map.NaverMap;
import com.trainer.courserunner.R;
import com.trainer.courserunner.course.CourseConductor;
import com.trainer.courserunner.map.drawer.NavermapLocationActivity;

//이 엑티비티를 사용해서 만든다.
public abstract class CourseConductorActivity extends NavermapLocationActivity {
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
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_guidecolor_option, menu);
        return true;
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
