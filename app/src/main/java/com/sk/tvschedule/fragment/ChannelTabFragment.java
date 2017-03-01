package com.sk.tvschedule.fragment;

import android.database.Cursor;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sk.tvschedule.R;
import com.sk.tvschedule.adapter.TabPagerFragmentAdaptor;
import com.sk.tvschedule.provider.ContractClass;


/**
 * Created by Сергій on 02.02.2017.
 */

public class ChannelTabFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>{
    private static final int Layout= R.layout.channel_tab_layout;
    Cursor cursor;

    public TabPagerFragmentAdaptor getChannelFragmentAdaptor() {
        return channelFragmentAdaptor;
    }

    TabPagerFragmentAdaptor    channelFragmentAdaptor;

    public void setContext(Context context) {
        this.context = context;

    }

    private Context context;



    private View relativeLayout;


    public ChannelTabFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        relativeLayout=inflater.inflate(Layout,null);



        channelFragmentAdaptor=new TabPagerFragmentAdaptor(getFragmentManager(), cursor);
        channelFragmentAdaptor.setContext(context);
        ViewPager viewPager=(ViewPager)relativeLayout.findViewById(R.id.viewPager);
        TabLayout   tabLayout = (TabLayout) relativeLayout.findViewById(R.id.tabLayoutChannel);
        viewPager.setAdapter(channelFragmentAdaptor);
        tabLayout.setupWithViewPager(viewPager);
        return relativeLayout;
    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {

        String idChannel=null;
         return new CursorLoader(

                context,
                ContractClass.Channel.CONTENT_URI,
                ContractClass.Channel.DEFAULT_PROJECTION,
                idChannel,
                null,
                null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
       // data.moveToFirst();
        Log.i("time finish",String.valueOf(SystemClock.currentThreadTimeMillis()));

      channelFragmentAdaptor.swapCursor(data);
      }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        channelFragmentAdaptor.swapCursor(null);
    }


}
