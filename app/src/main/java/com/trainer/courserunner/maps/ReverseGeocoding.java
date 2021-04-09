package com.trainer.courserunner.maps;

import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class ReverseGeocoding {
    public static String getJsonData(Double latitude,Double longtitude){
        String requestUrl= "https://naveropenapi.apigw.ntruss.com/map-reversegeocode/v2/gc" +
                "?coords={입력_좌표}&sourcecrs={좌표계}&orders={변환_작업_이름}&output={출력_형식}";
        requestUrl=requestUrl.replace("입력_좌표",latitude.toString()+","+longtitude.toString());
        requestUrl=requestUrl.replace("좌표계","epsg:4326");
        requestUrl=requestUrl.replace("변환_작업_이름","epsg:4326");
        requestUrl=requestUrl.replace("출력_형식","json");
        String geoJson=null;
        try {
            geoJson = ReverseGeocoding.downloadUrl(requestUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return geoJson;
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
            urlConnection.setRequestProperty("X-NCP-APIGW-API-KEY-ID","hzqct6qd8n");
            urlConnection.setRequestProperty("X-NCP-APIGW-API-KEY","");
            urlConnection.connect();
            iStream=urlConnection.getInputStream();
            iStream.read(buffer);
            s=new String(buffer);
            Log.v("TEMPTEMP",s);
        }
        catch (Exception e){
            Log.d("Download Error",e.toString());
        }
        finally {
            if(iStream!=null) {
                iStream.close();
            }
        }
        return s;
    }
}
