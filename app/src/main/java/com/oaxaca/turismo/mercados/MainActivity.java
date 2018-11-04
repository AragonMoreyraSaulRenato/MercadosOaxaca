package com.oaxaca.turismo.mercados;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.oaxaca.turismo.mercados.conexion.Peticiones;


public class MainActivity extends AppCompatActivity {
    static String base_url = "http://mercadosoaxaca.ddns.net/";
    static String llave="r5da3dfd0dssw4hfohu9fdgrv14";
    //static String base_url = "http:/10.168.123.213/";

    //splash activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Thread hilo = new Thread(new Runnable() {
            @Override
            public void run() {

                Peticiones peticion = new Peticiones(getApplicationContext(),base_url+"Mercado/mercados/"+llave);
                Peticiones peticion2 = new Peticiones(getApplicationContext(),base_url+"Mercado/mercadoById/"+llave+"/"+8);
                Peticiones peticion3 = new Peticiones(getApplicationContext(),base_url+"Mercado/imgFromMercado/"+llave+"/"+8);
                Peticiones peticion4 = new Peticiones(getApplicationContext(),base_url+"Mercado/localesDelMercado/"+llave+"/"+8);
                while (!peticion.getBanderita() &&
                        !peticion2.getBanderita() &&
                        !peticion3.getBanderita() &&
                        !peticion4.getBanderita()){
                    try {
                        Thread.sleep(100);

                    } catch (InterruptedException e) {
                        Toast.makeText(getApplicationContext(),R.string.error_conexion,Toast.LENGTH_LONG).show();
                    }
                }
                principal.lista=peticion.getJSON();
                principal.infomer=peticion2.getJSON();
                principal.galeri=peticion3.getJSON();
                principal.giros = peticion3.getJSON();

                Intent intent = new Intent(getApplicationContext(), principal.class);

                startActivity(intent);
                finish();
            }
        });
        hilo.start();
        super.onCreate(savedInstanceState);
    }
    public static String getBase_url(){
        return base_url;
    }

    public static String getLlave(){
        return llave;
    }
}

