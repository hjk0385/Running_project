package com.trainer.courserunner.rooms;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "usermapflag",
        foreignKeys = {
                @ForeignKey(
                        entity = MapFlag.class,
                        parentColumns = "mapflag_id",
                        childColumns = "mapflag_id",
                        onDelete = ForeignKey.CASCADE)
        })
public class UserMapFlag {
    @PrimaryKey(autoGenerate = true)
    public long usermapflag_id;
    public long mapflag_id;
}
