package com.inspiron.tharun26.saaral15;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class contacts extends Activity {



    private class MyAdapter extends BaseAdapter
    {
        private List<Item> items = new ArrayList<Item>();
        private LayoutInflater inflater;

        public MyAdapter(Context context)
        {
            inflater = LayoutInflater.from(context);
/*
            items.add(new Item("Image 1", R.drawable.default_male));
            items.add(new Item("Image 2", R.drawable.defaultfemale));
            items.add(new Item("Image 3", R.drawable.default_male));
            items.add(new Item("Image 4", R.drawable.default_male));
            items.add(new Item("Image 5", R.drawable.default_male));
            items.add(new Item("Image 6", R.drawable.default_male));
            items.add(new Item("Image 7", R.drawable.defaultfemale));
            items.add(new Item("Image 8", R.drawable.default_male));
            items.add(new Item("Image 9", R.drawable.default_male));
            items.add(new Item("Image 10", R.drawable.default_male));
    */

            items.add(new Item("Image 1", R.drawable.default_male,"Director"));
            items.add(new Item("Image 2", R.drawable.defaultfemale,"President"));

            items.add(new Item("Image 1", R.drawable.default_male,"Director"));
            items.add(new Item("Image 2", R.drawable.defaultfemale,"President"));
        }

        @Override
        public int getCount() {
            return items.size();
        }

        @Override
        public Object getItem(int i)
        {
            return items.get(i);
        }

        @Override
        public long getItemId(int i)
        {
            return items.get(i).drawableId;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup)
        {
            View v = view;
            ImageView picture;
            TextView name;
            TextView title;

            if(v == null)
            {
                v = inflater.inflate(R.layout.gridview_item, viewGroup, false);
                v.setTag(R.id.picture, v.findViewById(R.id.picture));
                v.setTag(R.id.text, v.findViewById(R.id.text));
                v.setTag(R.id.text1,v.findViewById(R.id.text1));
            }

            picture = (ImageView)v.getTag(R.id.picture);
            name = (TextView)v.getTag(R.id.text);
           title=(TextView)v.getTag(R.id.text1);

            Item item = (Item)getItem(i);

            picture.setImageResource(item.drawableId);
            name.setText(item.name);
            title.setText(item.title);

            return v;
        }

        private class Item
        {
            final String name;
            final int drawableId;
            final String title;

            Item(String name, int drawableId,String title)
            {
                this.name = name;
                this.drawableId = drawableId;
                this.title=title;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        GridView gridView = (GridView)findViewById(R.id.gridview);
        gridView.setAdapter(new MyAdapter(this));

        gridView.setOnItemClickListener(new GridView.OnItemClickListener()
        {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id)
            {
                // this 'mActivity' parameter is Activity object, you can send the current activity.
                if(position==0) {
                    Intent callIntent = new Intent(Intent.ACTION_DIAL);
                    callIntent.setData(Uri.parse("tel:8056097997"));
                    Context context = getApplicationContext();

                    startActivity(callIntent);
                }
                else if(position==1)
                {
                    Intent callIntent = new Intent(Intent.ACTION_DIAL);
                    callIntent.setData(Uri.parse("tel:9566211447"));
                    Context context = getApplicationContext();

                    startActivity(callIntent);

                }
                else if(position==2)
                {
                    Intent callIntent = new Intent(Intent.ACTION_DIAL);
                    callIntent.setData(Uri.parse("tel:9566211447"));
                    Context context = getApplicationContext();

                    startActivity(callIntent);

                }
                else if(position==3)
                {
                    Intent callIntent = new Intent(Intent.ACTION_DIAL);
                    callIntent.setData(Uri.parse("tel:9566211447"));
                    Context context = getApplicationContext();

                    startActivity(callIntent);

                }
                else if(position==4)
                {
                    Intent callIntent = new Intent(Intent.ACTION_DIAL);
                    callIntent.setData(Uri.parse("tel:9566211447"));
                    Context context = getApplicationContext();

                    startActivity(callIntent);

                }
                else if(position==5)
                {
                    Intent callIntent = new Intent(Intent.ACTION_DIAL);
                    callIntent.setData(Uri.parse("tel:9566211447"));
                    Context context = getApplicationContext();

                    startActivity(callIntent);

                }


               // Toast.makeText(context, "Image1 from row " + position + " was pressed", Toast.LENGTH_LONG).show();
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.contacts, menu);
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
