package com.sk.tvschedule.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sk.tvschedule.R;
import com.sk.tvschedule.data.Data;
import com.sk.tvschedule.model.Channel;
import com.sk.tvschedule.model.Programs;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Сергій on 27.01.2017.
 */

public class ChannelAdapter extends ArrayAdapter<Channel> {
    List<Channel> channelList;
    Context context;
    private LayoutInflater layoutInflater;

    public ChannelAdapter(Context context, List<Channel> channelList) {
        super(context,0,channelList);
        this.channelList = channelList;
        this.layoutInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public Channel getItem(int position) {
        return channelList.get(position);
    }


    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView==null){
            View view=layoutInflater.inflate(R.layout.channel_item,parent,false);
            viewHolder= ViewHolder.create((RelativeLayout)view);
            view.setTag(viewHolder);
        }else
            viewHolder=(ViewHolder)convertView.getTag();

        Data data=Data.getInstance();
        Channel item=getItem(position);
        viewHolder.textViewChannelName.setText(item.getName());
        viewHolder.textViewChannelURL.setText(item.getUrl());
        viewHolder.textViewChannelCategory.setText(item.getCategoryId().toString());
        Picasso.with(context).load(item.getPicture()).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(viewHolder.imageViewChannel);
        viewHolder.listOfProgram.setAdapter(new ProgramsAdapter(context,getprogram(data.getProgramList(),item.getId())));
        return viewHolder.relativeLayoutChannel;


    }

    private List<Programs> getprogram(List<Programs> allPrograms,int idChanmel){
        List<Programs> programs=new ArrayList<>();
        for (int i=0;i<allPrograms.size();i++)
            if (allPrograms.get(i).getChannelId()==idChanmel) programs.add(allPrograms.get(i));

        return programs;


    }

    private static class ViewHolder {
        public final RelativeLayout relativeLayoutChannel;
        public final ImageView imageViewChannel;
        public final TextView textViewChannelName;
        public final TextView textViewChannelURL;
        public final TextView textViewChannelCategory;
        public final ListView listOfProgram;

        public ViewHolder(RelativeLayout relativeLayout, ImageView imageView, TextView textViewChannelName,
                          TextView textViewChannelURL, TextView textViewChannelCategory,ListView listOfProgram) {
            this.relativeLayoutChannel = relativeLayout;
            this.imageViewChannel = imageView;
            this.textViewChannelURL = textViewChannelURL;
            this.textViewChannelName = textViewChannelName;
            this.textViewChannelCategory = textViewChannelCategory;
            this.listOfProgram=listOfProgram;
        }

        public static ChannelAdapter.ViewHolder create(RelativeLayout relativeLayout) {
            ImageView imageViewChannel = (ImageView) relativeLayout.findViewById(R.id.imageViewChannel);
            TextView textViewChannelName = (TextView) relativeLayout.findViewById(R.id.textViewChannelName);
            TextView textViewChannelURL = (TextView) relativeLayout.findViewById(R.id.textViewChannelURL);
            TextView textViewChannelCategory = (TextView) relativeLayout.findViewById(R.id.textViewChannelCategory);
            ListView listOfProgram = (ListView)relativeLayout.findViewById(R.id.listOfProgram);

            return new ChannelAdapter.ViewHolder(relativeLayout,imageViewChannel,textViewChannelName,
                                                textViewChannelURL,textViewChannelCategory,listOfProgram);
        }


    }
}
