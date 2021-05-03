package com.trainer.courserunner.course.overseer;

import android.location.Location;

import com.trainer.courserunner.Application.AppDatabaseLoader;
import com.trainer.courserunner.map.geo.DistanceConverter;
import com.trainer.courserunner.rooms.AppDatabase;
import com.trainer.courserunner.rooms.CourseFlag;
import com.trainer.courserunner.rooms.UserCourseFlag;

public class CourseOverseerUserFlag extends CourseOverseer {
    protected Long courseId;

    public CourseOverseerUserFlag(Long courseId, Long usercourseId) {
        super(usercourseId);
        this.courseId = courseId;
    }

    @Override
    public void oversight(Location location) {
        registUserCourseFlag(location);
    }

    private boolean checkMarkerDistance(Double currentLatitude,Double currentLongitude,Double markerLatitude, Double markerLongitude) {
        final Double MARKER_DISTANCE = 100.0;
        return DistanceConverter.getDistance(currentLatitude, currentLongitude,
                markerLatitude, markerLongitude) < MARKER_DISTANCE;
    }

    private void registUserCourseFlag(Location location) {
        AppDatabase appDatabase = AppDatabaseLoader.getAppDatabase();
        CourseFlag[] courseFlags = appDatabase.courseFlagDao().getNotVisitedCourseFlags(courseId, usercourseId);
        for (CourseFlag courseFlag : courseFlags) {
            if (checkMarkerDistance(location.getLatitude(),location.getLongitude(),
                    courseFlag.courseFlagLatitude, courseFlag.courseFlagLongitude)) {
                UserCourseFlag userCourseFlag = new UserCourseFlag();
                userCourseFlag.courseFlagId = courseFlag.courseFlagId;
                userCourseFlag.courseId = courseId;
                userCourseFlag.userCourseId = this.usercourseId;
                appDatabase.userCourseFlagDao().insertDto(userCourseFlag);
            }
        }
    }
}
