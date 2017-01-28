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
import com.sk.tvschedule.model.Category;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Сергій on 27.01.2017.
 */

public class CategoryAdapter extends ArrayAdapter<Category> {

    List<Category> categoryList;
    Context context;
    private LayoutInflater layoutInflater;

    public CategoryAdapter(Context context, List<Category> categoryList) {
        super(context, 0, categoryList);
        this.categoryList = categoryList;
        this.layoutInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public Category getItem(int position) {
        return categoryList.get(position);
    }


    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView==null){
            View view=layoutInflater.inflate(R.layout.categoryitem,parent,false);
            viewHolder=ViewHolder.create((RelativeLayout)view);
            view.setTag(viewHolder);
        }else
            viewHolder=(ViewHolder)convertView.getTag();

        Category item=getItem(position);
        viewHolder.textViewCategory.setText(item.getTitle());
        Picasso.with(context).load(item.getPicture()).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(viewHolder.imageViewCategory);

        return viewHolder.relativeLayoutCategory;


    }


    private static class ViewHolder {
        public final RelativeLayout relativeLayoutCategory;
        public final ImageView imageViewCategory;
        public final TextView textViewCategory;

        public ViewHolder(RelativeLayout relativeLayout, ImageView imageView, TextView textView) {
            this.relativeLayoutCategory = relativeLayout;
            this.imageViewCategory = imageView;
            this.textViewCategory = textView;
        }

        public static ViewHolder create(RelativeLayout relativeLayout) {
            ImageView imageViewCategory = (ImageView) relativeLayout.findViewById(R.id.imageViewCategory);
            TextView textViewCategory = (TextView) relativeLayout.findViewById(R.id.textViewCategory);

            return new ViewHolder(relativeLayout,imageViewCategory,textViewCategory);
        }


    }
}
