package com.trainer.courserunner.rooms;

import androidx.room.Entity;
import androidx.room.ForeignKey;

@Entity(tableName = "usercourselocationrecord",
        primaryKeys = {
                "usercourse_id",
                "userlocation_id"
        },
        foreignKeys = {
                @ForeignKey(
                        entity = UserCourseInfo.class,
                        parentColumns = "usercourse_id",
                        childColumns = "usercourse_id",
                        onDelete = ForeignKey.CASCADE),
                @ForeignKey(
                        entity = UserLocationRecord.class,
                        parentColumns = "userlocation_id",
                        childColumns = "userlocation_id",
                        onDelete = ForeignKey.CASCADE
                )
        })
public class UserCourseLocationRecord {
    public long usercourse_id;
    public long userlocation_id;
}
