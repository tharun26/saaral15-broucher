package com.inspiron.tharun26.saaral15;

/**
 * Created by tharun26 on 1/2/15.
 */
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by tharun26 on 18/1/15.
 */
public class EventListAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<EventItem> eventItems;

    public EventListAdapter(Context context, ArrayList<EventItem> eventItems) {
        this.context = context;
        this.eventItems = eventItems;
    }

    @Override
    public int getCount() {
        return eventItems.size();
    }

    @Override
    public Object getItem(int position) {

        return eventItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater)
                    context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.event_list_item, null);
        }
        ImageView img = (ImageView) convertView.findViewById(R.id.event_image);
        TextView txt = (TextView) convertView.findViewById(R.id.event_name);

        img.setImageResource(eventItems.get(position).getIcon());
        txt.setText(eventItems.get(position).getEvent_title());

/*
        if(position%2==0)
        {
            Animation animationX = new TranslateAnimation(parent.getWidth() / 4, 0, 0, 0);

            animationX.setDuration(500);
            convertView.startAnimation(animationX);
            animationX = null;
        }

        else {
            Animation animationX = new TranslateAnimation(-parent.getHeight() / 4, 0, 0, 0);

            animationX.setDuration(500);
            convertView.startAnimation(animationX);
            animationX = null;

        }
    */

        return convertView;
    }
}
