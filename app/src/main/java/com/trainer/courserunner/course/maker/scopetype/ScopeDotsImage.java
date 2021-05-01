package com.trainer.courserunner.course.maker.scopetype;

import android.graphics.Bitmap;
import android.graphics.Color;

//병목현상 발생-> 멀티쓰레드로 최적화

public class ScopeDotsImage extends ScopeDots {
    private static final int defaultPrecision = 100;//100x100

    public ScopeDotsImage(Bitmap image) {
        image = Bitmap.createScaledBitmap(image, defaultPrecision, defaultPrecision, true);
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                int rgb = image.getPixel(x, y);
                int red = Color.red(rgb);
                int green = Color.green(rgb);
                int blue = Color.blue(rgb);

                int colorLimit = 100;
                if (red <= colorLimit || green <= colorLimit || blue <= colorLimit) {
                    scopeDotList.add(new ScopeDotPixel(image.getWidth(), image.getHeight(), x, y));
                }
            }
        }
    }
}