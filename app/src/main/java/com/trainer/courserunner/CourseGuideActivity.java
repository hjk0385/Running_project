package com.trainer.courserunner;

import android.os.Bundle;

import androidx.fragment.app.FragmentManager;

import com.naver.maps.map.MapFragment;
import com.trainer.courserunner.courseguider.CourseOverseer;
import com.trainer.courserunner.courseguider.MapDrawer;

public class CourseGuideActivity extends NavermapActivity{
    CourseOverseer courseOverseer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        courseOverseer=new CourseOverseer((MapDrawer)this);
        //thread start



    }
}
