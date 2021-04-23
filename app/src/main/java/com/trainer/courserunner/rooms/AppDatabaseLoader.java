package com.trainer.courserunner.rooms;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

/*
     중간발표 이후에 개선할 내용
    1. 메인쓰레드에서 작동시키는 대신에 다음과 같은 형태로 변경한다.
    1.1 기본 : 코루틴으로 변경
    1.2 지속적으로 호출하는 경우(CourseOverseer) : AsyncTask
*/

public class AppDatabaseLoader {
    static private AppDatabase appDatabase;

    static public void initAppdatabase(Context context) {
        appDatabase = Room.databaseBuilder(context, AppDatabase.class, "testdatabase102").allowMainThreadQueries().build();
    }

    static public AppDatabase getAppDatabase() {
        return appDatabase;
    }
}
