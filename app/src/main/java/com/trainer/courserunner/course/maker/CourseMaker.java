package com.trainer.courserunner.course.maker;

import android.graphics.Bitmap;
import android.os.AsyncTask;

import androidx.core.util.Consumer;

import com.trainer.courserunner.Application.AppDatabaseLoader;
import com.trainer.courserunner.course.maker.policy.line.LineConnectPolicy;
import com.trainer.courserunner.course.maker.policy.quanzation.QuanzationPolicy;
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

public class CourseMaker extends AsyncTask<Void, Void, Long> {
    private Bitmap image;
    private ScopeMapInfo scopeMapInfo;
    private QuanzationPolicy quanzationPolicy;
    private LineConnectPolicy lineConnectPolicy;
    private Consumer<Long> courseIdConsumer;

    //직접 생성 금지
    private CourseMaker() {
    }

    @Override
    protected Long doInBackground(Void... voids) {
        //준비
        ScopeDotsMap scopeDotsMap = new ScopeDotsMap(scopeMapInfo);
        ScopeDotsImage scopeDotsImage = new ScopeDotsImage(image);
        //시작
        ScopeDotsMap imageRoad = quanzationPolicy.quantization(scopeDotsImage, scopeDotsMap);
        List<ScopeDotAddress> courseRoad = lineConnectPolicy.apply(imageRoad);
        //코스등록
        AppDatabase appDatabase = AppDatabaseLoader.getAppDatabase();
        //코스 정보등록
        CourseDao courseDao = appDatabase.courseDao();
        Long courseId = courseDao.insertDto(new Course());
        //코스 깃발등록
        CourseFlagDao courseFlagDao = AppDatabaseLoader.getAppDatabase().courseFlagDao();
        for (Integer i = 0; i < courseRoad.size(); i++) {
            CourseFlag courseFlag = new CourseFlag();
            courseFlag.courseId = courseId;
            courseFlag.courseFlagId = i;
            courseFlag.courseFlagLatitude = courseRoad.get(i).getLatitude();
            courseFlag.courseFlagLongitude = courseRoad.get(i).getLongitude();
            courseFlagDao.insertDto(courseFlag);
        }
        return courseId;
    }

    @Override
    protected void onPostExecute(Long aLong) {
        super.onPostExecute(aLong);
        courseIdConsumer.accept(aLong);
    }

    public static class CourseMakerBuilder {
        private Bitmap image;
        private ScopeMapInfo scopeMapInfo;
        private QuanzationPolicy quanzationPolicy;
        private LineConnectPolicy lineConnectPolicy;
        private Consumer<Long> courseIdConsumer;

        public CourseMakerBuilder(Bitmap image, ScopeMapInfo scopeMapInfo) {
            this.image = image;
            this.scopeMapInfo = scopeMapInfo;
            quanzationPolicy = null;
            lineConnectPolicy = null;
            courseIdConsumer = null;
        }

        public CourseMakerBuilder setLineConnectPolicy(LineConnectPolicy lineConnectPolicy) {
            this.lineConnectPolicy = lineConnectPolicy;
            return this;
        }

        public CourseMakerBuilder setQuanzationPolicy(QuanzationPolicy quanzationPolicy) {
            this.quanzationPolicy = quanzationPolicy;
            return this;
        }

        public CourseMakerBuilder setCourseIdConsumer(Consumer<Long> courseIdConsumer) {
            this.courseIdConsumer = courseIdConsumer;
            return this;
        }

        public CourseMaker build() {
            if (quanzationPolicy == null) {
                return null;
            } else if (lineConnectPolicy == null) {
                return null;
            } else if (courseIdConsumer == null) {
                return null;
            }
            CourseMaker courseMaker = new CourseMaker();
            courseMaker.image = this.image;
            courseMaker.scopeMapInfo = this.scopeMapInfo;
            courseMaker.lineConnectPolicy = this.lineConnectPolicy;
            courseMaker.quanzationPolicy = this.quanzationPolicy;
            return courseMaker;
        }
    }
}
