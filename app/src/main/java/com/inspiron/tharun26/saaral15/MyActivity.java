package com.inspiron.tharun26.saaral15;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gcm.GCMRegistrar;

import static com.inspiron.tharun26.saaral15.CommonUtilities.DISPLAY_MESSAGE_ACTION;
import static com.inspiron.tharun26.saaral15.CommonUtilities.SENDER_ID;
import static com.inspiron.tharun26.saaral15.CommonUtilities.EXTRA_MESSAGE;

public class MyActivity extends Activity {
    /*
GCM
 */
    ConnectionDetector cd;
    public static String name;
    public static String email;
    AsyncTask<Void, Void, Void> mRegisterTask;
    BroadcastReceiver mHandleMessageReceiver = null;


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

        name = "ManusysID";
        email = "ManusysEmail";

        // Make sure the device has the proper dependencies.
        GCMRegistrar.checkDevice(this);

        // Make sure the manifest was properly set - comment out this line
        // while developing the app, then uncomment it when it's ready.
        GCMRegistrar.checkManifest(this);


        registerReceiver(mHandleMessageReceiver, new IntentFilter(
                DISPLAY_MESSAGE_ACTION));

        // Get GCM registration id
        final String regId = GCMRegistrar.getRegistrationId(this);

        // Check if regid already presents
        if (regId.equals("")) {
            // Registration is not present, register now with GCM
            GCMRegistrar.register(this, SENDER_ID);
        } else {
            // Device is already registered on GCM
            if (GCMRegistrar.isRegisteredOnServer(this)) {
                // Skips registration.
                Toast.makeText(getApplicationContext(), "Already registered with GCM", Toast.LENGTH_LONG).show();
            } else {
                // Try to register again, but not in the UI thread.
                // It's also necessary to cancel the thread onDestroy(),
                // hence the use of AsyncTask instead of a raw thread.
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

        mHandleMessageReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String newMessage = intent.getExtras().getString(EXTRA_MESSAGE);
                // Waking up mobile if it is sleeping
                WakeLocker.acquire(getApplicationContext());
                Toast.makeText(getApplicationContext(), "New Message: " + newMessage, Toast.LENGTH_SHORT).show();

                WakeLocker.release();
            }
        };

            }


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
}
