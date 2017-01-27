package com.sk.tvschedule.Service;

import com.sk.tvschedule.model.Category;
import com.sk.tvschedule.model.Channel;
import com.sk.tvschedule.model.Programs;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Сергій on 27.01.2017.
 */

public interface ApiService {

    @GET("/categories")
    Call<Category> getMyJSONCategory();

    @GET("/chanels")
    Call<Channel> getMyJSONChannel();

    @GET("/programs/{time}")
    Call<Programs> getMyJSONPrograms(@Path("time") int time);


}
