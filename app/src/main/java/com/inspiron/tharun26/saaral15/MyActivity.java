package com.inspiron.tharun26.saaral15;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.TypedArray;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gcm.GCMRegistrar;

import static com.inspiron.tharun26.saaral15.CommonUtilities.DISPLAY_MESSAGE_ACTION;
import static com.inspiron.tharun26.saaral15.CommonUtilities.SENDER_ID;
import static com.inspiron.tharun26.saaral15.CommonUtilities.EXTRA_MESSAGE;

import com.inspiron.tharun26.saaral15.NotificationItems;

import java.util.ArrayList;
import java.util.List;

public class MyActivity extends Activity {
    /*
GCM
 */
    ConnectionDetector cd;
    public static String name;
    public static String email;
    AsyncTask<Void, Void, Void> mRegisterTask;


    private ListView notification_list;
    public static String[] notification_title=new String[50] ;
    private TypedArray notification_icons;
    private ArrayList<NotificationItems> notification_Items;
    private NotificationListAdapter notification_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
                Toast.makeText(getApplicationContext(), "Already registered with GCM", Toast.LENGTH_LONG).show();

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
