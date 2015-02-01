package com.inspiron.tharun26.saaral15;

import android.app.Activity;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;


public class Event extends Activity {


    private ListView event_list;
    private String[] event_titles;
    private TypedArray event_icons;
    private ArrayList<EventItem> event_Items;
    private  EventListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        event_titles = getResources().getStringArray(R.array.events);

        // nav drawer icons from resources
        event_icons = getResources()
                .obtainTypedArray(R.array.events_images);


        // mDrawerList = (ListView) findViewById(R.id.list_slidermenu);

        event_Items = new ArrayList<EventItem>();

        // adding nav drawer items to array
        // Home
        event_Items.add(new EventItem(event_titles[0], event_icons.getResourceId(0, -1)));
        // Find People
        event_Items.add(new EventItem(event_titles[1], event_icons.getResourceId(1, -1)));
        // Photos
        event_Items.add(new EventItem(event_titles[2],  event_icons.getResourceId(2, -1)));
        // Communities, Will add a counter here
        event_Items.add(new EventItem(event_titles[3],  event_icons.getResourceId(3, -1)));
        // Pages
      //  event_Items.add(new EventItem(event_titles[4],  event_icons.getResourceId(4, -1)));
        // What's hot, We  will add a counter here
      /*
        event_Items.add(new EventItem(event_titles[5],  event_icons.getResourceId(5, -1)));

        event_Items.add(new  EventItem(event_titles[6],  event_icons.getResourceId(6, -1)));

        event_Items.add(new EventItem(event_titles[7],  event_icons.getResourceId(7, -1)));
    */


        adapter = new EventListAdapter(this,event_Items);
        event_list= (ListView)findViewById(R.id.list123);
        event_list.setAdapter(adapter);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.event, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
