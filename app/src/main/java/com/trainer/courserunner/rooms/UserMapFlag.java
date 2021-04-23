package com.trainer.courserunner.rooms;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "usermapflag",
        primaryKeys = {
                "usercourse_id",
                "mapflag_id"
        },
        foreignKeys = {
                @ForeignKey(
                        entity = MapFlag.class,
                        parentColumns = "mapflag_id",
                        childColumns = "mapflag_id",
                        onDelete = ForeignKey.CASCADE),
                @ForeignKey(
                        entity = UserCourseInfo.class,
                        parentColumns = "usercourse_id",
                        childColumns = "usercourse_id",
                        onDelete = ForeignKey.CASCADE)
        },
        indices = {@Index("mapflag_id")})
public class UserMapFlag {
    public long usercourse_id;
    public long mapflag_id;
}
