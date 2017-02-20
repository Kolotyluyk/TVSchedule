package com.sk.tvschedule.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;

import java.util.HashMap;

/**
 * Created by Сергій on 04.02.2017.
 */

public class Provider extends ContentProvider {
    private static final int DATABASE_VERSION = 1;

  /*  private static HashMap<String, String> sCategoryProjectionMap;
    private static HashMap<String, String> sChannelProjectionMap;
    private static HashMap<String, String> sProgramProjectionMap;
    private static HashMap<String, String> sFavoriteProjectionMap;
*/

    private static final int CATEGORY = 1;
    private static final int CATEGORY_ID = 2;
    private static final int CHANNEL = 3;
    private static final int CHANNEL_ID = 4;
    private static final int PROGRAM = 5;
    private static final int PROGRAM_ID = 6;
    private static final int FAVORITE = 7;
    private static final int FAVORITE_ID = 8;
    private static final UriMatcher sUriMatcher;
    private DatabaseHelper dbHelper;


    static {
        sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        sUriMatcher.addURI(ContractClass.AUTHORITY, ContractClass.Category.PATH_CATEGORY, CATEGORY);
        sUriMatcher.addURI(ContractClass.AUTHORITY, ContractClass.Category.PATH_CATEGORY+"/#", CATEGORY_ID);

        sUriMatcher.addURI(ContractClass.AUTHORITY, ContractClass.Channel.PATH_Channel, CHANNEL);
        sUriMatcher.addURI(ContractClass.AUTHORITY, ContractClass.Channel.PATH_Channel_ID+"/#", CHANNEL_ID);

        sUriMatcher.addURI(ContractClass.AUTHORITY, ContractClass.Favorite.PATH_Favorite, FAVORITE);
        sUriMatcher.addURI(ContractClass.AUTHORITY, ContractClass.Favorite.PATH_Favorite_ID+"/#", FAVORITE_ID);


        sUriMatcher.addURI(ContractClass.AUTHORITY, ContractClass.Program.PATH_Program, PROGRAM);
        sUriMatcher.addURI(ContractClass.AUTHORITY, ContractClass.Program.PATH_Program_ID+"/#", PROGRAM_ID);


    }

    private static class DatabaseHelper extends SQLiteOpenHelper {
        private static final String DATABASE_NAME = "TVSchedule";
        public static final String DATABASE_TABLE_CATEGORY = ContractClass.Category.tableCategory;
        public static final String DATABASE_TABLE_CHANNEL = ContractClass.Channel.tableChannel;
        public static final String DATABASE_TABLE_PROGRAM = ContractClass.Program.tableProgram;
        public static final String DATABASE_TABLE_FAVORITE = ContractClass.Favorite.tableFavorite;



        public static final String columnCategoryId =ContractClass.Category.columnCategoryId;
        public static final String columnCategoryTitle =ContractClass.Category.columnCategoryTitle;
        public static final String columnCategoryPicture =ContractClass.Category.columnCategoryPicture;

        public static final String columnChannelId =ContractClass.Channel.columnChannelId;
        public static final String columnChannelName =ContractClass.Channel.columnChannelName;
        public static final String columnChannelCategorId =ContractClass.Channel.columnChannelCategorId;
        public static final String columnChannelURL =ContractClass.Channel.columnChannelURL;
        public static final String columnChannelPicture =ContractClass.Channel.columnChannelPicture;


        public static final String columnProgramId =ContractClass.Program.columnProgramId;
        public static final String columnProgramChannelId =ContractClass.Program.columnProgramChannelId;
        public static final String columnProgramTitle =ContractClass.Program.columnProgramTitle;
        public static final String columnProgramTime =ContractClass.Program.columnProgramTime;
        public static final String columnProgramDate =ContractClass.Program.columnProgramDate;
        public static final String columnProgramDescription =ContractClass.Program.columnProgramDescription;

        public static final String columnFavoriteID =ContractClass.Favorite.columnFavoriteID;
        public static final String columnFavoriteChannelID =ContractClass.Favorite.columnFavoriteChannelID;



        @Override
        public void onCreate(SQLiteDatabase db) {
            createProgram(db);
            createChannel(db);
            createFavorite(db);
            createCategory(db);

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_CATEGORY);
            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_PROGRAM);
            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_CHANNEL);
            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_FAVORITE);

            onCreate(db);
        }



        public void createChannel(SQLiteDatabase db){
            db.execSQL("create table IF NOT EXISTS "+DATABASE_TABLE_CHANNEL+" ("
                    +columnChannelId+ " integer primary key,"
                    +columnChannelName+ " text,"
                    +columnChannelURL+ " text,"
                    +columnChannelCategorId+ " integer,"
                    +columnChannelPicture+ " text" + ");");
        }

        public void createProgram(SQLiteDatabase db) {
            db.execSQL("create table IF NOT EXISTS "+DATABASE_TABLE_PROGRAM+" ("
                    +columnProgramId+ " integer primary key autoincrement,"
                    +columnProgramChannelId+" integer,"
                    +columnProgramDate+" text,"
                    +columnProgramTime+" text,"
                    +columnProgramTitle+" text,"
                    +columnProgramDescription+" text" + ");");
        }

        public void createCategory(SQLiteDatabase db) {
            db.execSQL("create table IF NOT EXISTS " + DATABASE_TABLE_CATEGORY + " ("
                    + columnCategoryId + " integer primary key,"
                    + columnCategoryTitle + " text,"
                    + columnCategoryPicture + " text" + ");");
        }


        public void createFavorite(SQLiteDatabase db) {
            db.execSQL("create table IF NOT EXISTS "+DATABASE_TABLE_FAVORITE+" ("
                +columnFavoriteID+" integer primary key autoincrement,"
                +columnFavoriteChannelID+" integer"+ ");");
        }

        private Context ctx;
        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            ctx = context;
        }

    }


    @Override
    public int delete(Uri uri, String where, String[] whereArgs) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String finalWhere;
        int count;
        switch (sUriMatcher.match(uri)) {
            case CATEGORY:
                count = db.delete(ContractClass.Category.tableCategory,where,whereArgs);
                break;
            case CATEGORY_ID:
                finalWhere = ContractClass.Category._ID + " = " + uri.getPathSegments().get(ContractClass.Category.CATEGORY_ID_PATH_POSITION);
                if (where != null) {
                    finalWhere = finalWhere + " AND " + where;
                }
                count = db.delete(ContractClass.Category.tableCategory,finalWhere,whereArgs);
                break;
            case CHANNEL:
                count = db.delete(ContractClass.Channel.tableChannel,where,whereArgs);
                break;
            case CHANNEL_ID:
                finalWhere = ContractClass.Channel._ID + " = " + uri.getPathSegments().get(ContractClass.Channel.CHANNEL_ID_PATH_POSITION);
                if (where != null) {
                    finalWhere = finalWhere + " AND " + where;
                }
                count = db.delete(ContractClass.Channel.tableChannel,finalWhere,whereArgs);
                break;
            case PROGRAM:
                count = db.delete(ContractClass.Program.tableProgram,where,whereArgs);
                break;
            case PROGRAM_ID:
                finalWhere = ContractClass.Program._ID + " = " + uri.getPathSegments().get(ContractClass.Program.PROGRAM_ID_PATH_POSITION);
                if (where != null) {
                    finalWhere = finalWhere + " AND " + where;
                }
                count = db.delete(ContractClass.Program.tableProgram,finalWhere,whereArgs);
                break;
            case FAVORITE:
                count = db.delete(ContractClass.Favorite.tableFavorite,where,whereArgs);
                break;
            case FAVORITE_ID:
                finalWhere = ContractClass.Favorite._ID + " = " + uri.getPathSegments().get(ContractClass.Favorite.FAVORITE_ID_PATH_POSITION);
                if (where != null) {
                    finalWhere = finalWhere + " AND " + where;
                }
                count = db.delete(ContractClass.Favorite.tableFavorite,finalWhere,whereArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    @Override
    public String getType(Uri uri) {
        switch (sUriMatcher.match(uri)) {
            case CATEGORY:
                return ContractClass.Category.CONTENT_TYPE;
            case CATEGORY_ID:
                return ContractClass.Category.CONTENT_ITEM_TYPE;
            case CHANNEL:
                return ContractClass.Channel.CONTENT_TYPE;
            case CHANNEL_ID:
                return ContractClass.Channel.CONTENT_ITEM_TYPE;
            case PROGRAM:
                return ContractClass.Program.CONTENT_TYPE;
            case PROGRAM_ID:
                return ContractClass.Program.CONTENT_ITEM_TYPE;
            case FAVORITE:
                return ContractClass.Favorite.CONTENT_TYPE;
            case FAVORITE_ID:
                return ContractClass.Favorite.CONTENT_ITEM_TYPE;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues initialValues) {
     if (
                        sUriMatcher.match(uri) != CATEGORY
                        &&
                        sUriMatcher.match(uri) != CHANNEL
                        &&
                        sUriMatcher.match(uri) != PROGRAM
                        &&
                        sUriMatcher.match(uri) != FAVORITE
                ) {
            throw new IllegalArgumentException("Unknown URI " + uri);
        }

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values;
        if (initialValues != null) {
            values = new ContentValues(initialValues);
        }
        else {
            values = new ContentValues();
        }
        long rowId = -1;
        Uri rowUri=null ;
        switch (sUriMatcher.match(uri)) {

            case CATEGORY:
                   rowId = db.insert(ContractClass.Category.tableCategory, null, values);
                if (rowId > 0) {
                    rowUri = ContentUris.withAppendedId(ContractClass.Category.CONTENT_URI, rowId);
                    getContext().getContentResolver().notifyChange(rowUri, null);
                }
                break;
            case CHANNEL:
                  rowId = db.insert(ContractClass.Channel.tableChannel, null, values);
                if (rowId > 0) {
                    rowUri = ContentUris.withAppendedId(ContractClass.Channel.CONTENT_URI, rowId);
                    getContext().getContentResolver().notifyChange(rowUri, null);
                }

                break;
            case PROGRAM:
           rowId = db.insert(ContractClass.Program.tableProgram, null, values);
                if (rowId > 0) {
                    rowUri = ContentUris.withAppendedId(ContractClass.Program.CONTENT_URI, rowId);
                    getContext().getContentResolver().notifyChange(rowUri, null);
                }

                break;
            case FAVORITE:
                  rowId = db.insert(ContractClass.Favorite.tableFavorite, null, values);
                if (rowId > 0) {
                    rowUri = ContentUris.withAppendedId(ContractClass.Favorite.CONTENT_URI, rowId);
                    getContext().getContentResolver().notifyChange(rowUri, null);
                }
                break;
        }
        return rowUri;
    }


    @Override
    public boolean onCreate() {
        dbHelper = new DatabaseHelper(getContext());
        return true;
    }



    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
    //    SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        String orderBy = null;
        Cursor cursor=null;
        SQLiteDatabase db;
        switch (sUriMatcher.match(uri)) {
            case CATEGORY:
                 orderBy = ContractClass.Category.DEFAULT_SORT_ORDER;
                db = dbHelper.getReadableDatabase();
                cursor = db.query(ContractClass.Category.tableCategory, projection, selection, selectionArgs, null, null, orderBy);
           //     cursor.moveToFirst();
                break;
            case CATEGORY_ID:
                if(TextUtils.isEmpty(selection))
                selection=ContractClass.Category.columnCategoryId + "=" + uri.getPathSegments().get(ContractClass.Category.CATEGORY_ID_PATH_POSITION);
                orderBy = ContractClass.Category.DEFAULT_SORT_ORDER;
                db = dbHelper.getReadableDatabase();
                cursor = db.query(ContractClass.Category.tableCategory , projection, selection, selectionArgs, null, null, orderBy);

                break;
            case CHANNEL:
                orderBy = ContractClass.Channel.DEFAULT_SORT_ORDER;
                db = dbHelper.getReadableDatabase();
                cursor = db.query(ContractClass.Channel.tableChannel , projection, selection, selectionArgs, null, null, orderBy);

                break;
            case CHANNEL_ID:
              //  qb.setTables(ContractClass.Channel.tableChannel);
              //  qb.setProjectionMap(sChannelProjectionMap);
                if(TextUtils.isEmpty(selection))
                selection=ContractClass.Channel.columnChannelId + "=" + uri.getPathSegments();
                orderBy = ContractClass.Channel.DEFAULT_SORT_ORDER;
                db = dbHelper.getReadableDatabase();
                cursor = db.query(ContractClass.Channel.tableChannel , projection, selection, selectionArgs, null, null, orderBy);

                break;
            case PROGRAM:
                 orderBy = ContractClass.Program.DEFAULT_SORT_ORDER;
                db = dbHelper.getReadableDatabase();
                cursor = db.query(ContractClass.Program.tableProgram , projection, selection, selectionArgs, null, null, orderBy);

                break;
            case PROGRAM_ID:
                 if(TextUtils.isEmpty(selection))
                    selection=ContractClass.Program.columnProgramId + "=" + uri.getPathSegments();
                orderBy = ContractClass.Program.DEFAULT_SORT_ORDER;
                db = dbHelper.getReadableDatabase();
                cursor = db.query(ContractClass.Program.tableProgram , projection, selection, selectionArgs, null, null, orderBy);

                break;
            case FAVORITE:
                orderBy = ContractClass.Favorite.DEFAULT_SORT_ORDER;
               db = dbHelper.getReadableDatabase();
                cursor = db.query(ContractClass.Favorite.tableFavorite , projection, selection, selectionArgs, null, null, orderBy);

                break;
            case FAVORITE_ID:
                 if(TextUtils.isEmpty(selection))
                    selection=(ContractClass.Favorite.columnFavoriteID + "=" + uri.getPathSegments());
                orderBy = ContractClass.Favorite.DEFAULT_SORT_ORDER;

                db = dbHelper.getReadableDatabase();
                cursor = db.query(ContractClass.Favorite.tableFavorite, projection, selection, selectionArgs, null, null, orderBy);

                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
         cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String where, String[] whereArgs) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int count;
        String finalWhere;
        String id;
        switch (sUriMatcher.match(uri)) {
            case CATEGORY:
                count = db.update(ContractClass.Category.tableCategory, values, where, whereArgs);
                break;
            case CATEGORY_ID:
                id = uri.getPathSegments().get(ContractClass.Category.CATEGORY_ID_PATH_POSITION);
                finalWhere = ContractClass.Category._ID + " = " + id;
                if (where !=null) {
                    finalWhere = finalWhere + " AND " + where;
                }
                count = db.update(ContractClass.Category.tableCategory, values, finalWhere, whereArgs);
                break;
            case CHANNEL:
                count = db.update(ContractClass.Channel.tableChannel, values, where, whereArgs);
                break;
            case CHANNEL_ID:
                id = uri.getPathSegments().get(ContractClass.Channel.CHANNEL_ID_PATH_POSITION);
                finalWhere = ContractClass.Channel._ID + " = " + id;
                if (where !=null) {
                    finalWhere = finalWhere + " AND " + where;
                }
                count = db.update(ContractClass.Channel.tableChannel, values, finalWhere, whereArgs);
                break;

            case PROGRAM:
                count = db.update(ContractClass.Program.tableProgram, values, where, whereArgs);
                break;
            case PROGRAM_ID:
                id = uri.getPathSegments().get(ContractClass.Program.PROGRAM_ID_PATH_POSITION);
                finalWhere = ContractClass.Program._ID + " = " + id;
                if (where !=null) {
                    finalWhere = finalWhere + " AND " + where;
                }
                count = db.update(ContractClass.Program.tableProgram, values, finalWhere, whereArgs);
                break;
            case FAVORITE:
                count = db.update(ContractClass.Favorite.tableFavorite, values, where, whereArgs);
                break;
            case FAVORITE_ID:
                id = uri.getPathSegments().get(ContractClass.Favorite.FAVORITE_ID_PATH_POSITION);
                finalWhere = ContractClass.Favorite._ID + " = " + id;
                if (where !=null) {
                    finalWhere = finalWhere + " AND " + where;
                }
                count = db.update(ContractClass.Favorite.tableFavorite, values, finalWhere, whereArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }



}
