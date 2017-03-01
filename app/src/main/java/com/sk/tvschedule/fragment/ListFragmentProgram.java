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
import android.widget.ListView;

import com.sk.tvschedule.R;
import com.sk.tvschedule.adapter.ProgramsAdapter;
import com.sk.tvschedule.provider.ContractClass;

/**
 * Created by Сергій on 02.02.2017.
 */

public class ListFragmentProgram extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>{


    private static final int Layout= R.layout.program_list_layout;
    private View relativeLayout;
    ProgramsAdapter programsAdapter;
    Context context;
    int id=0;


    public ListFragmentProgram(Context context, int id) {
        this.context = context;
        this.id = id;
    }






    public void setContext(Context context) {
        this.context = context;
        programsAdapter= new ProgramsAdapter(context,null,0);
    }


    public ListFragmentProgram() {
    }

    public static ListFragmentProgram getInstance() {
        Bundle args = new Bundle();
        ListFragmentProgram fragment = new ListFragmentProgram();
         fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
     relativeLayout=inflater.inflate(Layout,null);

          final ListView listOfProgram = (ListView)relativeLayout.findViewById(R.id.listViewProgram);
listOfProgram.setAdapter(programsAdapter);
              return relativeLayout;
    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {

        String idChannel=null;
        if (this.id!=0) idChannel= ContractClass.Program.columnProgramChannelId+"="+this.id;

        return new CursorLoader(

                context,
                ContractClass.Program.CONTENT_URI,
                ContractClass.Program.DEFAULT_PROJECTION,
                idChannel,
                null,
                null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        data.moveToFirst();
        programsAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        programsAdapter.swapCursor(null);
    }


}

