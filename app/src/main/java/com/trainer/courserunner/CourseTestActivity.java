package com.trainer.courserunner;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;

import com.trainer.courserunner.managedata.MapDBDownloader;

public class CourseTestActivity extends NavermapActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AssetManager assetManager = getResources().getAssets();
        MapDBDownloader.download(assetManager);
        //로그 테스트
        Log.v("MY_TAG_test","Download test success");
        //DAO, 커넥터 수정
    }

}
