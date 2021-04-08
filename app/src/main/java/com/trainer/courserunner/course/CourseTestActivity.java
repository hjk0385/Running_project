package com.trainer.courserunner.course;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;

import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.CameraPosition;
import com.naver.maps.map.CameraUpdate;
import com.naver.maps.map.NaverMap;
import com.trainer.courserunner.managedata.MapDAO;
import com.trainer.courserunner.managedata.MapDTO;
import com.trainer.courserunner.maps.NavermapActivity;
import com.trainer.courserunner.scopetype.ScopeDot;
import com.trainer.courserunner.scopetype.ScopeDotAddress;
import com.trainer.courserunner.scopetype.ScopeDotPixel;
import com.trainer.courserunner.scopetype.ScopeDots;
import com.trainer.courserunner.scopetype.ScopeDotsImage;
import com.trainer.courserunner.scopetype.ScopeDotsMap;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
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
        double startX = 126.7087037;
        double startY = 37.4916138;
        double endX = 126.779899;
        double endY = 37.506515;

        MapDAO.initMapDB(this);
        CameraUpdate cameraUpdate = CameraUpdate.toCameraPosition(new CameraPosition(new LatLng(startY,startX),10));
        naverMap.moveCamera(cameraUpdate);

        Bitmap bm=Bitmap.createBitmap(10,10,Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas();
        Paint MyPaint = new Paint();
        MyPaint.setColor(Color.RED);
        c.drawPoint(1,1,MyPaint);
        c.drawPoint(1,2,MyPaint);
        c.drawPoint(1,3,MyPaint);
        c.drawPoint(1,4,MyPaint);
        */

        /*
        List<ScopeDot> dots=new ArrayList<>();
        dots.add(new ScopeDotPixel(10,10,1,1));
        dots.add(new ScopeDotPixel(10,10,1,2));
        dots.add(new ScopeDotPixel(10,10,1,3));
        dots.add(new ScopeDotPixel(10,10,1,4));
        dots.add(new ScopeDotPixel(10,10,2,4));
        dots.add(new ScopeDotPixel(10,10,3,4));
        dots.add(new ScopeDotPixel(10,10,4,4));
        */

        /*
        ScopeDotsImage sdi = new ScopeDotsImage(bm);
        List<ScopeDotAddress> temp= new CourseSuggester(bm,startX,startY,endX,endY).suggestPath();
        this.drawPolyline(temp);
        */
    }

}
