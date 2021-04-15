package com.trainer.courserunner.course;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;

import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.CameraPosition;
import com.naver.maps.map.CameraUpdate;
import com.trainer.courserunner.managedata.MapDAO;
import com.trainer.courserunner.maps.NavermapLocationActivity;
import com.trainer.courserunner.scopetype.ScopeDotAddress;
import com.trainer.courserunner.scopetype.ScopeDotsImage;
import com.trainer.courserunner.scopetype.ScopeDotsMap;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class CourseTestActivity extends NavermapLocationActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onMapReady() {
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
            InputStream inputStream=assetManager.open("testbitmap1.png");
            ScopeDotsImage scopeDotsImage=new ScopeDotsImage(BitmapFactory.decodeStream(inputStream));


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
