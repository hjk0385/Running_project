package com.trainer.courserunner.drawtype;

import com.trainer.courserunner.rooms.CoursePath;
import com.trainer.courserunner.rooms.UserLocationPath;

import java.util.ArrayList;
import java.util.List;

public class DrawingPath extends ArrayList<DrawingAddress> {
    public DrawingPath(CoursePath[] coursePaths) {
        super();
        for (CoursePath coursePath : coursePaths) {
            this.add(new DrawingAddress(coursePath.latitude, coursePath.longtitude));
        }
    }

    public DrawingPath(UserLocationPath[] userLocationPaths) {
        super();
        for (UserLocationPath coursePath : userLocationPaths) {
            this.add(new DrawingAddress(coursePath.latitude,coursePath.longitude));
        }
    }
}

