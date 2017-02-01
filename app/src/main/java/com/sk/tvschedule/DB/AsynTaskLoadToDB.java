package com.sk.tvschedule.DB;

import android.os.AsyncTask;

import com.sk.tvschedule.data.Data;

import android.content.Context;

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
                    db.setCategoryList(data.getCategoryList());
                    db.saveCategory();
                    break;

                case 1:
                    db.setChannelList(data.getChannelList());
                    db.saveChannel();
                    break;

                case 2:
                    db.setProgramList(data.getProgramList());
                    db.saveProgram();
                    break;

                case 3:
                    db.setFavoriteList(data.getFavoriteList());
                    db.saveFavorite();
                    break;
            }

        return null;
    }
}
