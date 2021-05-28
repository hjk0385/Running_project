package com.trainer.courserunner.Application.bandsupport;

import android.content.Context;

public class GalaxyFitFunctionSupporter extends FunctionSupporter implements BandFunctionSupport{
    public GalaxyFitFunctionSupporter(Context context) {
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
