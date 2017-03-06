package com.sk.tvschedule.fragment;

import android.database.Cursor;
import android.os.SystemClock;
import android.support.annotation.Nullable;
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
import com.sk.tvschedule.event.EventChangeChannelCursor;
import com.sk.tvschedule.provider.ContractClass;
import com.squareup.otto.Subscribe;


/**
 * Created by Сергій on 02.02.2017.
 */

public class ChannelTabFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>{
    private static final int Layout= R.layout.channel_tab_layout;
    Cursor cursor;
    int id;
    ViewPager viewPager;
    TabLayout   tabLayout;




    @Subscribe
    public void changeIdCategory(EventChangeChannelCursor event){
       this.id= event.getId();

        String idChannel=null;
        if (this.id!=0) idChannel= ContractClass.Channel.columnChannelCategorId+"="+this.id;

        Cursor cursorChannel = context.getContentResolver().query(
                ContractClass.Channel.CONTENT_URI,
                ContractClass.Channel.DEFAULT_PROJECTION,
                idChannel,
                null,
                null);

        channelFragmentAdaptor.swapCursor(cursorChannel);
        Log.i("changeCursor channelFragment count frag", String.valueOf(channelFragmentAdaptor.getCursor().getCount()));


    }

    public TabPagerFragmentAdaptor getChannelFragmentAdaptor() {
        return channelFragmentAdaptor;
    }

    TabPagerFragmentAdaptor    channelFragmentAdaptor;

    public void setContext(Context context) {
        this.context = context;

    }

    private Context context;



    private View relativeLayout;


    public ChannelTabFragment(Context context) {
        this.context = context;

    }

    @Override
    public void onDestroy() {
        Log.i("channelFragment","destroy");

        super.onDestroy();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i("cnannel tab"," createview");

        if (channelFragmentAdaptor==null){

            relativeLayout=inflater.inflate(Layout,null);
            channelFragmentAdaptor=new TabPagerFragmentAdaptor(getFragmentManager(), cursor);
            channelFragmentAdaptor.setContext(context);
            viewPager=(ViewPager)relativeLayout.findViewById(R.id.viewPager);
            tabLayout = (TabLayout) relativeLayout.findViewById(R.id.tabLayoutChannel);

            viewPager.setAdapter(channelFragmentAdaptor);
            tabLayout.setupWithViewPager(viewPager);
            Log.i("createView channelFragment","create");


        }else {
            Log.i("createView channelFragment", String.valueOf(channelFragmentAdaptor.getCursor().getCount()));

           }
        Log.i("change cursor createtime",
                " time "+String.valueOf(SystemClock.currentThreadTimeMillis()));

        return relativeLayout;

    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {

        String idChannel=null;
        if (this.id!=0) idChannel= ContractClass.Channel.columnChannelCategorId+"="+this.id;
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
        Log.i("cnannel tab"," looad data finish");
      channelFragmentAdaptor.swapCursor(data);
      }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        channelFragmentAdaptor.swapCursor(null);
    }


}
