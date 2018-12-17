package com.oaxaca.turismo.mercados;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.oaxaca.turismo.mercados.clases.Mercado;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity {
    static JSONObject listam,urlimg;
    static ArrayList<Mercado> mercad = new ArrayList<Mercado>();
    private ViewPager mViewPager;
    private CardPagerAdapter mCardAdapter;
    private ShadowTransformer mCardShadowTransformer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mViewPager = (ViewPager) findViewById(R.id.viewPager);

        mCardAdapter = new CardPagerAdapter();
        crearCartasMercados();


        mCardShadowTransformer = new ShadowTransformer(mViewPager, mCardAdapter);

        mViewPager.setAdapter(mCardAdapter);
        mViewPager.setPageTransformer(false, mCardShadowTransformer);
        mViewPager.setOffscreenPageLimit(3);
        mCardShadowTransformer.enableScaling(true);

        Button button = (Button) findViewById(R.id.btntutorial);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),tutorialActivity.class);
                startActivity(i);

            }
        });
    }


    private void crearCartasMercados() {
        JSONObject objJson = listam;
        JSONObject objJson2 = urlimg;
        try{
            String urlimg="";
            JSONArray listaJson = objJson.optJSONArray("mercados");
            for(int i=0; i< listaJson.length(); i++) {
                JSONObject obj_dato = listaJson.getJSONObject(i);
                int id_m = obj_dato.getInt("idMercado");
                String nombre = obj_dato.getString("nombre");

                JSONArray listaJson2 = objJson2.optJSONArray("imagenes");
                for(int j=0;j<listaJson2.length();j++){
                    urlimg="";
                    JSONObject obj_dato2 = listaJson2.getJSONObject(j);
                    int id_me = obj_dato2.getInt("idMercado");
                    if(id_m==id_me){
                        urlimg =MainActivity.base_url+obj_dato2.getString("imagen");
                        break;
                    }
                }
                mCardAdapter.addCardItem(new CardItem(nombre,id_m,urlimg));
            }

        }catch (Exception ex){
            Toast.makeText(this,ex.toString(),Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }


}
