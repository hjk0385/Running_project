package com.trainer.courserunner.maps;

import android.content.Context;

import androidx.core.util.Consumer;

public class Geocoding extends BaseGeocoding{
    public Geocoding(Context context, Consumer<String> resultConsumer) {
        super(context, resultConsumer);
    }
    public void requestGeocoding(String address){
        String url="https://naveropenapi.apigw.ntruss.com/map-geocode/v2/geocode?query=ADDRESS&output=json";
        url=url.replace("ADDRESS",address);
        requestUrl(url);
    }
}
