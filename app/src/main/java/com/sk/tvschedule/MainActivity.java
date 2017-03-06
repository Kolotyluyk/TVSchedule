package com.sk.tvschedule;


import android.support.v4.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.sk.tvschedule.Service.RestrofitService;
import com.sk.tvschedule.event.BusStationChangeCursor;
import com.sk.tvschedule.event.BusStationMain;
import com.sk.tvschedule.event.EvenrLoadedData;
import com.sk.tvschedule.event.EventChangeChannelCursor;
import com.sk.tvschedule.event.EventShowChannels;
import com.sk.tvschedule.fragment.ChannelTabFragment;
import com.sk.tvschedule.fragment.ListFragmentCategory;
import com.sk.tvschedule.fragment.ListFragmentProgram;
import com.squareup.otto.Subscribe;

import java.io.File;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    boolean flag=false;
    ListFragmentCategory listFragmentCategory;
    ListFragmentProgram listFragmentProgram;
    ChannelTabFragment channelTabFragment;

    ProgressDialog pd;
    Handler h;
    Fragment previewFragment;
    FragmentTransaction fTrans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BusStationMain.getBus().register(this);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);




         File dbtest =new File("/data/data/com.sk.tvschedule/databases/TVSchedule");
        if(!dbtest.exists()) {
           startService(new Intent(this, RestrofitService.class));
            Log.i("load information","Start");
        }
         else{
            Log.i("show  information","Start");
            BusStationMain.getBus().post(new EvenrLoadedData());
    }
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 //    loadInformation();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


   @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
       int id = item.getItemId();
       if (id == R.id.nav_channel) BusStationChangeCursor.getBus().post(new EventChangeChannelCursor(0));
           return changeMenu(id);
   }


    public boolean changeMenu(int id){
        if (id == R.id.nav_cameram) {
            if (!previewFragment.equals(listFragmentProgram))
            {
                fTrans = getSupportFragmentManager().beginTransaction();
                fTrans.remove(previewFragment);
                fTrans.add(R.id.frameFragment,listFragmentProgram);
                previewFragment= listFragmentProgram;
                fTrans.commit();
            }
     } else if (id == R.id.nav_category) {
            fTrans = getSupportFragmentManager().beginTransaction();
            fTrans.remove(previewFragment);
            fTrans.add(R.id.frameFragment, listFragmentCategory);
            previewFragment= listFragmentCategory;
            fTrans.commit();

        } else if (id == R.id.nav_channel) {
            if (previewFragment!=channelTabFragment) {
                fTrans = getSupportFragmentManager().beginTransaction();
                fTrans.remove(previewFragment);
                fTrans.add(R.id.frameFragment, channelTabFragment);
                previewFragment = channelTabFragment;
                fTrans.commit();
            }
        } else if (id == R.id.nav_manage) {


        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Subscribe
    public void showData(EventShowChannels event) {

        changeMenu(R.id.nav_channel);

    }





    @Subscribe
    public void showData(EvenrLoadedData evenrLoadedData){

        listFragmentCategory = new ListFragmentCategory(this);
      //  listFragmentCategory.setContext(this);
        listFragmentProgram=ListFragmentProgram.getInstance();
        listFragmentProgram.setContext(this);


        channelTabFragment=new ChannelTabFragment(this);
      //  channelTabFragment.setContext(this);


    //    ChannelFragment channelFragment=new ChannelFragment(1);
   //     channelFragment.setContext(this);

        BusStationChangeCursor.getBus().register(channelTabFragment);

        getSupportLoaderManager().initLoader(1, null, listFragmentCategory);
        getSupportLoaderManager().initLoader(2, null,listFragmentProgram);
        getSupportLoaderManager().initLoader(3, null,channelTabFragment);


        fTrans = getSupportFragmentManager().beginTransaction();

        fTrans.add(R.id.frameFragment,channelTabFragment);
        previewFragment= channelTabFragment;
        fTrans.commit();


    }
}
