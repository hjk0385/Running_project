package com.trainer.courserunner.course;

import android.graphics.Color;
import android.location.Location;

import com.trainer.courserunner.course.component.CourseComponent;
import com.trainer.courserunner.course.component.drawer.CourseDrawer;
import com.trainer.courserunner.course.component.drawer.CourseDrawerUserRecord;
import com.trainer.courserunner.course.component.overseer.CourseOverseerUserRecord;
import com.trainer.courserunner.map.drawer.MapDrawer;

public class CourseConductorSketchBook extends CourseConductor{
    //컴포넌트
    protected CourseOverseerUserRecord overseerUserRecord;
    CourseDrawerUserRecord drawerUserRecord;

    public CourseConductorSketchBook(MapDrawer mapDrawer, Long userCourseId) {
        super(mapDrawer,userCourseId);
        //생성
        overseerUserRecord = new CourseOverseerUserRecord(userCourseId);
        drawerUserRecord = new CourseDrawerUserRecord(mapDrawer, userCourseId);

        overseerUserRecord.setCurrentLineColor(currentColor);
        //연동정의
        overseerUserRecord.setFinishEventConsumer((Object o)->{
                drawerUserRecord.runComponent();
        });
    }
    //위치이벤트 -> 감시이벤트 -> 그리기 이벤트

    @Override
    public void setCurrentColor(Integer currentColor) {
        this.currentColor = currentColor;
        overseerUserRecord.setCurrentLineColor(currentColor);
    }

    @Override
    public void refreshLocation(Location location){
        overseerUserRecord.refreshLocation(location);
    }
}
