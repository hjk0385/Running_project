package com.trainer.courserunner.Application;

import android.app.Application;
import android.os.AsyncTask;

import com.trainer.courserunner.Application.mapdb.RoadAddressConnector;
import com.trainer.courserunner.Application.rooms.AppDatabase;
import com.trainer.courserunner.Application.rooms.AppDatabaseConnector;
import com.trainer.courserunner.Application.sound.SoundManagerGuide;


public class ApplicationBoot extends Application {
    public void onCreate() {
        super.onCreate();
        AppDatabaseConnector.initappDatabaseConnection(this);
        SoundManagerGuide.initSoundManager(this);
    }

}
