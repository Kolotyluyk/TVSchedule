package com.sk.tvschedule.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.sk.tvschedule.R;
import com.sk.tvschedule.adapter.ProgramsAdapter;
import com.sk.tvschedule.data.Data;
import com.sk.tvschedule.model.Channel;
import com.sk.tvschedule.model.Programs;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Сергій on 02.02.2017.
 */

public class ChannelFragment extends Fragment {


    private static final int Layout= R.layout.channel_item;
    private View         relativeLayout;
    int id;



    public static ChannelFragment getInstance(int id) {
        Bundle args = new Bundle();
        ChannelFragment fragment = new ChannelFragment();
        args.putInt("ID",id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        id=getArguments().getInt("ID");

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        relativeLayout=inflater.inflate(Layout,container,false);

        ImageView imageViewChannel = (ImageView) relativeLayout.findViewById(R.id.imageViewChannel);
        TextView textViewChannelName = (TextView) relativeLayout.findViewById(R.id.textViewChannelName);
        TextView textViewChannelURL = (TextView) relativeLayout.findViewById(R.id.textViewChannelURL);
       // TextView textViewChannelCategory = (TextView) relativeLayout.findViewById(R.id.textViewChannelCategory);
        ListView listOfProgram = (ListView)relativeLayout.findViewById(R.id.listVIewProgramChannelItem);

        Data data= Data.getInstance();
        Channel item=new Channel();
        for (int i=0;i<data.getChannelList().size();i++)
            if (data.getChannelList().get(i).getId()==id)  {item=data.getChannelList().get(i); break;}


        textViewChannelName.setText(item.getName());
        textViewChannelURL.setText(item.getUrl());
    //    textViewChannelCategory.setText(item.getCategoryId().toString());
        Picasso.with(getContext()).load(item.getPicture()).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(imageViewChannel);
    //    listOfProgram.setAdapter(new ProgramsAdapter(getContext(),getprogram(data.getProgramList(),item.getId())));
        return relativeLayout;



    }

    private List<Programs> getprogram(List<Programs> allPrograms, int idChanmel){
        List<Programs> programs=new ArrayList<>();
        for (int i=0;i<allPrograms.size();i++)
            if (allPrograms.get(i).getChannelId()==idChanmel) programs.add(allPrograms.get(i));

        return programs;


    }
}

