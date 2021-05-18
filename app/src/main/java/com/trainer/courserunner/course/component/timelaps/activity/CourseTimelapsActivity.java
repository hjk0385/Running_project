package com.trainer.courserunner.course.component.timelaps.activity;

import android.os.AsyncTask;

import androidx.annotation.NonNull;

import com.naver.maps.map.NaverMap;
import com.trainer.courserunner.Application.AppFunctionLoader;
import com.trainer.courserunner.course.component.drawer.CourseDrawer;
import com.trainer.courserunner.course.component.drawer.CourseDrawerGuideLine;
import com.trainer.courserunner.course.component.drawer.CourseDrawerPolyline;
import com.trainer.courserunner.course.component.drawer.drawtype.DrawingAddress;
import com.trainer.courserunner.course.component.drawer.drawtype.DrawingPath;
import com.trainer.courserunner.course.component.timelaps.CourseDrawerTimelaps;
import com.trainer.courserunner.map.drawer.NavermapActivity;
import com.trainer.courserunner.rooms.AppDatabase;
import com.trainer.courserunner.rooms.UserCourseRecord;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CourseTimelapsActivity extends NavermapActivity {
    CourseDrawerTimelaps courseDrawerTimelaps;
    CourseTimeLapsAsyncTask courseTimeLapsAsyncTask;


    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {
        super.onMapReady(naverMap);
        Long userCourseId=getIntent().getLongExtra("userCourseId",-1);
        courseDrawerTimelaps=new CourseDrawerTimelaps(this,userCourseId);
        //asynctask 사용
        courseTimeLapsAsyncTask=new CourseTimeLapsAsyncTask();
        courseTimeLapsAsyncTask.execute();
    }

    @Override
    protected void onStop() {
        super.onStop();
        courseTimeLapsAsyncTask.setActive(false);
    }

    class CourseTimeLapsAsyncTask extends AsyncTask<Integer, Void,Void> {
        boolean active;
        public void setActive(boolean active) {
            this.active = active;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            active=true;
        }

        @Override
        protected Void doInBackground(Integer... integers) {

            while(active){
                try {
                    publishProgress();
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
            courseDrawerTimelaps.runComponent();
        }
    }
}
