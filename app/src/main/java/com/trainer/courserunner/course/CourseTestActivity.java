package com.trainer.courserunner.course;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.util.Pair;

import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.CameraPosition;
import com.naver.maps.map.CameraUpdate;
import com.naver.maps.map.NaverMap;
import com.trainer.courserunner.managedata.MapDAO;
import com.trainer.courserunner.maps.Geocoding;
import com.trainer.courserunner.maps.NavermapActivity;
import com.trainer.courserunner.maps.ReverseGeocoding;
import com.trainer.courserunner.scopetype.ScopeDotAddress;
import com.trainer.courserunner.scopetype.ScopeDotsImage;
import com.trainer.courserunner.scopetype.ScopeDotsMap;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class CourseTestActivity extends NavermapActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {
        this.naverMap = naverMap;
        /*
        ReverseGeocoding reverseGeocoding = new ReverseGeocoding(this, (String s) ->
                Log.v("Reverse GEOTEST", ReverseGeocoding.convertJsonToAddress(s)));
        reverseGeocoding.requestReverseGeocoding(37.5538369, 126.9757842);
        Geocoding geocoding = new Geocoding(this,
                (String s) -> {
                    Pair<Double, Double> temp = Geocoding.convertJsonToLatitudeLongtitude(s);
                    Log.v("GEOTEST", temp.first + "," + temp.second);
                }
        );
        geocoding.requestGeocoding("서울특별시 중구 남대문로5가 827");
        */

        /*
        String temp = ReverseGeocoding.getJsonData(37.474235, 126.697562);
        */

        double startX = 126.7087037;
        double startY = 37.4716138;
        double endX = 126.779899;
        double endY = 37.506515;

        MapDAO.initMapDB(this);
        CameraUpdate cameraUpdate = CameraUpdate.toCameraPosition(new CameraPosition(new LatLng(startY,startX),10));
        naverMap.moveCamera(cameraUpdate);
        
        AssetManager assetManager = getAssets();
        Bitmap bitmap = null;
        try {
            InputStream inputStream=assetManager.open("testbitmap2.png");
            bitmap = BitmapFactory.decodeStream(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        ScopeDotsImage scopeDotsImage=new ScopeDotsImage(bitmap);
        ScopeDotsMap scopeDotsMap=new ScopeDotsMap(startX,startY,endX,endY);
        List<ScopeDotAddress> temp= CourseSuggester.suggestPath(scopeDotsImage,scopeDotsMap);
        this.drawCourse(temp);
        for(ScopeDotAddress temp2:temp){
            this.drawOverlayMarker(temp2);
        }
    }

}
