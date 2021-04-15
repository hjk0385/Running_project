package com.trainer.courserunner.course;

import android.os.Bundle;
import android.util.Log;

import com.trainer.courserunner.managedata.AssetLoader;
import com.trainer.courserunner.managedata.MapDAO;
import com.trainer.courserunner.maps.NavermapLocationActivity;
import com.trainer.courserunner.rooms.AppDatabase;
import com.trainer.courserunner.rooms.AppDatabaseLoader;
import com.trainer.courserunner.rooms.CoursePath;
import com.trainer.courserunner.scopetype.ScopeDotLocation;
import com.trainer.courserunner.scopetype.ScopeDotsImage;
import com.trainer.courserunner.scopetype.ScopeDotsMap;
import com.trainer.courserunner.scopetype.ScopeMapInfo;

public class CourseTestActivity extends NavermapLocationActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onMapReady() {
        //DB초기화
        MapDAO.initMapDB(getApplicationContext());
        AppDatabaseLoader.initAppdatabase(getApplicationContext());

//        CoursePath[] temp =AppDatabaseLoader.getAppDatabase().coursePathDao().loadCoursePath(3);
//        Log.v("abcd", String.valueOf(temp.length));
        CourseOverseer courseOverseer= new CourseOverseer(this);
        courseOverseer.startOversight(3);

        /*
        //테스트코드
        ScopeMapInfo scopeMapInfo = new ScopeMapInfo(37.4916138, 126.7687037,
                37.506515, 126.779899);
        //course make
        ScopeDotsImage image = new ScopeDotsImage(AssetLoader.loadImage(this, "testbitmap1.png"));
        ScopeDotsMap maps = new ScopeDotsMap(scopeMapInfo);
        ScopeDotLocation currentLocation = new ScopeDotLocation(scopeMapInfo,scopeMapInfo.getStartX(),scopeMapInfo.getStartY());
        //테스트코드 - 나중에 인텐드로 코스번호만 받아서 처리가능
        AppDatabase appDatabase =AppDatabaseLoader.getAppDatabase();
        CourseMaker courseMaker = new CourseMaker();
        Long course_id=courseMaker.makeCourse(image,maps,currentLocation);
        Log.v("temp",course_id.toString());
        //
        */
        //DB에서 불러와서 그리기


    }
}
