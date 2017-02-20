package com.sk.tvschedule.adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sk.tvschedule.R;
import com.sk.tvschedule.provider.ContractClass;
import com.squareup.picasso.Picasso;

/**
 * Created by Сергій on 27.01.2017.
 */

public class CategoryAdapter extends CursorAdapter {


    private LayoutInflater layoutInflater;

    public CategoryAdapter(Context context, Cursor c, int flags) {
        super(context, c,flags);

        this.layoutInflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public void bindView( View convertView, Context context,Cursor cursor) {
      long id=cursor.getLong(cursor.getColumnIndex(ContractClass.Category.columnCategoryId));
        String categoryTitle=cursor.getString(cursor.getColumnIndex(ContractClass.Category.columnCategoryTitle));
        String categoryPicture=cursor.getString(cursor.getColumnIndex(ContractClass.Category.columnCategoryPicture));
        ViewHolder holder = (ViewHolder) convertView.getTag();
        if (holder!=null){

            holder.textViewCategory.setText(categoryTitle);
            holder.id=id;
            Picasso.with(context).load(categoryPicture).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(holder.imageViewCategory);
        }
    }


    @Override
    public View newView(Context ctx, Cursor cur, ViewGroup parent) {
        View root = layoutInflater.inflate(R.layout.category_item, parent, false);
        ViewHolder holder = new ViewHolder();
        TextView title = (TextView)root.findViewById(R.id.textViewCategory);
        holder.textViewCategory =title;
        ImageView image = (ImageView) root.findViewById(R.id.imageViewCategory);
        holder.imageViewCategory=image;
        root.setTag(holder);
        return root;
    }



    public static class ViewHolder {
        public  long id;
        public  ImageView imageViewCategory;
        public  TextView textViewCategory;

        public ViewHolder() {

        }



    }
}
