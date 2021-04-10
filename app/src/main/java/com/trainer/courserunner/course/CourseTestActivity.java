package com.trainer.courserunner.course;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;

import com.naver.maps.map.NaverMap;
import com.trainer.courserunner.maps.Geocoding;
import com.trainer.courserunner.maps.NavermapActivity;
import com.trainer.courserunner.maps.ReverseGeocoding;

public class CourseTestActivity extends NavermapActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {
        this.naverMap = naverMap;
        ReverseGeocoding reverseGeocoding= new ReverseGeocoding(this,(String s)-> Log.v("Reverse GEOTEST",s));
        reverseGeocoding.requestReverseGeocoding(37.3595963,127.1054328);
        Geocoding geocoding=new Geocoding(this,(String s)-> Log.v("GEOTEST",s));
        geocoding.requestGeocoding("분당구 불정로 6");
        /*
        String temp = ReverseGeocoding.getJsonData(37.474235, 126.697562);
        */
        /*
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
            InputStream inputStream=assetManager.open("testbitmap3.png");
            bitmap = BitmapFactory.decodeStream(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        ScopeDotsImage scopeDotsImage=new ScopeDotsImage(bitmap);
        ScopeDotsMap scopeDotsMap=new ScopeDotsMap(startX,startY,endX,endY);
        List<ScopeDotAddress> temp= CourseSuggester.suggestPath(scopeDotsImage,scopeDotsMap);
        this.drawPolyline(temp);
        */
    }

}
