package com.trainer.courserunner.course.drawer.drawtype;

import com.trainer.courserunner.rooms.MapFlag;
import com.trainer.courserunner.rooms.UserLocationRecord;

import java.util.ArrayList;
import java.util.List;

public class DrawingPath extends ArrayList<DrawingAddress> {
    public DrawingPath() {

    }

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

    public DrawingPath(List<MapFlag> mapFlags) {
        super();
        for (MapFlag mapFlag : mapFlags) {
            this.add(new DrawingAddress(mapFlag.latitude, mapFlag.longitude));
        }
    }
}

