package com.sk.tvschedule.adapter;

import android.content.Context;
import android.database.Cursor;
import android.os.SystemClock;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.sk.tvschedule.fragment.ChannelFragment;
import com.sk.tvschedule.provider.ContractClass;

/**
 * Created by Сергій on 01.02.2017.
 */

public class TabPagerFragmentAdaptor extends FragmentPagerAdapter {

        protected Cursor mCursor;
        private Context context;

    public void setContext(Context context) {
        this.context = context;
    }

        public TabPagerFragmentAdaptor(final FragmentManager fragmentManager, final Cursor cursor)
        {
            super(fragmentManager);
            this.mCursor = cursor;
        }

        @Override
        public Fragment getItem(final int position)
        {
            Log.i("channelFragment pos ",String.valueOf(position));


            if (this.mCursor == null)
            {
                return null;
            }
            this.mCursor.moveToPosition(position);
         //   Log.i("getitem",mCursor.getString(mCursor.getColumnIndex(ContractClass.Channel.columnChannelName)));

            return this.getItem(this.mCursor);
        }





    @Override
    public CharSequence getPageTitle(int position) {
        this.mCursor.moveToPosition(position);
       Log.i("tab adaptor name",mCursor.getString(mCursor.getColumnIndex(ContractClass.Channel.columnChannelName)));
     //  return mCursor.getString(mCursor.getColumnIndex(ContractClass.Channel.columnChannelName));

       return "";

    }


        public  Fragment getItem(final Cursor cursor){

            ChannelFragment channelFragment=new ChannelFragment(cursor.getInt(cursor.getColumnIndex(ContractClass.Channel.columnChannelId)));
             channelFragment.setContext(context);
            Log.i("channelFragment id", String.valueOf(cursor.getInt(cursor.getColumnIndex(ContractClass.Channel.columnChannelId))));
            return channelFragment;
        }
                                        ////////////






        @Override
        public int getCount()
        {
            if (this.mCursor == null)
            {
                return 0;
            }
            return this.mCursor.getCount();
        }

        public void swapCursor(final Cursor cursor)
        {
              if (this.mCursor == cursor)
            {
                return;
            }

            this.mCursor = cursor;
            Log.i("change cursor in adaptor",String.valueOf(cursor.getCount())+" time "+String.valueOf(SystemClock.currentThreadTimeMillis()));

            this.notifyDataSetChanged();
        }

        public Cursor getCursor()
        {
            return this.mCursor;
        }

    }
