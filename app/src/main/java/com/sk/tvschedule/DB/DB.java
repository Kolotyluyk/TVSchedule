package com.sk.tvschedule.DB;

import android.content.ContentValues;
import android.content.Context;
import android.widget.Toast;

import com.sk.tvschedule.model.Category;
import com.sk.tvschedule.model.Channel;
import com.sk.tvschedule.model.Programs;
import com.sk.tvschedule.provider.ContractClass;

import java.util.ArrayList;

/**
 * Created by Сергій on 30.01.2017.
 */

public class DB {




    Context context;



    public void saveProgram(ArrayList<Programs> programList) {
       int i=0,c=0;
            for (i = 0; i < programList.size(); i++) {
                ContentValues cv = new ContentValues();
                //cv.put(columnProgramId,i);
                cv.put(ContractClass.Program.columnProgramChannelId, programList.get(i).getChannelId());
                cv.put(ContractClass.Program.columnProgramTitle, programList.get(i).getTitle());
                cv.put(ContractClass.Program.columnProgramDate, programList.get(i).getDate());
                cv.put(ContractClass.Program.columnProgramTime, programList.get(i).getTime());
                cv.put(ContractClass.Program.columnProgramDescription, programList.get(i).getDescription());
                context.getContentResolver().insert(ContractClass.Program.CONTENT_URI, cv);
            }
        }


    public void saveCategory(ArrayList<Category> categoryList) {
        if (categoryList.size()!=0){
               for (int i=0;i<categoryList.size();i++) {
                ContentValues cv = new ContentValues();
                cv.put(ContractClass.Category.columnCategoryId, categoryList.get(i).getId());
                cv.put(ContractClass.Category.columnCategoryTitle, categoryList.get(i).getTitle());
                cv.put(ContractClass.Category.columnCategoryPicture, categoryList.get(i).getPicture());
                context.getContentResolver().insert(ContractClass.Category.CONTENT_URI, cv);
            }
        }
    }


    public void saveChannel(ArrayList<Channel> channelList) {
        if (channelList.size()!=0){
             for (int i=0;i<channelList.size();i++){
                ContentValues cv = new ContentValues();
                cv.put(ContractClass.Channel.columnChannelCategorId,channelList.get(i).getCategoryId());
                cv.put(ContractClass.Channel.columnChannelPicture,channelList.get(i).getPicture());
                cv.put(ContractClass.Channel.columnChannelName,channelList.get(i).getName());
                cv.put(ContractClass.Channel.columnChannelURL,channelList.get(i).getUrl());
                cv.put(ContractClass.Channel.columnChannelId,channelList.get(i).getId());
                 context.getContentResolver().insert(ContractClass.Channel.CONTENT_URI, cv);

            }
        }
    }

    public void saveFavorite(ArrayList<Integer> favoriteList) {
          for (int i = 0; i < favoriteList.size(); i++) {
            ContentValues cv = new ContentValues();
            cv.put(ContractClass.Favorite.columnFavoriteChannelID, favoriteList.get(i));
              context.getContentResolver().insert(ContractClass.Favorite.CONTENT_URI, cv);
        }
    }

    public DB(Context context) {

     //   dbHelper= new DBHelper(context);
        this.context=context;
    }


}

