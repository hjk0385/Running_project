package com.trainer.courserunner.course.sounder;

public class SoundCommandCourseGuide implements SoundCommand {
    int flagCount;
    int passFlagCount;

    public SoundCommandCourseGuide(int flagCount, int passFlagCount) {
        this.flagCount = flagCount;
        this.passFlagCount = passFlagCount;
    }

    @Override
    public void execute() {
        //25,50,75,100에 소리 발생
        double onePercentage = ((double) passFlagCount / (double) flagCount) * 100;
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
