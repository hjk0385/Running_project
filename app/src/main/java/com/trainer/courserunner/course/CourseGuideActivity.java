package com.trainer.courserunner.course;

import android.location.Location;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;

import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.CameraPosition;
import com.naver.maps.map.CameraUpdate;
import com.naver.maps.map.NaverMap;
import com.trainer.courserunner.managedata.AssetLoader;
import com.trainer.courserunner.managedata.MapDAO;
import com.trainer.courserunner.maps.MapDrawer;
import com.trainer.courserunner.maps.NavermapLocationActivity;
import com.trainer.courserunner.rooms.AppDatabase;
import com.trainer.courserunner.rooms.AppDatabaseLoader;
import com.trainer.courserunner.scopetype.ScopeDotLocation;
import com.trainer.courserunner.scopetype.ScopeDotsImage;
import com.trainer.courserunner.scopetype.ScopeDotsMap;
import com.trainer.courserunner.scopetype.ScopeMapInfo;

import java.util.Map;


public class CourseGuideActivity extends NavermapLocationActivity {
    CourseOverseer courseOverseer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {
        super.onMapReady(naverMap);

        //테스트코드
        MapDAO.initMapDB(getApplicationContext());
        AppDatabaseLoader.initAppdatabase(getApplicationContext());
        ScopeMapInfo scopeMapInfo = new ScopeMapInfo(37.4916138, 126.7687037,
                37.506515, 126.779899);
        //course make
        ScopeDotsImage image = new ScopeDotsImage(AssetLoader.loadImage(this, "testbitmap1.png"));
        ScopeDotsMap maps = new ScopeDotsMap(scopeMapInfo);
        ScopeDotLocation currentLocation = new ScopeDotLocation(scopeMapInfo, scopeMapInfo.getStartX(), scopeMapInfo.getStartY());
        CourseMaker courseMaker = new CourseMaker();
        long course_id = courseMaker.makeCourse(image, maps, currentLocation);
        //테스트코드 종료

        //인텐드로 course_id를 받아서 처리될 내용
        courseOverseer = new CourseOverseer((MapDrawer) this);
        naverMap.addOnLocationChangeListener((Location location) -> {
            courseOverseer.updateOversight(location);
        });
        courseOverseer.startOversight(course_id);
    }

}
