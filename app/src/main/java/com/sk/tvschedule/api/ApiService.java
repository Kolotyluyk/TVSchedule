package com.sk.tvschedule.api;

import com.sk.tvschedule.model.Category;
import com.sk.tvschedule.model.Channel;
import com.sk.tvschedule.model.Programs;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Сергій on 27.01.2017.
 */

public interface ApiService {

    @GET("categories")
    Call<List<Category>> getJSONCategory();

    @GET("chanels")
    Call<List<Channel>> getJSONChannel();

    @GET("programs/{time}")
    Call<List<Programs>> getJSONPrograms(@Path("time") long time);


}
