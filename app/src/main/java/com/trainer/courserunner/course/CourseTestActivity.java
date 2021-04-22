package com.trainer.courserunner.course;

import android.os.Bundle;

import androidx.annotation.NonNull;

import com.naver.maps.map.NaverMap;
import com.trainer.courserunner.maps.Geocoding;
import com.trainer.courserunner.maps.NavermapActivity;
import com.trainer.courserunner.maps.NavermapLocationActivity;

public class CourseTestActivity extends NavermapActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {
        super.onMapReady(naverMap);



    }
}
