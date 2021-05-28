package com.trainer.courserunner.Application.bandsupport;

import android.content.Context;

public class MiBandFunctionSupporter extends FunctionSupporter implements BandFunctionSupport{
    public MiBandFunctionSupporter(Context context) {
        super(context);
    }

    @Override
    public int getHeartRate() {
        return 0;
    }

    @Override
    public void alarm() {

    }

    @Override
    public int getStep() {
        return 0;
    }
}
