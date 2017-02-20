package com.sk.tvschedule.DB;

import android.os.AsyncTask;

import com.sk.tvschedule.data.Data;
import com.sk.tvschedule.model.Category;
import com.sk.tvschedule.model.Channel;
import com.sk.tvschedule.model.Programs;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by Сергій on 01.02.2017.
 */

public class AsynTaskLoadToDB extends AsyncTask<Integer,Void,Void> {


    private Context context;

    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    protected Void doInBackground(Integer... params) {

        Data data=Data.getInstance();
        DB db=new DB(context);



        int task= params[0];
            switch (task){
                case 0:
                    db.saveCategory((ArrayList<Category>) data.getCategoryList());
                    break;

                case 1:
                //    db.setChannelList();
                    db.saveChannel((ArrayList<Channel>) data.getChannelList());
                    break;

                case 2:
              //      db.setProgramList();
                    db.saveProgram((ArrayList<Programs>) data.getProgramList());
                    break;

                case 3:
                //    db.setFavoriteList();
                    db.saveFavorite((ArrayList<Integer>) data.getFavoriteList());
                    break;
            }

        return null;
    }
}
