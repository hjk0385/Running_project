package com.trainer.courserunner.course.maker.scopetype;

import android.graphics.Bitmap;
import android.graphics.Color;

//병목현상 발생-> 멀티쓰레드로 최적화

public class ScopeDotsImage extends ScopeDots {
    private static final Integer defaultPrecision = 100;//100x100

    public ScopeDotsImage(Bitmap image) {
        image = Bitmap.createScaledBitmap(image, defaultPrecision, defaultPrecision, true);
        for (Integer x = 0; x < image.getWidth(); x++) {
            for (Integer y = 0; y < image.getHeight(); y++) {
                Integer rgb = image.getPixel(x, y);
                Integer red = Color.red(rgb);
                Integer green = Color.green(rgb);
                Integer blue = Color.blue(rgb);

                Integer colorLimit = 100;
                if (red <= colorLimit || green <= colorLimit || blue <= colorLimit) {
                    scopeDotList.add(new ScopeDotPixel(image.getWidth(), image.getHeight(), x, y));
                }
            }
        }
    }
}
