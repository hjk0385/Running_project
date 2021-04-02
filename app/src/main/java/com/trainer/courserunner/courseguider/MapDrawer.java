package com.trainer.courserunner.courseguider;

import androidx.core.util.Supplier;

public interface MapDrawer {
    void drawpath(double startLongitude, double startLatitude,
                  double endLongitude, double endLatitude,
                  Supplier<String> overlayType);
    void drawUserPath(double startLongitude, double startLatitude,
                      double endLongitude, double endLatitude);
    void drawUserLogPath(double startLongitude, double startLatitude,
                         double endLongitude, double endLatitude);
}
