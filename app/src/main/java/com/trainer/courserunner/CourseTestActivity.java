package com.trainer.courserunner;

import android.os.Bundle;

import com.trainer.courserunner.managedata.MapDAO;

public class CourseTestActivity extends NavermapActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MapDAO.initMapDB(this);
        MapDAO.initMapDB(this);
    }

}
