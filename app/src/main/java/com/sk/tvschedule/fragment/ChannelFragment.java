package com.sk.tvschedule.fragment;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.sk.tvschedule.R;
import com.sk.tvschedule.adapter.ProgramsAdapter;
import com.sk.tvschedule.provider.ContractClass;
import com.squareup.picasso.Picasso;

/**
 * Created by Сергій on 02.02.2017.
 */

public class ChannelFragment extends Fragment {


    private static final int Layout= R.layout.channel_item;
    private View         relativeLayout;
    Context context;
    int id;

    public void setCursor(final Cursor  cursor) {
        this.cursor = cursor;
    }

    Cursor cursor;

    public void setContext(Context context) {
        this.context = context;
    }



    public ChannelFragment(int id) {
    this.id=id;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public ChannelFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        relativeLayout=inflater.inflate(Layout,container,false);

        ImageView imageViewChannel = (ImageView) relativeLayout.findViewById(R.id.imageViewChannel);
        TextView textViewChannelName = (TextView) relativeLayout.findViewById(R.id.textViewChannelName);
        TextView textViewChannelURL = (TextView) relativeLayout.findViewById(R.id.textViewChannelURL);
        ListView listOfProgram = (ListView)relativeLayout.findViewById(R.id.listViewProgram);

Log.i("channel id",String.valueOf(id));
      cursor = context.getContentResolver().query(
                ContractClass.Channel.CONTENT_URI,
                ContractClass.Channel.DEFAULT_PROJECTION,
                ContractClass.Channel.columnChannelId+"="+id,
               null,
                null);

        cursor.moveToFirst();


         this.id=cursor.getInt(cursor.getColumnIndex(ContractClass.Channel.columnChannelId));
        long idCategoru=cursor.getLong(cursor.getColumnIndex(ContractClass.Channel.columnChannelCategorId));
        String name=cursor.getString(cursor.getColumnIndex(ContractClass.Channel.columnChannelName));
        String picture=cursor.getString(cursor.getColumnIndex(ContractClass.Channel.columnChannelPicture));
        String url=cursor.getString(cursor.getColumnIndex(ContractClass.Channel.columnChannelURL));




        Log.i("id of channel",String.valueOf(id));



        Cursor cursorProgram = context.getContentResolver().query(
                ContractClass.Program.CONTENT_URI,
                ContractClass.Program.DEFAULT_PROJECTION,
                ContractClass.Program.columnProgramChannelId+"="+id,
                null,
                null);


        textViewChannelName.setText(name);
        textViewChannelURL.setText(url);
    //    textViewChannelCategory.setText(item.getCategoryId().toString());
        Picasso.with(getContext()).load(picture).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(imageViewChannel);
        ProgramsAdapter programsAdapter=new ProgramsAdapter(context,cursorProgram,0);

        listOfProgram.setAdapter(programsAdapter);
        return relativeLayout;



    }


}

