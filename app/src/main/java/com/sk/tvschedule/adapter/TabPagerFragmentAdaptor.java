package com.sk.tvschedule.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.sk.tvschedule.fragment.ChannelFragment;
import com.sk.tvschedule.model.Channel;

import java.util.List;

/**
 * Created by Сергій on 01.02.2017.
 */

public class TabPagerFragmentAdaptor extends FragmentPagerAdapter {
    List<Channel> channelList;

    public void setAdaptorNumb(int adaptorNumb) {
        this.adaptorNumb = adaptorNumb;
    }

    int adaptorNumb;
    Context context;

    public void setChannelList(List<Channel> channelList) {
        this.channelList = channelList;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return channelList.get(position).getName();
    }

    public TabPagerFragmentAdaptor(Context context, FragmentManager fm, List<Channel> channelList) {
        super(fm);
        this.channelList=channelList;
        this.context=context;


    }

    @Override
    public int getCount(){
        if (adaptorNumb==0)
         return channelList.size();
    return 1;
    }


    @Override
    public Fragment getItem(int position) {

            return ChannelFragment.getInstance(channelList.get(position).getId());


}
}
