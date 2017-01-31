package com.sk.tvschedule.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sk.tvschedule.R;
import com.sk.tvschedule.model.Programs;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Сергій on 27.01.2017.
 */

public class ProgramsAdapter extends ArrayAdapter<Programs> {
        List<Programs> programsList;
        Context context;
private LayoutInflater layoutInflater;

public ProgramsAdapter(Context context, List<Programs> programslList) {
        super(context,0,programslList);
        this.programsList = programslList;
        this.layoutInflater = LayoutInflater.from(context);
        this.context = context;
        }

@Override
public Programs getItem(int position) {
        return programsList.get(position);
        }


@NonNull
@Override
public View getView(int position, View convertView, ViewGroup parent) {
final ViewHolder viewHolder;
        if (convertView==null){
        View view=layoutInflater.inflate(R.layout.program_item,parent,false);
        viewHolder= ViewHolder.create((RelativeLayout)view);
        view.setTag(viewHolder);
        }else
        viewHolder=(ViewHolder)convertView.getTag();

        Programs item=getItem(position);
       viewHolder.textViewProgramChannelId.setText(item.getChannelId().toString());
         //Picasso.with(context).load(item.getPicture()).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(viewHolder.imageView);
   viewHolder.textViewProgramTitle.setText(item.getTitle());
    viewHolder.textViewProgramTime.setText(item.getTime());
    viewHolder.textViewProgramDate.setText(item.getDate());
   viewHolder.textViewProgramDescription.setText(item.getDescription());

        return viewHolder.relativeLayoutProgram;


        }


private static class ViewHolder {
    public final RelativeLayout relativeLayoutProgram;
 //   public final ImageView image;
    public final TextView textViewProgramChannelId;
    public final TextView textViewProgramTitle;
    public final TextView textViewProgramTime;
    public final TextView textViewProgramDate;
    public final TextView textViewProgramDescription;

    public ViewHolder(RelativeLayout relativeLayout /*ImageView imageView*/,TextView textViewProgramChannelId,
            TextView textViewProgramTitle, TextView textViewProgramTime,TextView textViewProgramDate,
             TextView textViewProgramDescription) {
        this.relativeLayoutProgram = relativeLayout;
        this.textViewProgramChannelId = textViewProgramChannelId;
        this.textViewProgramTitle = textViewProgramTitle;
        this.textViewProgramTime = textViewProgramTime;
        this.textViewProgramDate = textViewProgramDate;
        this.textViewProgramDescription= textViewProgramDescription;
    }

    public static ProgramsAdapter.ViewHolder create(RelativeLayout relativeLayout) {
      //  ImageView imageView = (ImageView) relativeLayout.findViewById(R.id.imageView);


        TextView textViewProgramChannelID = (TextView) relativeLayout.findViewById(R.id.textViewProgramChannelId);
        TextView textViewProgramTitle = (TextView) relativeLayout.findViewById(R.id.textViewProgramTitle);
        TextView textViewProgramDate = (TextView) relativeLayout.findViewById(R.id.textViewdProgramDate);
        TextView textViewProgramTime = (TextView) relativeLayout.findViewById(R.id.textViewProgramTime);
        TextView textViewProgramDescription = (TextView) relativeLayout.findViewById(R.id.textViewProgramDescription);

        return new ProgramsAdapter.ViewHolder(relativeLayout,textViewProgramChannelID,
                 textViewProgramTitle, textViewProgramTime, textViewProgramDate,
                  textViewProgramDescription);
    }


}
}
