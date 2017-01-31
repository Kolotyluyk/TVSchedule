package com.sk.tvschedule.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.sk.tvschedule.DB.DBHelper;
import com.sk.tvschedule.model.Category;
import com.sk.tvschedule.model.Channel;
import com.sk.tvschedule.model.Programs;

import java.util.List;

import static com.sk.tvschedule.DB.DBHelper.*;


public class SetData extends Thread {

    List<Category> categoryList;


    List<Channel> channelList;
    List<Programs> programList;
    List<Integer> favoriteList;


    DBHelper dbHelper;
    int numb;

    public List<Integer> getFavoriteList() {
        return favoriteList;
    }

    public void setFavoriteList(List<Integer> favoriteList) {
        this.favoriteList = favoriteList;
    }

    public List<Channel> getChannelList() {
        return channelList;
    }

    public void setChannelList(List<Channel> channelList) {
        this.channelList = channelList;
    }

    public List<Programs> getProgramList() {
        return programList;
    }

    public void setProgramList(List<Programs> programList) {
        this.programList = programList;
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }



    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    public SetData( Context context,int numberOfTable) {
        dbHelper= new DBHelper(context);
        numb=numberOfTable;


    }

    @Override
    public void run() {
        switch (numb){
            case 0: saveCategory();
                break;

            case 1: saveChannel();
                break;

            case 2:saveProgram();
                break;

            case 3:saveFavorite();
                break;
        }

    }

    public void saveProgram() {
        if (programList.size()!=0){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        dbHelper.dropTable(db, tableProgram);
            dbHelper.createProgram(db);
        for (int i = 0; i < programList.size(); i++) {
            ContentValues cv = new ContentValues();
       //     cv.put(columnProgramId,i);
            cv.put(columnProgramChannelId, programList.get(i).getChannelId());
            cv.put(columnProgramTitle, programList.get(i).getTitle());
            cv.put(columnProgramDate, programList.get(i).getDate());
            cv.put(columnProgramTime, programList.get(i).getTime());
            cv.put(columnProgramDescription, programList.get(i).getDescription());
            long rowID = db.insert(tableProgram, null, cv);
        }
        }
    }

    public void saveCategory() {
        if (categoryList.size()!=0){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        dbHelper.dropTable(db, tableCategory);
            dbHelper.createCategory(db);
        for (int i=0;i<categoryList.size();i++) {
            ContentValues cv = new ContentValues();
            cv.put(columnCategoryId, categoryList.get(i).getId());
            cv.put(columnCategoryTitle, categoryList.get(i).getTitle());
            cv.put(columnCategoryPicture, categoryList.get(i).getPicture());
            long rowID = db.insert(tableCategory, null, cv);
        }
    }
    }


    public void saveChannel() {
        if (channelList.size()!=0){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        dbHelper.dropTable(db, tableChannel);
            dbHelper.createChannel(db);
        for (int i=0;i<channelList.size();i++){
            ContentValues cv = new ContentValues();
            cv.put(columnChannelCategorId,channelList.get(i).getCategoryId());
            cv.put(columnChannelPicture,channelList.get(i).getPicture());
            cv.put(columnChannelName,channelList.get(i).getName());
            cv.put(columnChannelURL,channelList.get(i).getUrl());
            cv.put(columnChannelId,channelList.get(i).getId());
            long rowID = db.insert(tableChannel, null, cv);

        }
    }
    }

    public void saveFavorite() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        dbHelper.dropTable(db, tableFavorite);
        for (int i = 0; i < favoriteList.size(); i++) {
            ContentValues cv = new ContentValues();
            cv.put(columnFavoriteChannelID, favoriteList.get(i));
            long rowID = db.insert(tableChannel, null, cv);
        }
    }
}





