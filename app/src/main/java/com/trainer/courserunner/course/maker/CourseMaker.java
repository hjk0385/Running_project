package com.trainer.courserunner.course.maker;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;

import androidx.core.util.Consumer;

import com.trainer.courserunner.Application.AppDatabaseLoader;
import com.trainer.courserunner.course.maker.policy.line.LineConnectPolicy;
import com.trainer.courserunner.course.maker.policy.marker.MarkerSelection;
import com.trainer.courserunner.course.maker.policy.quanzation.QuanzationImageToMap;
import com.trainer.courserunner.course.maker.scopetype.ScopeDotAddress;
import com.trainer.courserunner.course.maker.scopetype.ScopeDotsImage;
import com.trainer.courserunner.course.maker.scopetype.ScopeDotsMap;
import com.trainer.courserunner.course.maker.scopetype.ScopeMapInfo;
import com.trainer.courserunner.rooms.AppDatabase;
import com.trainer.courserunner.rooms.Course;
import com.trainer.courserunner.rooms.CourseDao;
import com.trainer.courserunner.rooms.CourseFlag;
import com.trainer.courserunner.rooms.CourseFlagDao;

import java.util.List;

public class CourseMaker implements Runnable{
    private Bitmap image;
    private ScopeMapInfo scopeMapInfo;

    private QuanzationImageToMap quanzationImageToMap;
    private LineConnectPolicy lineConnectPolicy;
    private MarkerSelection markerSelection;
    private Consumer<Long> courseIdConsumer;

    public CourseMaker(Bitmap image, ScopeMapInfo scopeMapInfo) {
        this.image=image;
        this.scopeMapInfo=scopeMapInfo;
    }

    public void setQuanzationImageToMap(QuanzationImageToMap quanzationImageToMap) {
        this.quanzationImageToMap = quanzationImageToMap;
    }

    public void setLineConnectPolicy(LineConnectPolicy lineConnectPolicy) {
        this.lineConnectPolicy = lineConnectPolicy;
    }

    public void setMarkerSelection(MarkerSelection markerSelection) {
        this.markerSelection = markerSelection;
    }

    public void setCourseIdConsumer(Consumer<Long> courseIdConsumer) {
        this.courseIdConsumer = courseIdConsumer;
    }

    private List<ScopeDotAddress> makeCourse(){
        ScopeDotsMap scopeDotsMap = new ScopeDotsMap(scopeMapInfo);
        ScopeDotsImage scopeDotsImage = new ScopeDotsImage(image);
        //
        ScopeDotsMap imageAddresses=quanzationImageToMap.apply(scopeDotsImage,scopeDotsMap);
        return lineConnectPolicy.apply(imageAddresses);
    }

    private Long registDB(List<ScopeDotAddress> courseRoad){
        AppDatabase appDatabase = AppDatabaseLoader.getAppDatabase();
        Long courseId = appDatabase.courseDao().insertDto(new Course());
        for (int i = 0; i < courseRoad.size(); i++) {
            CourseFlag courseFlag = new CourseFlag();
            courseFlag.courseId = courseId;
            courseFlag.courseFlagId = (long) i;
            courseFlag.courseFlagLatitude = courseRoad.get(i).getLatitude();
            courseFlag.courseFlagLongitude = courseRoad.get(i).getLongitude();
            courseFlag.markerFlag=markerSelection.get();
            appDatabase.courseFlagDao().insertDto(courseFlag);
        }
        return courseId;
    }


    @Override
    public void run(){
        new CourseMakerAsyncTask().execute();
    }

    public class CourseMakerAsyncTask extends AsyncTask<Void, Void, Long>{
        @Override
        protected Long doInBackground(Void... voids) {
            return registDB(makeCourse());
        }

        @Override
        protected void onPostExecute(Long aLong) {
            super.onPostExecute(aLong);
            courseIdConsumer.accept(aLong);
        }
    }
}
