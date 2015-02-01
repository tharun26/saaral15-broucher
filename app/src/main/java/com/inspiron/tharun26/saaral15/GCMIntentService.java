package com.inspiron.tharun26.saaral15;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.google.android.gcm.GCMBaseIntentService;

import java.util.List;

import static com.inspiron.tharun26.saaral15.CommonUtilities.SENDER_ID;
import static com.inspiron.tharun26.saaral15.CommonUtilities.displayMessage;

/**
 * Created by tharun26 on 25/1/15.
 */

public class GCMIntentService extends GCMBaseIntentService {

    private static final String TAG = "GCMIntentService";

    public GCMIntentService() {
        super(SENDER_ID);
    }

    /**
     * Method called on device registered
     **/
    @Override
    protected void onRegistered(Context context, String registrationId) {
        Log.i(TAG, "Device registered: regId = " + registrationId);
        displayMessage(context, "Your device registred with GCM");
       // Log.d("NAME", MainActivity.name);
        ServerUtilities.register(context, MyActivity.name, MyActivity.email, registrationId);
    }

    /**
     * Method called on device un registred
     * */
    @Override
    protected void onUnregistered(Context context, String registrationId) {
        Log.i(TAG, "Device unregistered");
        displayMessage(context, getString(R.string.gcm_unregistered));
        ServerUtilities.unregister(context, registrationId);
    }


    protected void storedb(String message)
    {
        DatabaseHandler db = new DatabaseHandler(this);
        Log.d("Insert: ", "Inserting ..");
        db.addNotification(new NotificationDb(message));
        Log.d("Reading: ", "Reading all contacts..");
        List<NotificationDb> contacts = db.getAllContacts();

        for (NotificationDb cn : contacts) {
              // db.deleteContact(cn);
            String log = "Id: "+cn.getId()+" ,Name: " + cn.getNotification()  ;
            //  Writing Contacts to log
            Log.d("Name: ", log);
        }


    }






    /**
     * Method called on Receiving a new message
     * */
    @Override
    protected void onMessage(Context context, Intent intent) {

        Log.i(TAG, "Received message");



        String message = intent.getExtras().getString("price");

        if(message=="")
        {
         message="Welcome to Saaral'2015";
        }

         storedb(message);



        /*Planning to store it in a databse*/
/*
        DatabaseHandler db = new DatabaseHandler(this);
         Log.d("Insert: ", "Inserting ..");
        db.addNotification(new NotificationDb(message));
         Log.d("Reading: ", "Reading all contacts..");
        List<NotificationDb> contacts = db.getAllContacts();

        for (NotificationDb cn : contacts) {
        //    db.deleteContact(cn);
           String log = "Id: "+cn.getId()+" ,Name: " + cn.getNotification()  ;
           //  Writing Contacts to log
            Log.d("Name: ", log);
        }
*/



        displayMessage(context, message);
        // notifies user
        generateNotification(context, message);



    }

    /**
     * Method called on receiving a deleted message
     * */
    @Override
    protected void onDeletedMessages(Context context, int total) {
        Log.i(TAG, "Received deleted messages notification");
        String message = getString(R.string.gcm_deleted, total);
        displayMessage(context, message);
        // notifies user
        generateNotification(context, message);
    }

    /**
     * Method called on Error
     * */
    @Override
    public void onError(Context context, String errorId) {
        Log.i(TAG, "Received error: " + errorId);
        displayMessage(context, getString(R.string.gcm_error, errorId));
    }

    @Override
    protected boolean onRecoverableError(Context context, String errorId) {
        // log message
        Log.i(TAG, "Received recoverable error: " + errorId);
        displayMessage(context, getString(R.string.gcm_recoverable_error,
                errorId));
        return super.onRecoverableError(context, errorId);
    }

    /**
     * Issues a notification to inform the user that server has sent a message.
     */
    int count=0;
    private  void generateNotification(Context context, String message) {


        /*

            DatabaseHandler db = new DatabaseHandler(this);
            Log.d("Insert: ", "Inserting ..");
            db.addNotification(new NotificationDb(message));
            Log.d("Reading: ", "Reading all contacts..");
            List<NotificationDb> contacts = db.getAllContacts();

            for (NotificationDb cn : contacts) {
                //    db.deleteContact(cn);
                String log = "Id: " + cn.getId() + " ,Name: " + cn.getNotification();
                //  Writing Contacts to log
                Log.d("Name: ", log);
            }
        */

        int icon = R.drawable.ic_logo;
        long when = System.currentTimeMillis();
        NotificationManager notificationManager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification = new Notification(icon, message, when);

        String title = context.getString(R.string.app_name);

        Intent notificationIntent = new Intent(context, MyActivity.class);
        // set intent so it does not start a new activity
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent intent =
                PendingIntent.getActivity(context, 0, notificationIntent, 0);
        notification.setLatestEventInfo(context, title, message, intent);
        notification.flags |= Notification.FLAG_AUTO_CANCEL;

        // Play default notification sound
        notification.defaults |= Notification.DEFAULT_SOUND;

        // Vibrate if vibrate is enabled
        notification.defaults |= Notification.DEFAULT_VIBRATE;
        notificationManager.notify(0, notification);





    }

}
