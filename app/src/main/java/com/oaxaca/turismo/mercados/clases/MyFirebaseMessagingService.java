package com.oaxaca.turismo.mercados.clases;


import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
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

import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    Bitmap bitmap;

    /**
     * se llama cuando se recibe un mensaje
     * @param remoteMessage Object representa el mensaje recibido de  Firebase Cloud Messaging.
     */
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        String mensaje = remoteMessage.getData().get("mensaje");
        String imagenUri = remoteMessage.getData().get("imagen");

        //Para obtener el Bitmap de la imagen desde la URL recibida
        try {
            bitmap = getBitmapfromUrl(imagenUri);
            enviarNotificaciones(mensaje, bitmap);
        }catch (Exception e){

        }

    }


    /**
     * Crear y mostrar una simple notificacion con el contenido del mensaje FCM.
     */

    private void enviarNotificaciones(String messageBody, Bitmap image) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0 , intent, PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        Notification.Builder notificationBuilder = new Notification.Builder(getApplicationContext())
                .setLargeIcon(image)/*Icono de la Notificacion*/
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentTitle(messageBody)
                .setStyle(new Notification.BigPictureStyle()
                        .bigPicture(image))/*Imagen de la Notificacion*/
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(100 , notificationBuilder.build());
    }

    /*
     *Para obtener el Bitmap de la imagen segun el URL recibido
     * */
    public Bitmap getBitmapfromUrl(String imageUrl) {
        try {
            URL url = new URL(imageUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap bitmap = BitmapFactory.decodeStream(input);
            return bitmap;

        } catch (Exception e) {
            return null;
        }
    }

}
