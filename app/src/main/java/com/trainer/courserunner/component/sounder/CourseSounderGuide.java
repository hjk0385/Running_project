package com.trainer.courserunner.component.sounder;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.trainer.courserunner.Application.rooms.AppDatabase;
import com.trainer.courserunner.Application.rooms.AppDatabaseConnector;
import com.trainer.courserunner.Application.rooms.UserCourseFlagDerived;
import com.trainer.courserunner.Application.sound.GuideSound;
import com.trainer.courserunner.Application.sound.VoiceType;
import com.trainer.courserunner.ExerciseResultsActivity;
import com.trainer.courserunner.component.CourseComponent;

import java.util.Objects;

import static androidx.core.content.ContextCompat.startActivity;

public class CourseSounderGuide extends CourseComponent {
    Context context;
    Long courseId;
    Long userCoursedId;
    boolean percentFlag25;
    boolean percentFlag50;
    boolean percentFlag75;
    boolean percentFlag100;


    public CourseSounderGuide(Long courseId, Long userCoursedId, Context context) {
        this.courseId = courseId;
        this.userCoursedId = userCoursedId;
        this.context = context;

        percentFlag25 = false;
        percentFlag50 = false;
        percentFlag75 = false;
        percentFlag100 = false;
    }

    @Override
    protected Object runInWorkThread() {
        /*
            0~100 : 25, 50, 75, 100
            0~50 : 12.5, 25, 37.5 ,50
            0~1 : 0.25, 0.5, 0.75 1
            0~n : (int)0.25*n 0.5*n 0.75*n 1*n

            ex)
            0~12 : 3, 6, 9 ,12
        */

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        String voiceTypeData = prefs.getString("sound_list", "여성 목소리");
        VoiceType voiceType = VoiceType.FEMALE;
        if (Objects.equals(voiceTypeData, "여성 목소리")) {
            voiceType = VoiceType.FEMALE;
        } else if (Objects.equals(voiceTypeData, "남성 목소리")) {
            voiceType = VoiceType.MALE;
        } else if (Objects.equals(voiceTypeData, "아이 목소리")) {
            voiceType = VoiceType.CHILD;
        }

        AppDatabase appDatabase = AppDatabaseConnector.getAppDatabaseConnection();
        int flagCount = appDatabase.courseFlagDao().getCountCourseMarkerFlags(courseId);
        int passedFlagCount = UserCourseFlagDerived.getCountUnvistedUserCourseFlags(courseId, userCoursedId);

        if (passedFlagCount == (int) ((double) flagCount * 0.25)) {
            //25%지점
            if (!percentFlag25) {
                /*
                //처음으로 지나가는 경우
                percentFlag25 = true;
                if (voiceType == VoiceType.MALE) {
                    return new SoundCommandGuide(GuideSound.FINISHMAN);
                } else if (voiceType == VoiceType.FEMALE) {
                    return new SoundCommandGuide(GuideSound.FINISHWOMAN);
                } else if (voiceType == VoiceType.CHILD) {
                    return new SoundCommandGuide(GuideSound.FINISHKID);
                }

                 */
            }

        } else if (passedFlagCount == (int) ((double) flagCount * 0.50)) {
            //50%지점
            if (!percentFlag50) {
                //처음으로 지나가는 경우
                /*
                percentFlag50 = true;
                if (voiceType == VoiceType.MALE) {
                    return new SoundCommandGuide(GuideSound.FINISHMAN);
                } else if (voiceType == VoiceType.FEMALE) {
                    return new SoundCommandGuide(GuideSound.FINISHWOMAN);
                } else if (voiceType == VoiceType.CHILD) {
                    return new SoundCommandGuide(GuideSound.FINISHKID);
                }

                 */
            }
        } else if (passedFlagCount == (int) ((double) flagCount * 0.75)) {
            //75%지점
            if (!percentFlag75) {
                /*
                //처음으로 지나가는 경우
                percentFlag75 = true;
                if (voiceType == VoiceType.MALE) {
                    return new SoundCommandGuide(GuideSound.FINISHMAN);
                } else if (voiceType == VoiceType.FEMALE) {
                    return new SoundCommandGuide(GuideSound.FINISHWOMAN);
                } else if (voiceType == VoiceType.CHILD) {
                    return new SoundCommandGuide(GuideSound.FINISHKID);
                }

                 */
            }
        } else if (passedFlagCount == (int) ((double) flagCount * 1.0)) {
            //100%지점
            if (!percentFlag100) {
                //처음으로 지나가는 경우
                percentFlag100 = true;
                if (voiceType == VoiceType.MALE) {
                    return new SoundCommandGuide(GuideSound.FINISHMAN);
                } else if (voiceType == VoiceType.FEMALE) {
                    return new SoundCommandGuide(GuideSound.FINISHWOMAN);
                } else if (voiceType == VoiceType.CHILD) {

                    return new SoundCommandGuide(GuideSound.FINISHKID);
                }
            }
        }
        return null;
    }

    protected void runInUiThread(Object object) {
        super.runInUiThread(object);
        if (object != null) {
            ((SoundCommand) object).execute();
            Intent intent = new Intent(context, ExerciseResultsActivity.class);
            context.startActivities(new Intent[]{intent});
        }
    }

}
