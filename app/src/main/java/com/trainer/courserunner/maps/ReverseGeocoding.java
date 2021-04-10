package com.trainer.courserunner.maps;

import android.content.Context;

import androidx.core.util.Consumer;

import com.android.volley.RequestQueue;

public class ReverseGeocoding extends BaseGeocoding{
    public ReverseGeocoding(Context context, Consumer<String> resultConsumer) {
        super(context, resultConsumer);
    }
    public void requestReverseGeocoding(Double latitude,Double longtitude){
        String url="https://naveropenapi.apigw.ntruss.com/map-reversegeocode/v2/gc?coords=LATITUDE,LONGTITUDE";
        url=url.replace("LATITUDE",latitude.toString());
        url=url.replace("LONGTITUDE",longtitude.toString());
        requestUrl(url);
    }
}

//https://velog.io/@dlrmwl15/%EC%95%88%EB%93%9C%EB%A1%9C%EC%9D%B4%EB%93%9C-Volley%EB%A5%BC-%EC%9D%B4%EC%9A%A9%ED%95%9C-HTTP-%ED%86%B5%EC%8B%A0
