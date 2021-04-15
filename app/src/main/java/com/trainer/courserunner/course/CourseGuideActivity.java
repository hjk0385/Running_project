package com.trainer.courserunner.course;

import android.content.res.AssetManager;
import android.database.sqlite.SQLiteBindOrColumnIndexOutOfRangeException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;

import com.trainer.courserunner.managedata.AssetLoader;
import com.trainer.courserunner.maps.NavermapLocationActivity;
import com.trainer.courserunner.rooms.AppDatabase;
import com.trainer.courserunner.rooms.AppDatabaseInstance;
import com.trainer.courserunner.scopetype.ScopeDotAddress;
import com.trainer.courserunner.scopetype.ScopeDotLocation;
import com.trainer.courserunner.scopetype.ScopeDotsImage;
import com.trainer.courserunner.scopetype.ScopeDotsMap;
import com.trainer.courserunner.scopetype.ScopeMapInfo;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;


public class CourseGuideActivity extends NavermapLocationActivity {
    CourseOverseer courseOverseer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onMapReady() {
        ScopeMapInfo scopeMapInfo = new ScopeMapInfo(37.4916138, 126.7687037,
                37.506515, 126.779899);
        //course make
        ScopeDotsImage image = new ScopeDotsImage(AssetLoader.loadImage(this, "testbitmap1.png"));
        ScopeDotsMap maps = new ScopeDotsMap(scopeMapInfo);
        ScopeDotLocation currentLocation = new ScopeDotLocation(scopeMapInfo,scopeMapInfo.getStartX(),scopeMapInfo.getStartY());
        //테스트코드 - 나중에 인텐드로 코스번호만 받아서 처리가능
        AppDatabaseInstance appDatabase =new AppDatabaseInstance(this);
        CourseMaker courseMaker = new CourseMaker(appDatabase);
        Long course_id=courseMaker.makeCourse(image,maps,currentLocation);
        Log.v("temp",course_id.toString());
    }


    @Override
    public void onLocationChangeListener(Location location) {
        super.onLocationChangeListener(location);
    }
}
