package com.trainer.courserunner;

import android.os.AsyncTask;
import android.os.Bundle;

import com.trainer.courserunner.coursesuggest.DotAddress;

import java.util.List;


public class CourseGuideActivity extends NavermapActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new CourseOverseerMap().execute();

    }

    class CourseOverseerMap extends AsyncTask<Object,Object,Object> {


        @Override
        protected Object doInBackground(Object... objects) {
            while(true){
                //oversightMap
                //1초 대기
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //Debug Code
                break;
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Object... values) {
            super.onProgressUpdate(values);
            //현재경로, 남은경로, 지나간 경로
        }

    }
}
