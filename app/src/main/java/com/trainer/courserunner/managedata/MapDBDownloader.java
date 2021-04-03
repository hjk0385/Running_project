package com.trainer.courserunner.managedata;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class MapDBDownloader extends MapDBInfo {
    static public void download(AssetManager assetManager, Context context) {
        InputStream mapDBAssetInput = null;
        OutputStream mapDBOutput = null;
        try {
            mapDBAssetInput = assetManager.open(mapDBAssetLocation);
            mapDBOutput = context.openFileOutput(mapDBAssetLocation,Context.MODE_PRIVATE);
            copyFile(mapDBAssetInput, mapDBOutput);
            mapDBAssetInput.close();
            mapDBOutput.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static private void copyFile(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int read;
        while ((read = in.read(buffer)) != -1) {
            out.write(buffer, 0, read);
        }
    }
}
