package com.trainer.courserunner.course;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.os.Bundle;

import com.trainer.courserunner.managedata.AssetLoader;
import com.trainer.courserunner.maps.NavermapLocationActivity;

import java.io.IOException;
import java.io.InputStream;


public class CourseGuideActivity extends NavermapLocationActivity {
    CourseSuggesterDrawer courseSuggesterDrawer;
    CourseOverseer courseOverseer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public void onMapReady() {
        double startx = 126.7687037;
        double starty = 37.4916138;
        double endx = 126.779899;
        double endy = 37.506515;

        CourseSuggesterDrawer=new CourseSuggesterDrawer(AssetLoader(this,"testbitmap1.png"),)

        CourseSuggester courseSuggester = new CourseSuggester(bitmap, startx, starty, endx, endy);
        courseOverseer = new CourseOverseer(this, courseSuggester.suggestPath(), startx, starty, endx, endy);
    }

    @Override
    public void onLocationChangeListener(Location location) {
        super.onLocationChangeListener(location);
        courseOverseer.refresh(userLongitude, userLatitude);
    }
}
