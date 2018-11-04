package com.oaxaca.turismo.mercados.clases;


import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.oaxaca.turismo.mercados.MainActivity;
import com.oaxaca.turismo.mercados.R;
import com.oaxaca.turismo.mercados.conexion.VolleySingleton;

import org.json.JSONException;
import org.json.JSONObject;

public class MyFirebaseMessagingService extends FirebaseMessagingService{


    private static final String TAG = "HOLA ";


    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.e(TAG, "From: " + remoteMessage.getFrom());

      /*  if (remoteMessage == null)
            return;*/


        // Check if message contains a data
        //if (remoteMessage.getData().size() > 0) {
            Log.e(TAG, "Data Payload: " + remoteMessage.getData().toString().replace("=",":"));

            try {
                JSONObject json = new JSONObject(remoteMessage.getData().toString().replace("=",":"));
                try{

                    String nombre = json.getString("titulo");
                    String mensaj = json.getString("mensaje");
                    String urlim = json.getString("imagen");

                    Intent intent = new Intent(Intent.ACTION_VIEW,
                            Uri.parse("https://www.youtube.com/"));
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_ONE_SHOT);
                    Uri sounduri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                    final NotificationCompat.Builder builder= new NotificationCompat.Builder(this);
                    builder.setContentTitle(nombre);
                    builder.setContentText(mensaj);
                    builder.setContentIntent(pendingIntent);
                    builder.setSound(sounduri);
                    builder.setSmallIcon(R.mipmap.ic_launcher_round);

                    ImageRequest imageRequest= new ImageRequest(urlim, new Response.Listener<Bitmap>() {
                        @Override
                        public void onResponse(Bitmap response) {
                            builder.setStyle(new NotificationCompat.BigPictureStyle().bigPicture(response));
                            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

                            notificationManager.notify(12312,builder.build());

                        }
                    }, 0, 0, null,Bitmap.Config.RGB_565, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    });

                    VolleySingleton.getInstanciaVolley(this).addToRequestQueue(imageRequest);

                }catch (Exception ex){
                    Toast.makeText(this,ex.toString(),Toast.LENGTH_LONG).show();
                }
            } catch (Exception e) {
                Log.e(TAG, "Exception: " + e.getMessage());
            }
       // }
    }


}
