package com.trainer.courserunner.conductor;

import android.content.Context;
import android.location.Location;

import com.trainer.courserunner.component.capture.CourseCapture;
import com.trainer.courserunner.component.drawer.CourseDrawerUserRecord;
import com.trainer.courserunner.component.overseer.CourseOverseerUserRecord;
import com.trainer.courserunner.mapdrawer.MapDrawer;

public class CourseConductorSketchBook extends CourseConductor {
    //컴포넌트
    protected CourseOverseerUserRecord overseerUserRecord;
    CourseDrawerUserRecord drawerUserRecord;
    CourseCapture courseCapture;

    public CourseConductorSketchBook(MapDrawer mapDrawer, Long userCourseId, Context context) {
        super(mapDrawer, userCourseId, context);
        //생성
        overseerUserRecord = new CourseOverseerUserRecord(userCourseId);
        drawerUserRecord = new CourseDrawerUserRecord(mapDrawer, userCourseId);

        overseerUserRecord.setCurrentLineColor(currentColor);
        courseCapture = new CourseCapture(userCourseId,context);
        //연동정의
        overseerUserRecord.setFinishEventConsumer((Object o) -> {
            drawerUserRecord.runComponent();
            courseCapture.setScreenshotName(String.valueOf(o));
            courseCapture.runComponent();
        });
    }
    //위치이벤트 -> 감시이벤트 -> 그리기 이벤트

    @Override
    public void setCurrentColor(Integer currentColor) {
        this.currentColor = currentColor;
        overseerUserRecord.setCurrentLineColor(currentColor);
    }

    @Override
    public void refreshLocation(Location location) {
        overseerUserRecord.refreshLocation(location);
    }
}
