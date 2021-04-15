package com.trainer.courserunner.course;

import android.content.res.AssetManager;
import android.database.sqlite.SQLiteBindOrColumnIndexOutOfRangeException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.os.Bundle;

import com.trainer.courserunner.managedata.AssetLoader;
import com.trainer.courserunner.maps.NavermapLocationActivity;
import com.trainer.courserunner.scopetype.ScopeDotAddress;
import com.trainer.courserunner.scopetype.ScopeDotLocation;
import com.trainer.courserunner.scopetype.ScopeDotsImage;
import com.trainer.courserunner.scopetype.ScopeDotsMap;

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
        //test code
        double startx = 126.7687037;
        double starty = 37.4916138;
        double endx = 126.779899;
        double endy = 37.506515;

        ScopeDotsImage image=new ScopeDotsImage(AssetLoader.loadImage(this,"testbitmap1.png"));
        ScopeDotsMap maps=new ScopeDotsMap(startx,starty,endx,endy);
        ScopeDotLocation currentLocation=new ScopeDotLocation(startx,starty,endx,endy,startx,endy);

        List<ScopeDotAddress> course=CourseSuggester.suggestPath(image,maps,currentLocation);

        courseOverseer = new CourseOverseer(this);
        courseOverseer.startOverseer(course,currentLocation);
    }

    @Override
    public void onLocationChangeListener(Location location) {
        super.onLocationChangeListener(location);
        courseOverseer.refreshOversight(userLongitude, userLatitude);
    }
}
