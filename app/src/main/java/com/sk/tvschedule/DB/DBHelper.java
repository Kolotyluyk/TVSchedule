package com.sk.tvschedule.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Сергій on 28.01.2017.
 */

 public class DBHelper extends SQLiteOpenHelper {

    public static final String dBName ="TVSchedule";


    public static final String tableCategory ="TVSchedule";
    public static final String tableChannel ="Category";
    public static final String tableProgram ="Program";
    public static final String tableFavorite ="Favorite";

    public static final String columnCategoryId ="id";
    public static final String columnCategoryTitle ="title";
    public static final String columnCategoryPicture ="picture";

    public static final String columnChannelId ="id";
    public static final String columnChannelName ="name";
    public static final String columnChannelCategorId ="category_id";
    public static final String columnChannelURL ="url";
    public static final String columnChannelPicture ="picture";


    public static final String columnProgramId ="id";
    public static final String columnProgramChannelId ="channel_id";
    public static final String columnProgramTitle ="title";
    public static final String columnProgramTime ="p_time";
    public static final String columnProgramDate ="p_date";
    public static final String columnProgramDescription ="description";

    public static final String columnFavoriteID ="id";
    public static final String columnFavoriteChannelID ="channel_id";







    public DBHelper(Context context) {
        // конструктор суперкласса
        super(context, dBName, null, 1);
    }



    public void onCreate(SQLiteDatabase db) {
        createProgram(db);
        createChannel(db);
        createFavorite(db);
        createCategory(db);

    }


   public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

   }


    public void createChannel(SQLiteDatabase db){
        db.execSQL("create table IF NOT EXISTS "+tableChannel+" ("
                +columnChannelId+ " integer primary key,"
                +columnChannelName+ " text,"
                +columnChannelURL+ " text,"
                +columnChannelCategorId+ " integer,"
                +columnChannelPicture+ " text" + ");");
    }

   public void createProgram(SQLiteDatabase db) {
      db.execSQL("create table IF NOT EXISTS "+tableProgram+" ("
              +columnProgramId+ " integer primary key autoincrement,"
              +columnProgramChannelId+" integer,"
              +columnProgramDate+" text,"
              +columnProgramTime+" text,"
              +columnProgramTitle+" text,"
              +columnProgramDescription+" text" + ");");
   }

    public void createCategory(SQLiteDatabase db) {
        db.execSQL("create table IF NOT EXISTS " + tableCategory + " ("
            + columnCategoryId + " integer primary key,"
            + columnCategoryTitle + " text,"
            + columnCategoryPicture + " text" + ");");
    }


    public void createFavorite(SQLiteDatabase db) {        db.execSQL("create table IF NOT EXISTS "+tableFavorite+" ("
            +columnFavoriteID+" integer primary key autoincrement,"
            +columnFavoriteChannelID+" integer"+ ");");
    }

    public void dropTable(SQLiteDatabase db,String table) {
        db.execSQL("DROP TABLE IF EXISTS " + table);

    }



}