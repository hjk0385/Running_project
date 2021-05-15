package com.trainer.courserunner.Application;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.os.Build;

import com.trainer.courserunner.R;

enum GuideSound{
    FINISHKID,
    FINISHMAN,
    FINISHWOMAN
}

public class SoundManagerGuide {
    static private SoundManager soundManager;
    private SoundManagerGuide(){}

    static public void initSoundManager(Context context){
        SoundPool.Builder builder=new SoundPool.Builder();
        builder.setMaxStreams(1);
        builder.setAudioAttributes()
        SoundPool soundPool=builder.build();

        soundManager=new SoundManager(context,soundPool);
        soundManager.addSound(GuideSound.FINISHKID.ordinal(), R.raw.finish_kid);
        soundManager.addSound(GuideSound.FINISHMAN.ordinal(), R.raw.finish_man);
        soundManager.addSound(GuideSound.FINISHWOMAN.ordinal(), R.raw.finish_woman);
    }

    static public SoundManager getSoundManager(){
        return soundManager;
    }
}
