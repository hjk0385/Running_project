package com.trainer.courserunner.rooms;

import androidx.room.Insert;

public interface UserLocationPathDao {
    @Insert
    public long insertUserLocationPath(UserLocationPath userLocationPath);
}
