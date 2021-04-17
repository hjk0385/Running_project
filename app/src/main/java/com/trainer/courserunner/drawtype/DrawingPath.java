package com.trainer.courserunner.drawtype;

import com.trainer.courserunner.rooms.CourseFlag;

import java.util.ArrayList;

public class DrawingPath extends ArrayList<DrawingAddress> {
    public DrawingPath(CourseFlag[] courseFlags) {
        super();
        for (CourseFlag courseFlag : courseFlags) {
            this.add(new DrawingAddress(courseFlag.latitude, courseFlag.longtitude));
        }
    }

    public DrawingPath(UserLocationRecord[] userLocationRecords) {
        super();
        for (UserLocationRecord coursePath : userLocationRecords) {
            this.add(new DrawingAddress(coursePath.latitude,coursePath.longitude));
        }
    }
}

