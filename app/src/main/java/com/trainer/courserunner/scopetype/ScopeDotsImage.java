package com.trainer.courserunner.scopetype;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Rect;

import java.io.InputStream;
import java.util.List;

public class ScopeDotsImage extends ScopeDots {
    private static final int defaultPrecision=100;//100x100
    public ScopeDotsImage(Bitmap image) {
        image=Bitmap.createScaledBitmap(image, defaultPrecision, defaultPrecision, true);
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
