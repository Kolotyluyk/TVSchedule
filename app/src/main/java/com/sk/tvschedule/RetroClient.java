package com.sk.tvschedule;

import com.sk.tvschedule.Service.ApiService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Сергій on 27.01.2017.
 */

public class RetroClient {


    private static final String Base_URL="http://52.50.138.211:8080/ChanelAPI";

    private static Retrofit getRetrofitInstace(){
        return new Retrofit.Builder()
                .baseUrl(Base_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static ApiService getService(){
        return getRetrofitInstace().create(ApiService.class);
    }
}
