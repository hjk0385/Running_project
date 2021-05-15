package com.trainer.courserunner.course;

import android.os.AsyncTask;

import com.trainer.courserunner.course.component.drawer.CourseDrawerTimeLaps;
import com.trainer.courserunner.map.drawer.MapDrawer;

public class CourseTimelaps {

    /*
    CourseDrawerTimeLaps courseDrawerTimeLaps;
    CourseTimelapsAsyncTask courseTimelapsAsyncTask;
    public CourseTimelaps(MapDrawer mapDrawer,Long userCourseId){
        courseDrawerTimeLaps=new CourseDrawerTimeLaps(mapDrawer,userCourseId);
        courseTimelapsAsyncTask=new CourseTimelapsAsyncTask();
    }

    public void start(){
        courseTimelapsAsyncTask.execute();
    }
    public void stop(){
        courseTimelapsAsyncTask.setActive(false);
        courseTimelapsAsyncTask.cancel(true);
    }

    class CourseTimelapsAsyncTask extends AsyncTask<Void,Void,Void>{
        volatile boolean active;
        public CourseTimelapsAsyncTask(){
            active=true;
        }

        public void setActive(boolean active) {
            this.active = active;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            while(active){
                courseDrawerTimeLaps.update(null,null);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }
    }

     */
}
