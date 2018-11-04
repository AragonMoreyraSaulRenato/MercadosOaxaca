package com.oaxaca.turismo.mercados.activitys;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.oaxaca.turismo.mercados.R;
import com.oaxaca.turismo.mercados.adapter.RecyclerViewAdapter;
import com.ms.square.android.expandabletextview.ExpandableTextView;

import java.util.ArrayList;

public class Activity_Local extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private static String base_url = "http://mercadosoaxaca.hopto.org/";
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();

    String historia = "De la mano de mi madre aprendí a ver la vida a caminar y a verla trabajar incansablemente. " + "\n" +
            "De la mano de mi abuela aprendí el oficio de hacer la nieve día a día y en que cada parte de los sabores, va un pedacito de corazón.";
    String productos = "Leche quemada\n" + "Nuez \n" +"Sorbete\n" +"Fresa\n" +"Zarzamora\n" +"Queso\n" +"Piña colada\n";
    String contacto = "Tel: 951 244 24 64";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        getImages();

        ExpandableTextView expTv1 = (ExpandableTextView) findViewById(R.id.expand_text_view);
        expTv1.setText(historia);

        ExpandableTextView expTv2 = (ExpandableTextView) findViewById(R.id.expand_text_view2);
        expTv2.setText(productos);

        RelativeLayout backLa = (RelativeLayout) findViewById(R.id.backButton);
        backLa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void getImages(){
        Log.d(TAG, "initImageBitmaps: preparing bitmaps.");

        mImageUrls.add("https://c1.staticflickr.com/5/4636/25316407448_de5fbf183d_o.jpg");
        mNames.add("Local #1");

        mImageUrls.add("https://i.redd.it/tpsnoz5bzo501.jpg");
        mNames.add("Local #2");

        mImageUrls.add("https://i.redd.it/qn7f9oqu7o501.jpg");
        mNames.add("Local #3");

        mImageUrls.add("https://i.redd.it/j6myfqglup501.jpg");
        mNames.add("Local #4");


        mImageUrls.add("https://i.redd.it/0h2gm1ix6p501.jpg");
        mNames.add("Local #5");

        mImageUrls.add("https://i.redd.it/k98uzl68eh501.jpg");
        mNames.add("Local #185");


        mImageUrls.add("https://i.redd.it/glin0nwndo501.jpg");
        mNames.add("Local #145");

        mImageUrls.add("https://i.redd.it/obx4zydshg601.jpg");
        mNames.add("Local #141");

        mImageUrls.add("https://i.imgur.com/ZcLLrkY.jpg");
        mNames.add("Local #14");

        initRecyclerView();

    }

    private void initRecyclerView() {
        Log.d(TAG, "initRecyclerView: init recyclerview");

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        final RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(layoutManager);
        final RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, mNames, mImageUrls, false);
        recyclerView.setAdapter(adapter);

    }

    /*
    private void getPetitions(){
        //RequestQueue colaPeticion;
        Network generarCache = new BasicNetwork(new HurlStack());
        Cache cache = new DiskBasedCache(getCacheDir(), 4096 * 4096);
        //colaPeticion = new RequestQueue(cache,generarCache);
        //colaPeticion.start();

        String url = base_url+"Mercado/mercado/saulpopo/1";
        JsonObjectRequest objetojson = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                    }
                },
                new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(),"Error Conexión",Toast.LENGTH_LONG).show();
                }
        });
        VolleySingleton.getInstanciaVolley(getApplicationContext()).addToRequestQueue(objetojson);
        //colaPeticion.add(objetojson);
        objetojson.setRetryPolicy(new DefaultRetryPolicy(400000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
    }

    private void procesoJSON (JSONObject objJson){
        try{
            ArrayList<String> mercados = new ArrayList<String>();
            JSONArray listaJson = objJson.optJSONArray("mercado");
            for(int i=0; i< listaJson.length(); i++){
                JSONObject obj_dato = listaJson.getJSONObject(i);
                String content = obj_dato.getString("idMercado");
                mercados.add(content);
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.activity_list_item,mercados);
        }catch (Exception ex){
            Toast.makeText(getApplicationContext(),"No se proceso",Toast.LENGTH_LONG).show();
        }
    }*/

}
