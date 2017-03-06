package com.sk.tvschedule.Service;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;

import com.sk.tvschedule.DB.AsynTaskLoadToDB;
import com.sk.tvschedule.api.ApiService;
import com.sk.tvschedule.api.RetroClient;
import com.sk.tvschedule.data.Data;
import com.sk.tvschedule.event.BusStationMain;
import com.sk.tvschedule.event.EvenrLoadedData;
import com.sk.tvschedule.model.Category;
import com.sk.tvschedule.model.Channel;
import com.sk.tvschedule.model.Programs;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class RestrofitService extends IntentService {

    private Context mContext;

    public RestrofitService() {
        super("load");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        loadInformation();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mContext = getApplicationContext();


        return super.onStartCommand(intent, flags, startId);





    }



    public void loadInformation(){


        final Data data=Data.getInstance();
        ApiService api = RetroClient.getService();


        Call<List<Channel>> callChannel= api.getJSONChannel();
        callChannel.enqueue(new Callback<List<Channel>>() {
            @Override
            public void onResponse(Call<List<Channel>> call, Response<List<Channel>> response) {
                if (response.isSuccessful()){
                    data.setChannelList(response.body());
                    AsynTaskLoadToDB insert=new AsynTaskLoadToDB();
                    insert.setContext(getApplicationContext());
                    insert.execute(1);



                }
            }
            @Override
            public void onFailure(Call<List<Channel>> call, Throwable t) {

            }
        });
        Call<List<Category>> callCategory= api.getJSONCategory();
        callCategory.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                if (response.isSuccessful()){
                    data.setCategoryList(response.body());
                    AsynTaskLoadToDB insert=new AsynTaskLoadToDB();
                    insert.setContext(getApplicationContext());
                    insert.execute(0);

                }
            }
            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {

            }
        });



        Call<List<Programs>> callProgram= api.getJSONPrograms( System.currentTimeMillis());
        callProgram.enqueue(new Callback<List<Programs>>() {
            @Override
            public void onResponse(Call<List<Programs>> call, Response<List<Programs>> response) {
                if (response.isSuccessful()){
                    List<Programs>  categoryList=  response.body();



                    data.setProgramList(response.body());
                    AsynTaskLoadToDB insert=new AsynTaskLoadToDB();
                    insert.setContext(getApplicationContext());
                    insert.execute(2);
                    BusStationMain.getBus().post(new EvenrLoadedData());


                }
            }
            @Override
            public void onFailure(Call<List<Programs>> call, Throwable t) {

            }



        });

    }

}

