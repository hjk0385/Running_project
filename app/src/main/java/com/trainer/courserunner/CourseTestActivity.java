package com.trainer.courserunner;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;

import com.trainer.courserunner.coursesuggest.CourseSuggester;
import com.trainer.courserunner.managedata.MapDBDownloader;

public class CourseTestActivity extends NavermapActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AssetManager assetManager = getResources().getAssets();
        MapDBDownloader.download(assetManager,getApplicationContext());
        //DB load test
        Log.v("MY_USE_TAG","DB Copy Success");
        
    }

}
