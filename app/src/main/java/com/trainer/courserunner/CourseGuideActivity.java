package com.trainer.courserunner;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;

import com.naver.maps.map.LocationTrackingMode;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.OnMapReadyCallback;
import com.trainer.courserunner.courseguider.CourseOverseer;
import com.trainer.courserunner.coursesuggest.CourseSuggester;
import com.trainer.courserunner.coursesuggest.DotAddress;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public class CourseGuideActivity extends NavermapUserLocationActivity{
    CourseOverseer courseOverseer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //debug -> release intent
        Bitmap bitmap=null;
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
        CourseSuggester courseSuggester = new CourseSuggester(bitmap,startx,starty,endx,endy);
        courseOverseer=new CourseOverseer(this,courseSuggester.suggestPath(),startx,starty,endx,endy);
    }

    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {
        super.onMapReady(naverMap);
        naverMap.setLocationSource(locationSource);
        naverMap.setLocationTrackingMode(LocationTrackingMode.Follow);
        naverMap.addOnLocationChangeListener(location -> {
            userLatitude=location.getLatitude();
            userLongitude=location.getLongitude();
            courseOverseer.refresh(userLongitude,userLatitude);
        });
    }
}
