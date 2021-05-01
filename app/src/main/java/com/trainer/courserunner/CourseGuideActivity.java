package com.trainer.courserunner;

import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;

import com.naver.maps.map.NaverMap;
import com.trainer.courserunner.course.drawer.CourseDrawerUserCourse;
import com.trainer.courserunner.legacy.CourseOverseer;
import com.trainer.courserunner.map.drawer.NavermapLocationActivity;

/*
    다른 기능들을 지원하기 위해서 CourseGuideActivity를 추상클래스로 만들고
    MapDrawer와의 기능을 합친후에 상속받아서 구현하는 형태로 만든다.
*/
public class CourseGuideActivity extends NavermapLocationActivity {
    CourseOverseer courseOverseer;
    CourseDrawerUserCourse courseDrawerUserCourse;

    boolean tempOptionMarker = true;
    boolean tempOptionCourse = true;
    boolean first;

    private void mapStart() {
        courseDrawerUserCourse.drawCoursePath();
        courseDrawerUserCourse.drawMarkers();
    }

    private void mapRefresh() {
        courseDrawerUserCourse.drawUserLocationPath();
        if (tempOptionMarker) {
            courseDrawerUserCourse.drawMarkers();
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
                tempOptionMarker = !tempOptionMarker;
                if (tempOptionMarker) {
                    courseDrawerUserCourse.drawMarkers();
                } else {
                    courseDrawerUserCourse.clearMarkers();
                }
                return true;
            case R.id.nocourse_option:
                //임시옵션
                tempOptionCourse = !tempOptionCourse;
                if (tempOptionCourse) {
                    courseDrawerUserCourse.drawCoursePath();
                } else {
                    courseDrawerUserCourse.clearCoursePath();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {
        super.onMapReady(naverMap);

        Intent intent = getIntent();
        long course_id = intent.getExtras().getLong("course_id");
        courseOverseer = new CourseOverseer();
        long usercourseId = courseOverseer.startOversight(course_id);
        courseDrawerUserCourse = new CourseDrawerUserCourse(this, course_id, usercourseId);
        this.mapStart();

        naverMap.addOnLocationChangeListener((Location location) -> {
            boolean changed = courseOverseer.updateUserLocation(location);
            if (changed) {
                this.mapRefresh();
            }
        });
    }
}
