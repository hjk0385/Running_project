package com.trainer.courserunner.course.component.sounder;

public class SoundCommandCourseFlag implements SoundCommand {
    int flagCount;
    int passFlagCount;

    public SoundCommandCourseFlag(int flagCount, int passFlagCount) {
        this.flagCount = flagCount;
        this.passFlagCount = passFlagCount;
    }

    @Override
    public void execute() {
        //25,50,75,100에 소리 발생
        double onePercentage = ((double) passFlagCount / (double) flagCount);
        if ((int) (onePercentage * 25) == passFlagCount) {
            //25% sound
            //new SoundPool.Builder().setAudioAttributes().build().play();
        } else if ((int) (onePercentage * 50) == passFlagCount) {
            //50% sound
            //new SoundPool.Builder().setAudioAttributes().build().play();
        } else if ((int) (onePercentage * 75) == passFlagCount) {
            //70% sound
            //new SoundPool.Builder().setAudioAttributes().build().play();
        }
    }
}