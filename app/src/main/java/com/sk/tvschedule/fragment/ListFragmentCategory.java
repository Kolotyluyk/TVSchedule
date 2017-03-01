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
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.sk.tvschedule.R;
import com.sk.tvschedule.adapter.CategoryAdapter;
import com.sk.tvschedule.model.Category;
import com.sk.tvschedule.provider.ContractClass;

/**
 * Created by Сергій on 02.02.2017.
 */

public class ListFragmentCategory extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {


    private static final int Layout= R.layout.category_list_layout;
    private View relativeLayout;
    CategoryAdapter categoryAdapter;

    public void setContext(Context context) {
        this.context = context;
        categoryAdapter= new CategoryAdapter(context,null,0);

    }

    Context context;


    public static ListFragmentCategory getInstance() {
        Bundle args = new Bundle();
        ListFragmentCategory fragment = new ListFragmentCategory();
         fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         CategoryAdapter categoryAdapter;

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
     relativeLayout=inflater.inflate(Layout,null);

          final ListView listOfCategory = (ListView)relativeLayout.findViewById(R.id.listViewCategory);

listOfCategory.setAdapter(categoryAdapter);



  /*   listOfProgram.setOnItemClickListener(new AdapterView.OnItemClickListener() {
         @Override
         public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
             if(listOfProgram.getAdapter().equals(categoryAdapter)){
                 int categoryId=categoryAdapter.getItem(position).getId();

                 ViewPager viewPager=(ViewPager)vifindViewById(R.id.viewPager);
                 TabPagerFragmentAdaptor channelFragmentAdaptor=new TabPagerFragmentAdaptor(getApplicationContext(),getSupportFragmentManager(),c);
                 channelFragmentAdaptor.setAdaptorNumb(0);
                 viewPager.setAdapter(channelFragmentAdaptor);

                 TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
                 tabLayout.setupWithViewPager(viewPager);   channeladapter=new ChannelAdapter(getApplicationContext(),sortCategory(categoryId,  data.getChannelList()));
                 listView.setAdapter(channeladapter);
                 flag=true;
             }
         }
     });
*/
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
/*

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        final ArrayAdapter<Category> arrayAdapter=new ArrayAdapter<Category>(
                context,R.layout.category_item);

        CategoryAdapter.ViewHolder holder = (CategoryAdapter.ViewHolder) view.getTag();
        if(holder != null) {
            Category[] category = getCategory(holder.id);
            if(category != null && category.length > 0) {
                arrayAdapter.addAll(category);
            }
        } else {
            return;
        }

    }

    private Category[] getCategory(long categoryID){
        Category[] categories=null;
        Cursor c = context.getContentResolver().query(
                ContractClass.Category.CONTENT_URI,
                ContractClass.Category.DEFAULT_PROJECTION,
                                                    null,
                new String[] {""+categoryID},
                null);
        if(c != null) {
            if(c.moveToFirst()) {
                categories = new Category[c.getCount()];
                int i=0;
                do {
                    String title = c.getString(c.getColumnIndex(ContractClass.Category.columnCategoryTitle));
                    String picture = c.getString(c.getColumnIndex(ContractClass.Category.columnCategoryPicture));
                    int score = c.getInt(c.getColumnIndex(ContractClass.Category.columnCategoryId));
                    Category cc=new Category();
                    cc.setTitle(title);
                    cc.setPicture(picture);
                    cc.setId(score);
                    categories[i] =cc;
                    i++;
                } while (c.moveToNext());
            }
            c.close();
        }
        return categories;
    }
*/
}

