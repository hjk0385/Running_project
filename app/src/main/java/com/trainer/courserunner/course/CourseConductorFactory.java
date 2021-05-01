package com.trainer.courserunner.course;

public class CourseConductorFactory {
    public CourseConductor createCouseConductor(String modetype) {
        switch (modetype) {
            case "run":
                //create를 재정의한 매소드들을 사용해서 구현한다.


                return new CourseConductor();
            default:
                return null;
        }
    }
}
