package com.trainer.courserunner.drawtype;

import com.trainer.courserunner.rooms.CoursePath;

import java.util.ArrayList;
import java.util.List;

public class DrawingPath extends ArrayList<DrawingAddress> {
    public DrawingPath(CoursePath[] coursePaths){
        super();
        for (CoursePath coursePath : coursePaths) {
            this.add(new DrawingAddress(coursePath.latitude, coursePath.longtitude));
        }
    }
}

