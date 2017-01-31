package com.sk.tvschedule;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import com.sk.tvschedule.DB.GetData;
import com.sk.tvschedule.DB.SetData;
import com.sk.tvschedule.adapter.CategoryAdapter;
import com.sk.tvschedule.adapter.ChannelAdapter;
import com.sk.tvschedule.adapter.ProgramsAdapter;
import com.sk.tvschedule.api.ApiService;
import com.sk.tvschedule.api.RetroClient;
import com.sk.tvschedule.data.Data;
import com.sk.tvschedule.model.Category;
import com.sk.tvschedule.model.Channel;
import com.sk.tvschedule.model.Programs;

import java.io.File;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.sk.tvschedule.DB.DBHelper.dBName;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private ListView listView;
    private View     parentView;
    Data data;
    GetData getData;
    CategoryAdapter  categoryAdapter;
    ChannelAdapter channeladapter;
    ProgramsAdapter programadapter;

 //   private ArrayList<Category> categoryList;
  //  private CategoryAdapter channeladapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        parentView = findViewById(R.id.content_main);
        listView= (ListView)findViewById(R.id.listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //           Snackbar.make(parentView, categoryList.get(position).getTitle() , Snackbar.LENGTH_LONG).show();
            }
        });

       data=Data.getInstance();

        File dbtest =new File("/data/data/com.sk.tvschedule/databases/TVSchedule");
        if(!dbtest.exists()) {
            loadInformation();
        }
        else {

            getData=new GetData(MainActivity.this);

            data.setProgramList(getData.getProgram());
            data.setChannelList(getData.getChannel());
            data.setCategoryList(getData.getCategory());
            data.setFavoriteList(getData.getFavorite());

            categoryAdapter = new CategoryAdapter(getApplicationContext(), data.getCategoryList());
            channeladapter = new ChannelAdapter(getApplicationContext(), data.getChannelList());
            programadapter = new ProgramsAdapter(getApplicationContext(), data.getProgramList());

            listView.setAdapter(channeladapter);
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
              //          .setAction("Action", null).show();
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {

           listView.setAdapter(programadapter);
        } else if (id == R.id.nav_gallery) {
            listView.setAdapter(categoryAdapter);
        } else if (id == R.id.nav_slideshow) {
                 listView.setAdapter(channeladapter);
        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }





    public void loadInformation(){
        ApiService api = RetroClient.getService();
        Call<List<Channel>> callChannel= api.getJSONChannel();
        callChannel.enqueue(new Callback<List<Channel>>() {
            @Override
            public void onResponse(Call<List<Channel>> call, Response<List<Channel>> response) {
                if (response.isSuccessful()){
                    data.setChannelList(response.body());
                 //   channeladapter.addAll(data.getChannelList());
                    SetData setDate=new SetData(getApplicationContext(),1);
                    setDate.setChannelList(data.getChannelList());
                    setDate.run();
                    channeladapter = new ChannelAdapter(getApplicationContext(), data.getChannelList());
                    listView.setAdapter(channeladapter);
                }
            }
            @Override
            public void onFailure(Call<List<Channel>> call, Throwable t) {

            }
        });


        Call<List<Category>> callCategory= api.getJSONCategory();
        callCategory.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                if (response.isSuccessful()){
                    data.setCategoryList(response.body());
                    SetData setDate=new SetData(getApplicationContext(),0);
                    setDate.setCategoryList(data.getCategoryList());
                    setDate.run();
                   //  categoryAdapter.addAll(data.getCategoryList());
                    //     listView.setAdapter(channeladapter);
                    categoryAdapter = new CategoryAdapter(getApplicationContext(), data.getCategoryList());
                }
            }
            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {

            }
        });


        Call<List<Programs>> callProgram= api.getJSONPrograms((int) System.currentTimeMillis());
        callProgram.enqueue(new Callback<List<Programs>>() {
            @Override
            public void onResponse(Call<List<Programs>> call, Response<List<Programs>> response) {
                if (response.isSuccessful()){
                    List<Programs>  categoryList=  response.body();
                    data.setProgramList(response.body());
                    SetData setDate=new SetData(getApplicationContext(),2);
                    setDate.setProgramList(data.getProgramList());
                   // programadapter.addAll(data.getProgramList());
                    setDate.run();
                    programadapter = new ProgramsAdapter(getApplicationContext(), data.getProgramList());


                }
            }
            @Override
            public void onFailure(Call<List<Programs>> call, Throwable t) {

            }



        });

    }
}
