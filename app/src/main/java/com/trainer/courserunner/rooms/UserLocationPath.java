package com.trainer.courserunner.rooms;

import androidx.room.Entity;
import androidx.room.ForeignKey;

@Entity(tableName = "userlocationpath",
        primaryKeys = {
                "usercourse_id",
                "userlocation_id"
        },
        foreignKeys = {
                @ForeignKey(
                        entity = UserCourseInfo.class,
                        parentColumns = "usercourse_id",
                        childColumns = "usercourse_id",
                        onDelete = ForeignKey.CASCADE
                )
        }
)
public class UserLocationPath {
    public long usercourse_id;
    public long userlocation_id;
    public double longitude;
    public double latitude;
}
