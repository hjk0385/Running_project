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

import java.util.List;
import java.util.Map;


public class CourseGuideActivity extends NavermapLocationActivity {
    CourseOverseer courseOverseer;
    CourseDrawer courseDrawer;

    private void mapStart() {
        courseDrawer.drawCoursePath();
        courseDrawer.drawMarkers();
    }

    private void mapRefresh() {
        courseDrawer.drawMarkers();
        courseDrawer.drawUserLocationPath();
    }

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
        ScopeDotsImage image = new ScopeDotsImage(AssetLoader.loadImage(this, "testbitmap2.png"));
        ScopeDotsMap maps = new ScopeDotsMap(scopeMapInfo);
        ScopeDotLocation currentLocation = new ScopeDotLocation(scopeMapInfo, scopeMapInfo.getStartX(), scopeMapInfo.getStartY());
        CourseMaker courseMaker = new CourseMaker();
        long course_id = courseMaker.makeCourse(image, maps, currentLocation);
        Log.v("testFunction", String.valueOf(course_id));
        //테스트코드 종료


        //인텐드로 course_id를 받아서 처리될 내용
        courseOverseer = new CourseOverseer();
        long usercourseId = courseOverseer.startOversight(course_id);
        courseDrawer = new CourseDrawer(this, course_id, usercourseId);
        this.mapStart();

        naverMap.addOnLocationChangeListener((Location location) -> {
            boolean changed = courseOverseer.updateUserLocation(location);
            if (changed) {
                this.mapRefresh();
            }
        });
    }
}
