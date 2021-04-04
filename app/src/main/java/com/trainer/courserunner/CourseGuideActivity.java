package com.trainer.courserunner;

import android.os.AsyncTask;
import android.os.Bundle;

import com.trainer.courserunner.courseguider.CourseOverseer;
import com.trainer.courserunner.coursesuggest.DotAddress;

import java.util.ArrayList;
import java.util.List;


public class CourseGuideActivity extends NavermapActivity {
    CourseOverseer courseOverseer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //debug
        List<DotAddress> course = new ArrayList<>();
        courseOverseer = new CourseOverseer(this, course);
    }

    class CourseOverseerTasker extends AsyncTask<CourseOverseer, Void, Void> {
        boolean active;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            active = true;
        }

        @Override
        protected Void doInBackground(CourseOverseer... courseOverseers) {
            while (active) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
            //위치 불러오기

            //위치 새로고침

        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            active = false;
        }
    }
}
