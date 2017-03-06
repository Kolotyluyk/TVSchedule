package com.sk.tvschedule.fragment;


import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.sk.tvschedule.R;
import com.sk.tvschedule.adapter.CategoryAdapter;
import com.sk.tvschedule.event.BusStationChangeCursor;
import com.sk.tvschedule.event.BusStationMain;
import com.sk.tvschedule.event.EventChangeChannelCursor;
import com.sk.tvschedule.event.EventShowChannels;
import com.sk.tvschedule.provider.ContractClass;

/**
 * Created by Сергій on 02.02.2017.
 */

public class ListFragmentCategory extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {


    private static final int Layout= R.layout.category_list_layout;
    private View relativeLayout;
    CategoryAdapter categoryAdapter;
    Context context;




    public ListFragmentCategory(Context context) {
        this.context = context;
        categoryAdapter= new CategoryAdapter(context,null,0);

    }






    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
     relativeLayout=inflater.inflate(Layout,null);

          final ListView listOfCategory = (ListView)relativeLayout.findViewById(R.id.listViewCategory);

listOfCategory.setAdapter(categoryAdapter);



    listOfCategory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
         @Override
         public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
             if(listOfCategory.getAdapter().equals(categoryAdapter)){
                 BusStationChangeCursor.getBus().post(new EventChangeChannelCursor(position));
                 BusStationMain.getBus().post(new EventShowChannels());

             }
         }
     });

              return relativeLayout;



    }



    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(
                context,
                ContractClass.Category.CONTENT_URI,
                ContractClass.Category.DEFAULT_PROJECTION,
                null,
                null,
                null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        categoryAdapter.swapCursor(data);
    }





    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        categoryAdapter.swapCursor(null);
    }

}

