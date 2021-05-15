package com.trainer.courserunner.course.component.maker;

import android.graphics.Bitmap;
import android.location.Location;
import android.os.AsyncTask;

import androidx.core.util.Consumer;

import com.trainer.courserunner.Application.AppDatabaseLoader;
import com.trainer.courserunner.course.component.CourseComponent;
import com.trainer.courserunner.course.component.maker.layer.flag.ConvertCourseFlagLayer;
import com.trainer.courserunner.course.component.maker.layer.line.LineConnectLayer;
import com.trainer.courserunner.course.component.maker.layer.line.LineConnectPolicy;
import com.trainer.courserunner.course.component.maker.layer.marker.MarkerSelectionLayer;
import com.trainer.courserunner.course.component.maker.layer.quanzation.QuanzationLayer;
import com.trainer.courserunner.course.component.maker.layer.regist.CourseRegistLayer;
import com.trainer.courserunner.course.component.maker.scopetype.ScopeDotAddress;
import com.trainer.courserunner.course.component.maker.scopetype.ScopeDotsImage;
import com.trainer.courserunner.course.component.maker.scopetype.ScopeDotsMap;
import com.trainer.courserunner.course.component.maker.scopetype.ScopeMapInfo;
import com.trainer.courserunner.rooms.AppDatabase;
import com.trainer.courserunner.rooms.Course;
import com.trainer.courserunner.rooms.CourseFlag;

import java.util.List;

public class CourseMaker extends CourseComponent {
    private ScopeDotsImage scopeDotsImage;
    private ScopeDotsMap scopeDotsMap;
    private ScopeDotAddress startLocation;

    private QuanzationLayer quanzationLayer;
    private LineConnectLayer lineConnectLayer;
    private CourseRegistLayer courseRegistLayer;

    public static class Builder{
        private ScopeDotsImage scopeDotsImage;
        private ScopeDotsMap scopeDotsMap;
        private ScopeDotAddress startLocation;

        private QuanzationLayer quanzationLayer;
        private LineConnectLayer lineConnectLayer;
        private CourseRegistLayer courseRegistLayer;

        public void setScopeDotsImage(Bitmap bitmap) {
            this.scopeDotsImage = new ScopeDotsImage(bitmap);
        }

        public void setScopeDotsMap(ScopeMapInfo scopeMapInfo) {
            this.scopeDotsMap = new ScopeDotsMap(scopeMapInfo);
        }

        public void setLineConnectLayer(LineConnectLayer lineConnectLayer) {
            this.lineConnectLayer = lineConnectLayer;
        }

        public void setQuanzationLayer(QuanzationLayer quanzationLayer) {
            this.quanzationLayer = quanzationLayer;
        }

        public void setCourseRegistLayer(CourseRegistLayer courseRegistLayer) {
            this.courseRegistLayer = courseRegistLayer;
        }

        public void setStartLocation(ScopeDotAddress startLocation) {
            this.startLocation = startLocation;
        }

        public CourseMaker build(){
            CourseMaker courseMaker = new CourseMaker();
            courseMaker.scopeDotsImage=scopeDotsImage;
            courseMaker.scopeDotsMap=scopeDotsMap;
            courseMaker.startLocation=startLocation;
            courseMaker.quanzationLayer=quanzationLayer;
            courseMaker.lineConnectLayer=lineConnectLayer;
            courseMaker.courseRegistLayer=courseRegistLayer;
            return courseMaker;
        }

    }

    @Override
    protected Object runInWorkThread() {
        ScopeDotsMap scopeDotsImageMap = quanzationLayer.apply(scopeDotsImage,scopeDotsMap);
        List<ScopeDotAddress> course = lineConnectLayer.apply(scopeDotsImageMap,startLocation);
        Long courseId = courseRegistLayer.apply(course);
        return courseId;
    }

    @Override
    protected void runInUiThread(Object object) {

    }
}
