package com.sk.tvschedule.data;

import com.sk.tvschedule.DB.DBHelper;
import com.sk.tvschedule.model.Category;
import com.sk.tvschedule.model.Channel;
import com.sk.tvschedule.model.Programs;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Сергій on 30.01.2017.
 */
public class Data {
    List<Category> categoryList;
    List<Programs> programList;
    List<Channel> channelList;
    List<Integer> favoriteList;

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    public List<Programs> getProgramList() {
        return programList;
    }

    public void setProgramList(List<Programs> programList) {
        this.programList = programList;
    }

    public List<Channel> getChannelList() {
        return channelList;
    }

    public void setChannelList(List<Channel> channelList) {
        this.channelList = channelList;
    }

    public List<Integer> getFavoriteList() {

        return favoriteList;
    }

    public void setFavoriteList(List<Integer> favoriteList) {
        this.favoriteList = favoriteList;
    }

    DBHelper dbHelper;


    private static Data ourInstance = new Data();

    public static Data getInstance() {
        return ourInstance;
    }

    private Data() {
        categoryList=new ArrayList<>();
        programList=new ArrayList<>();
        channelList=new ArrayList<>();
        favoriteList=new ArrayList<>();


    }
}
