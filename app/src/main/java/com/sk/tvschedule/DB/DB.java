package com.sk.tvschedule.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import com.sk.tvschedule.model.Category;
import com.sk.tvschedule.model.Channel;
import com.sk.tvschedule.model.Programs;
import static com.sk.tvschedule.DB.DBHelper.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Сергій on 30.01.2017.
 */

public class DB {


    private List<Category> categoryList;
    private List<Channel> channelList;
    private List<Programs> programList;
    private List<Integer> favoriteList;

    DBHelper dbHelper;


    public void setFavoriteList(List<Integer> favoriteList) {
        this.favoriteList = favoriteList;
    }


    public void setChannelList(List<Channel> channelList) {
        this.channelList = channelList;
    }


    public void setProgramList(List<Programs> programList) {
        this.programList = programList;
    }


    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    public DB(Context context) {

        dbHelper= new DBHelper(context);
    }







    public List<Category> getCategory() {

        categoryList=new ArrayList<>();
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        Cursor cursor = database.query(tableCategory, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            int id = cursor.getColumnIndex(columnCategoryId);
            int title= cursor.getColumnIndex(columnCategoryTitle);
            int picture= cursor.getColumnIndex(columnCategoryPicture);
            do {
                Category category=new Category();
                category.setId(cursor.getInt(id));
                category.setPicture(cursor.getString(picture));
                category.setTitle(cursor.getString(title));
                categoryList.add(category);

            } while (cursor.moveToNext());
        } else


            cursor.close();
        return categoryList;

    }


    public List<Channel> getChannel() {

        channelList=new ArrayList<>();
        SQLiteDatabase database = dbHelper.getWritableDatabase();

        Cursor cursor = database.query(tableChannel, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            int id = cursor.getColumnIndex(columnChannelId);
            int name= cursor.getColumnIndex(columnChannelName);
            int categoryId= cursor.getColumnIndex(columnChannelCategorId);
            int picture= cursor.getColumnIndex(columnChannelPicture);
            int url= cursor.getColumnIndex(columnChannelURL);
            do {
                Channel channel=new Channel();
                channel.setId(cursor.getInt(id));
                channel.setPicture(cursor.getString(picture));
                channel.setName(cursor.getString(name));
                channel.setUrl(cursor.getString(url));
                channel.setCategoryId(cursor.getInt(categoryId));
                channelList.add(channel);

            } while (cursor.moveToNext());
        } else


            cursor.close();
        return channelList;

    }

    public List<Programs> getProgram() {
        programList=new ArrayList<>();
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        Cursor cursor = database.query(tableProgram, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            int id = cursor.getColumnIndex(columnProgramId);
            int channeId = cursor.getColumnIndex(columnProgramChannelId);
            int date = cursor.getColumnIndex(columnProgramDate);
            int description = cursor.getColumnIndex(columnProgramDescription);
            int time = cursor.getColumnIndex(columnProgramTime);
            int title = cursor.getColumnIndex(columnProgramTitle);
            do {
                Programs programs=new Programs();
                programs.setChannelId(cursor.getInt(channeId));
                programs.setTitle(cursor.getString(title));
                programs.setDate(cursor.getString(date));
                programs.setDescription(cursor.getString(description));
                programs.setTime(cursor.getString(time));
                programList.add(programs);

            } while (cursor.moveToNext());
        } else


            cursor.close();
        return programList;

    }


    public List<Integer> getFavorite() {
        favoriteList=new ArrayList<>();
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        Cursor cursor = database.query(tableFavorite, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            int channelId = cursor.getColumnIndex(columnFavoriteChannelID);
            do {
                 int favoriteChannel=(cursor.getInt(channelId));
                 favoriteList.add(favoriteChannel);

            } while (cursor.moveToNext());
        } else

            cursor.close();
        return favoriteList;

    }




    public void saveProgram() {
        if (programList.size()!=0){
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            //    dbHelper.dropTable(db, tableProgram);
            dbHelper.createProgram(db);
            for (int i = 0; i < programList.size(); i++) {
                ContentValues cv = new ContentValues();
                //     cv.put(columnProgramId,i);
                cv.put(columnProgramChannelId, programList.get(i).getChannelId());
                cv.put(columnProgramTitle, programList.get(i).getTitle());
                cv.put(columnProgramDate, programList.get(i).getDate());
                cv.put(columnProgramTime, programList.get(i).getTime());
                cv.put(columnProgramDescription, programList.get(i).getDescription());
                db.insert(tableProgram, null, cv);
            }
        }
    }

    public void saveCategory() {
        if (categoryList.size()!=0){
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            //    dbHelper.dropTable(db, tableCategory);
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
            //    dbHelper.dropTable(db, tableChannel);
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

