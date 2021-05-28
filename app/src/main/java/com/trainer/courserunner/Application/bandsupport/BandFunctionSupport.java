package com.trainer.courserunner.Application.bandsupport;

public interface BandFunctionSupport extends BaseFunctionSupport{
    /*
        심박수
        알람
    */
    int getHeartRate();
    void alarm();
}
