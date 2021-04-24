package com.trainer.courserunner.course;

import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;

import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.CameraPosition;
import com.naver.maps.map.CameraUpdate;
import com.naver.maps.map.NaverMap;
import com.trainer.courserunner.R;
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

/*
    다른 기능들을 지원하기 위해서 CourseGuideActivity를 추상클래스로 만들고
    MapDrawer와의 기능을 합친후에 상속받아서 구현하는 형태로 만든다.
*/
public class CourseGuideActivity extends NavermapLocationActivity {
    CourseOverseer courseOverseer;
    CourseDrawer courseDrawer;

    boolean tempOptionMarker=true;

    private void mapStart() {
        courseDrawer.drawCoursePath();
        if(tempOptionMarker){
            courseDrawer.drawMarkers();
        }
    }

    private void mapRefresh() {
        courseDrawer.drawUserLocationPath();
        if(tempOptionMarker){
            courseDrawer.drawMarkers();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    //옵션 메뉴
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
                courseOverseer.setCurrentLineColor(Color.BLACK);
                return true;
            case R.id.guidecolor_blue:
                courseOverseer.setCurrentLineColor(Color.BLUE);
                return true;
            case R.id.guidecolor_red:
                courseOverseer.setCurrentLineColor(Color.RED);
                return true;
            case R.id.nomarker_option:
                //임시옵션
                courseDrawer.clearMarkers();
                tempOptionMarker=!tempOptionMarker;
                mapRefresh();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    boolean first;
    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {
        super.onMapReady(naverMap);

        Intent intent=getIntent();
        long course_id=intent.getExtras().getLong("course_id");
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
