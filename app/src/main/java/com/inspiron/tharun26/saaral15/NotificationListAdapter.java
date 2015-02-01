package com.inspiron.tharun26.saaral15;

/**
 * Created by tharun26 on 1/2/15.
 */


        import android.content.Context;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.BaseAdapter;

        import java.util.ArrayList;

        import android.app.Activity;
        import android.content.Context;
        import android.graphics.Color;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.view.animation.Animation;
        import android.view.animation.TranslateAnimation;
        import android.widget.BaseAdapter;
        import android.widget.ImageView;
        import android.widget.TextView;

        import com.inspiron.tharun26.saaral15.R;
        import com.inspiron.tharun26.saaral15.NotificationItems;
        import com.inspiron.tharun26.saaral15.NotificationDb;

/**
 * Created by tharun26 on 26/1/15.
 */
public class NotificationListAdapter extends BaseAdapter{

    private Context context;
    private ArrayList<NotificationItems> notificationItems;



    public NotificationListAdapter(Context context, ArrayList<NotificationItems> notificationItems) {
        this.context = context;
        this.notificationItems = notificationItems;
    }


    @Override
    public int getCount() {
        return notificationItems.size();
    }

    @Override
    public Object getItem(int position) {
        return notificationItems.get(position);
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
            convertView = mInflater.inflate(R.layout.notification_item, null);
        }
        TextView title = (TextView) convertView.findViewById(R.id.textView);
        title.setText(notificationItems.get(position).getMessage());

        return convertView;
    }
}