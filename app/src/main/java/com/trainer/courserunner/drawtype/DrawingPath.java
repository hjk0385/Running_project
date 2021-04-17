package com.trainer.courserunner.drawtype;

import com.trainer.courserunner.rooms.CourseFlag;
import com.trainer.courserunner.rooms.UserLocationPath;

import java.util.ArrayList;

public class DrawingPath extends ArrayList<DrawingAddress> {
    public DrawingPath(CourseFlag[] courseFlags) {
        super();
        for (CourseFlag courseFlag : courseFlags) {
            this.add(new DrawingAddress(courseFlag.latitude, courseFlag.longtitude));
        }
    }

    public DrawingPath(UserLocationPath[] userLocationPaths) {
        super();
        for (UserLocationPath coursePath : userLocationPaths) {
            this.add(new DrawingAddress(coursePath.latitude,coursePath.longitude));
        }
    }
}

