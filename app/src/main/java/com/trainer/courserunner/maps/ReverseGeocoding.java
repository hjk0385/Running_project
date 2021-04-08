package com.trainer.courserunner.maps;

import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ReverseGeocoding {
    public static String getJsonData(double Latitude,double Longtitude){

    }

    private static String downloadUrl(String strUrl) throws IOException{
        String s=null;
        byte[] buffer = new byte[1024];
        InputStream iStream=null;
        try{
            URL url = new URL(strUrl);
            HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
            //설정
            urlConnection.setRequestMethod("GET");
            

            urlConnection.connect();
            iStream=urlConnection.getInputStream();
            iStream.read(buffer);
            s=new String(buffer);
        }
        catch (Exception e){
            Log.d("Download Error",e.toString());
        }
        finally {
            iStream.close();
        }
        return s;
    }
}
