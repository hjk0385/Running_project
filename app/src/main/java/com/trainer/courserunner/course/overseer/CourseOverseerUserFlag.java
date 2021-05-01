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
    protected Void doInBackground(Location... locations) {
        registUserCourseFlag();
        return null;
    }

    private boolean checkMarkerDistance(Double markerLatitude, Double markerLongitude) {
        final Double MARKER_DISTANCE = 100.0;
        return DistanceConverter.getDistance(currentLocation.getLatitude(), currentLocation.getLongitude(),
                markerLatitude, markerLongitude) < MARKER_DISTANCE;
    }

    private void registUserCourseFlag() {
        AppDatabase appDatabase = AppDatabaseLoader.getAppDatabase();
        CourseFlag[] courseFlags = appDatabase.courseFlagDao().getNotVisitedCourseFlags(courseId, usercourseId);
        for (CourseFlag courseFlag : courseFlags) {
            if (checkMarkerDistance(courseFlag.courseFlagLatitude, courseFlag.courseFlagLongitude)) {
                UserCourseFlag userCourseFlag = new UserCourseFlag();
                userCourseFlag.courseFlagId = courseFlag.courseFlagId;
                userCourseFlag.userCourseId = this.usercourseId;
                appDatabase.userCourseFlagDao().insertDto(userCourseFlag);
            }
        }
    }


}
