package com.oaxaca.turismo.mercados;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.oaxaca.turismo.mercados.conexion.Peticiones;

import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity {
    static String base_url = "http://hernandezislasadrian.000webhostapp.com/";
    static String llave="r5da3dfd0dssw4hfohu9fdgrv14";
    //static String base_url = "http:/10.0.0.13/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Thread hilo = new Thread(new Runnable() {
            @Override
            public void run() {

                Peticiones peticion = new Peticiones(getApplicationContext(),base_url+"Mercado/mercados/"+llave);
                Peticiones peticion2 = new Peticiones(getApplicationContext(),base_url+"Mercado/mercadoById/"+llave+"/"+8);
                Peticiones peticion3 = new Peticiones(getApplicationContext(),base_url+"Mercado/imgFromMercado/"+llave+"/"+8);
                Peticiones peticion4 = new Peticiones(getApplicationContext(),base_url+"Mercado/localesDelMercado/"+llave+"/"+8);

                while (!peticion.getBanderita() ||
                        !peticion2.getBanderita() ||
                        !peticion3.getBanderita() ||
                        !peticion4.getBanderita()){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_LONG).show();
                    }
                }
                principal.lista=peticion.getJSON();
                principal.infomer=peticion2.getJSON();
                principal.galeri=peticion3.getJSON();
                principal.giros = peticion4.getJSON();
                Intent intent = null;
                if(principal.lista!=null && principal.infomer!=null && principal.galeri!=null && principal.giros!=null)
                {
                    intent = new Intent(getApplicationContext(), principal.class);
                    startActivity(intent);
                    finish();
                }
                else{
                    System.out.println("PETICIONES:  \nLISTA: "+principal.lista+
                            "\nLISTA: "+principal.infomer +
                            "\nGALERIA: "+principal.galeri+
                            "\nGIROS: "+principal.giros
                    );
                    TimerTask timerTask = new TimerTask() {
                        int cont = 3;
                        @Override
                        public void run() {
                            if(cont > 0){
                                System.out.println("Cerrando en..."+ cont);
                                cont--;
                            }
                            else if(cont == 0)
                                finish();
                        }
                    };
                    Timer t = new Timer();
                    t.schedule(timerTask,1000,1000);
                }

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

