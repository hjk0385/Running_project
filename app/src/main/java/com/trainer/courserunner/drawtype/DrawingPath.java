package com.trainer.courserunner.drawtype;

import com.trainer.courserunner.rooms.CourseFlag;
import com.trainer.courserunner.rooms.MapFlag;
import com.trainer.courserunner.rooms.UserLocationRecord;

import java.util.ArrayList;

public class DrawingPath extends ArrayList<DrawingAddress> {
    public DrawingPath(MapFlag[] mapFlags) {
        super();
        for (MapFlag courseFlag : mapFlags) {
            this.add(new DrawingAddress(courseFlag.latitude, courseFlag.longitude));
        }
    }
    public DrawingPath(UserLocationRecord[] userLocationRecords) {
        super();
        for (UserLocationRecord userLocationRecord : userLocationRecords) {
            this.add(new DrawingAddress(userLocationRecord.latitude, userLocationRecord.longitude));
        }
    }
}

