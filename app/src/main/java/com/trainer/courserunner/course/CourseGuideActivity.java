package com.trainer.courserunner.course;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.os.Bundle;

import androidx.annotation.NonNull;

import com.naver.maps.map.LocationTrackingMode;
import com.naver.maps.map.NaverMap;
import com.trainer.courserunner.course.CourseOverseer;
import com.trainer.courserunner.course.CourseSuggester;
import com.trainer.courserunner.maps.NavermapLocationActivity;

import java.io.IOException;
import java.io.InputStream;


public class CourseGuideActivity extends NavermapLocationActivity {
    CourseOverseer courseOverseer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //debug -> release intent
        Bitmap bitmap = null;
        try {
            AssetManager assetManager = getResources().getAssets();
            InputStream inputStream = assetManager.open("testbitmap1.png");
            bitmap = BitmapFactory.decodeStream(inputStream);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        double startx = 126.7687037;
        double starty = 37.4916138;
        double endx = 126.779899;
        double endy = 37.506515;

        //구현 실행 내용
        CourseSuggester courseSuggester = new CourseSuggester(bitmap, startx, starty, endx, endy);
        courseOverseer = new CourseOverseer(this, courseSuggester.suggestPath(), startx, starty, endx, endy);
    }

    @Override
    public void onLocationChangeListener(Location location) {
        super.onLocationChangeListener(location);
        courseOverseer.refresh(userLongitude, userLatitude);
    }
}
