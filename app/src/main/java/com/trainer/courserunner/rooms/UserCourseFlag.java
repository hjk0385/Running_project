package com.trainer.courserunner.rooms;

import androidx.room.Entity;
import androidx.room.ForeignKey;

@Entity(tableName = "usercourseflag",
        primaryKeys = {
                "usercourse_id",
                "courseflag_id"
        },
        foreignKeys = {
                @ForeignKey(
                        entity = UserCourseInfo.class,
                        parentColumns = "usercourse_id",
                        childColumns = "usercourse_id",
                        onDelete = ForeignKey.CASCADE
                ),
                @ForeignKey(

                )
        }
)
public class UserCourseFlag {
    public long usercourse_id;
    public long courseflag_id;
}
