package com.sk.tvschedule.adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sk.tvschedule.R;
import com.sk.tvschedule.model.Programs;
import com.sk.tvschedule.provider.ContractClass;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Сергій on 27.01.2017.
 */

public class ProgramsAdapter extends CursorAdapter {

    private LayoutInflater layoutInflater;

    public ProgramsAdapter(Context context, Cursor c, int flags) {
        super(context, c,flags);

        this.layoutInflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }





    @Override
    public void bindView( View convertView, Context context,Cursor cursor) {
        long id=cursor.getLong(cursor.getColumnIndex(ContractClass.Program.columnProgramId));
        String programTitle=cursor.getString(cursor.getColumnIndex(ContractClass.Program.columnProgramTitle));
        String programTime=cursor.getString(cursor.getColumnIndex(ContractClass.Program.columnProgramTime));
        String programDate=cursor.getString(cursor.getColumnIndex(ContractClass.Program.columnProgramDate));
        String programDescription=cursor.getString(cursor.getColumnIndex(ContractClass.Program.columnProgramDescription));
   //     long channelId=cursor.getLong(cursor.getColumnIndex(ContractClass.Program.columnProgramChannelId));

        ProgramsAdapter.ViewHolder holder = (ProgramsAdapter.ViewHolder) convertView.getTag();
        if (holder!=null){

            holder.textViewProgramTitle.setText(programTitle);
            holder.textViewProgramTime.setText(programTime);
            holder.textViewProgramDate.setText(programDate);
            holder.textViewProgramDescription.setText(programDescription);
        }
    }


    @Override
    public View newView(Context ctx, Cursor cur, ViewGroup parent) {
        View root = layoutInflater.inflate(R.layout.program_item, parent, false);
        ProgramsAdapter.ViewHolder holder = new ProgramsAdapter.ViewHolder();


        TextView textViewProgramTitle = (TextView) root.findViewById(R.id.textViewProgramTitle);
        TextView textViewProgramDate = (TextView) root.findViewById(R.id.textViewdProgramDate);
        TextView textViewProgramTime = (TextView) root.findViewById(R.id.textViewProgramTime);
        TextView textViewProgramDescription = (TextView) root.findViewById(R.id.textViewProgramDescription);

        holder.textViewProgramTitle = textViewProgramTitle;
        holder.textViewProgramTime = textViewProgramTime;
        holder.textViewProgramDate = textViewProgramDate;
        holder.textViewProgramDescription= textViewProgramDescription;

        root.setTag(holder);
        return root;
    }


    public static class ViewHolder {
         //   public final ImageView image;
        //  public final TextView textViewProgramChannelId;
        public  TextView textViewProgramTitle;
        public  TextView textViewProgramTime;
        public  TextView textViewProgramDate;
        public  TextView textViewProgramDescription;

        public ViewHolder() {

        }

    }

}
