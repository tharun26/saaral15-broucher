package com.inspiron.tharun26.saaral15;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.TypedArray;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gcm.GCMRegistrar;

import static com.inspiron.tharun26.saaral15.CommonUtilities.DISPLAY_MESSAGE_ACTION;
import static com.inspiron.tharun26.saaral15.CommonUtilities.SENDER_ID;
import static com.inspiron.tharun26.saaral15.CommonUtilities.EXTRA_MESSAGE;

import com.inspiron.tharun26.saaral15.NotificationItems;

import java.util.ArrayList;
import java.util.List;


public class MyActivity extends Activity   {
    /*
GCM
 */
    ConnectionDetector cd;
    public static String name;
    public static String email;
    AsyncTask<Void, Void, Void> mRegisterTask;

    ImageButton events;
    ImageButton contacts;
    ImageButton sponsors,dev,developer,sponsors1;
    ImageButton guest;


    private ListView notification_list;
    public static String[] notification_title=new String[50] ;
    private TypedArray notification_icons;
    private ArrayList<NotificationItems> notification_Items;
    private NotificationListAdapter notification_adapter;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        /*GCM*/
        cd = new ConnectionDetector(getApplicationContext());
        // Check if Internet present
        if (!cd.isConnectingToInternet()) {
            // Internet Connection is not present
            Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_LONG).show();
            // stop executing code by return
            //  return;
        }

        name = "ManusysID56";
        email = "ManusysEmail56";

        // Make sure the device has the proper dependencies.
        GCMRegistrar.checkDevice(this);

        // Make sure the manifest was properly set - comment out this line
        // while developing the app, then uncomment it when it's ready.
        GCMRegistrar.checkManifest(this);


        registerReceiver(mHandleMessageReceiver, new IntentFilter(DISPLAY_MESSAGE_ACTION));

        // Get GCM registration id
        final String regId = GCMRegistrar.getRegistrationId(this);

        // Check if regid already presents
        if (regId.equals("")) {
            // Registration is not present, register now with GCM
           GCMRegistrar.register(this, SENDER_ID);
          // GCMRegistrar.unregister(this);
           // Toast.makeText(getApplicationContext(), "Registered", Toast.LENGTH_LONG).show();
        } else {
            // Device is already registered on GCM
            // Device is already registered on GCM
            if (GCMRegistrar.isRegisteredOnServer(this)) {
                // Skips registration.
       //         Toast.makeText(getApplicationContext(), "Already registered with GCM", Toast.LENGTH_LONG).show();

            } else {
                // Device is already registered on GCM

                // Skips registration.
                //Toast.makeText(getApplicationContext(), "Already registered with GCM", Toast.LENGTH_LONG).show();

                final Context context = this;
                mRegisterTask = new AsyncTask<Void, Void, Void>() {

                    @Override
                    protected Void doInBackground(Void... params) {
                        // Register on our server
                        // On server creates a new user
                        ServerUtilities.register(context, name, email, regId);
                        return null;
                    }

                    @Override
                    protected void onPostExecute(Void result) {
                        mRegisterTask = null;
                    }

                };
                mRegisterTask.execute(null, null, null);
            }
        }

        /*mHandleMessageReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String newMessage = intent.getExtras().getString(EXTRA_MESSAGE);
                // Waking up mobile if it is sleeping
                WakeLocker.acquire(getApplicationContext());
                Toast.makeText(getApplicationContext(), "New Message: " + newMessage, Toast.LENGTH_SHORT).show();

                WakeLocker.release();
            }
        };*/

         events=(ImageButton)findViewById(R.id.events_btn);
        events.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyActivity.this,Event.class);
                startActivity(intent);
            }
        });

        guest=(ImageButton)findViewById(R.id.guest_btn1);
        guest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyActivity.this,guest.class);
                startActivity(intent);
            }
        });

        contacts=(ImageButton)findViewById(R.id.contacts);
        contacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyActivity.this,contacts.class);
                startActivity(intent);
            }
        });

        developer=(ImageButton)findViewById(R.id.i3);
        developer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyActivity.this, developer.class);
                startActivity(intent);
            }
        });

        sponsors=(ImageButton)findViewById(R.id.power_btn);
        sponsors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://www.getisteer.com/";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
        sponsors1=(ImageButton)findViewById(R.id.i2);
        sponsors1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://www.getisteer.com/";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });


        ListView lv = (ListView)findViewById(R.id.list);  // your listview inside scrollview
        lv.setOnTouchListener(new ListView.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        // Disallow ScrollView to intercept touch events.
                        v.getParent().requestDisallowInterceptTouchEvent(true);
                        break;

                    case MotionEvent.ACTION_UP:
                        // Allow ScrollView to intercept touch events.
                        v.getParent().requestDisallowInterceptTouchEvent(false);
                        break;
                }

                // Handle ListView touch events.
                v.onTouchEvent(event);
                return true;
            }
        });








        notification_Items = new ArrayList<NotificationItems>();
/*Check*/

        DatabaseHandler db = new DatabaseHandler(this);
        //Log.d("Insert: ", "Inserting ..");
        //db.addNotification(new NotificationDb(newMessage));
        Log.d("Reading: ", "Notification Reading all contacts..");
        List<NotificationDb> notificationDbs = db.getAllContacts();

        int x=0;
        for (NotificationDb cn : notificationDbs) {
            //      db.deleteContact(cn);
            notification_title[x++]=cn.getNotification();
            String log = "Id: "+cn.getId()+" ,Name: " + cn.getNotification()  ;
            //  Writing Contacts to log
            Log.d("Name: ", log+"**"+x);

        }




        int i;
        for(i=x-1;i>=0;i--) {
            notification_Items.add(new NotificationItems(notification_title[i]));
            // notification_Items.add(new NotificationItem("Welcome to Manusys!!"));
        }



        notification_adapter = new NotificationListAdapter(this,notification_Items);
        notification_list= (ListView)findViewById(R.id.list);
        notification_list.setAdapter(notification_adapter);

        ImageButton refresh=(ImageButton)findViewById(R.id.imageButton);
        refresh.setOnClickListener(new ImageButton.OnClickListener() {
            @Override
            public void onClick(View p_v) {
                // TODO Auto-generated method stub
                Log.d("hi","hi");
               // notification_adapter.notifyDataSetChanged();
                /* Get ImageView Object */
                ImageButton iv = (ImageButton) findViewById(R.id.imageButton);

/* Create Animation */
                Animation rotation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.button_rotate);
                rotation.setRepeatCount(Animation.INFINITE);

/* start Animation */
                int i,c=0;
                iv.startAnimation(rotation);

                for(i=0;i<1000;i++)
                {
                    c=c+1;
                }


                onCreate(savedInstanceState);
            }
        });












    }
    private final BroadcastReceiver mHandleMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String newMessage = intent.getExtras().getString(EXTRA_MESSAGE);
            // Waking up mobile if it is sleeping
            WakeLocker.acquire(getApplicationContext());

            /**
             * Take appropriate action on this message
             * depending upon your app requirement
             * For now i am just displaying it on the screen
             * */

            // Showing received message
//            lblMessage.append(newMessage + "\n");
            Toast.makeText(getApplicationContext(), "New Message: " + newMessage, Toast.LENGTH_LONG).show();

            // Releasing wake lock
            WakeLocker.release();
        }
    };


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return super.onCreateOptionsMenu(menu);
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
    protected void onDestroy(){
        super.onDestroy();
        if (mRegisterTask != null) {
            mRegisterTask.cancel(true);
        }
        try {
            unregisterReceiver(mHandleMessageReceiver);
            GCMRegistrar.onDestroy(this);
        } catch (Exception e) {
            Log.e("UnRegister Receiver Error", "> " + e.getMessage());
        }

    }
}
