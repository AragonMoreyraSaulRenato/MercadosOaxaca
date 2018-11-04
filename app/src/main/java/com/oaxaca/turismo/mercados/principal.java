package com.oaxaca.turismo.mercados;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.oaxaca.turismo.mercados.activitys.Gallery;
import com.oaxaca.turismo.mercados.activitys.Head;
import com.oaxaca.turismo.mercados.activitys.Lista;
import com.oaxaca.turismo.mercados.adapter.Adaptador_ListaExpandible;
import com.oaxaca.turismo.mercados.clases.Categoria;
import com.oaxaca.turismo.mercados.clases.Local;
import com.oaxaca.turismo.mercados.clases.MenuModel;
import com.oaxaca.turismo.mercados.clases.Mercado;
import com.oaxaca.turismo.mercados.conexion.Peticiones;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
public class principal extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    //variables ocupadas;
    private ViewPager mViewPager;
    private SectionsPagerAdapter mSectionsPagerAdapter;
    Adaptador_ListaExpandible expandableListAdapter;
    ExpandableListView expandableListView;
    List<MenuModel> headerList = new ArrayList<>();
    HashMap<MenuModel, List<MenuModel>> childList = new HashMap<>();
    static JSONObject lista;
    static JSONObject infomer;
    static JSONObject galeri;
    static JSONObject giros;
    private double lo,la;
    String seleccionado;
    int selecc;
    Notification.Builder builder;
    NotificationManager nm;



    Head comi;
    Gallery comi2;
    Lista comi3;

    Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        nm= (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        expandableListView = findViewById(R.id.expandableListView);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));
        obtenerlosmercadosparaelmenu();
        botonesflotantes();

        mContext = getApplicationContext();

        comi = new Head() ;
        comi2 = new Gallery();
        comi3 = new Lista();
    }


    public void botonesflotantes(){

        FloatingActionButton nuevo= (FloatingActionButton) findViewById(R.id.fab);
        nuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String labelLocation = "VISITA OAXACA:"+seleccionado;
                    String uri = "geo:<" + la+ ">,<" + lo+ ">?q=<" +la + ">,<" + lo+ ">(" + labelLocation + ")";
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                    startActivity(intent);
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(), R.string.instala_nave,Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //esto era para poner los tres puntitos
        //getMenuInflater().inflate(R.menu.principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //metodo que genera la barra de navegacion segun las arreglos
    private void prepareMenuData() {
        int i=0;
        MenuModel childModel;
        List<MenuModel> childModelsList = new ArrayList<>();
        headerList = new ArrayList<>();
        MenuModel menuModel = new MenuModel("Zona Centro Histórico", true, true, "",i); //Menu of Java Tutorials
        headerList.add(menuModel);
        i++;
        for(int j=0; j<mercadosch.size(); j++){
            childModel  = new MenuModel(mercadosch.get(j).getNombre(), false, false, "",i);
            childModelsList.add(childModel);
            i++;}
        if (menuModel.hasChildren) {
            Log.d("API123", "here");
            childList.put(menuModel, childModelsList); }
        //---
        childModelsList = new ArrayList<>();
        menuModel = new MenuModel("Zona Centro", true, true, "",i); //Menu of Python Tutorials
        headerList.add(menuModel);
        i++;
        for(int j=0; j<mercadosc.size(); j++){
            childModel  = new MenuModel(mercadosc.get(j).getNombre(), false, false, "",i);
            childModelsList.add(childModel);
            i++; }

        if (menuModel.hasChildren) {
            childList.put(menuModel, childModelsList); }
        //----
        childModelsList = new ArrayList<>();
        menuModel = new MenuModel("Zona Norte", true, true, "",i); //Menu of Python Tutorials
        headerList.add(menuModel);
        i++;
        for(int j=0; j<mercadosn.size(); j++){
            childModel  = new MenuModel(mercadosn.get(j).getNombre(), false, false, "",i);
            childModelsList.add(childModel);
            i++; }

        if (menuModel.hasChildren) {
            childList.put(menuModel, childModelsList); }
        //--
        childModelsList = new ArrayList<>();
        menuModel = new MenuModel("Zona Sur", true, true, "",i); //Menu of Python Tutorials
        headerList.add(menuModel);
        i++;
        for(int j=0; j<mercadoss.size(); j++){
            childModel  = new MenuModel(mercadoss.get(j).getNombre(), false, false, "",i);
            childModelsList.add(childModel);
            i++; }
        if (menuModel.hasChildren) {
            childList.put(menuModel, childModelsList);
        }
        //-----
        childModelsList = new ArrayList<>();
        menuModel = new MenuModel("Zona Poniente", true, true, "",i); //Menu of Python Tutorials
        headerList.add(menuModel);
        i++;
        for(int j=0; j<mercadosp.size(); j++){
            childModel  = new MenuModel(mercadosp.get(j).getNombre(), false, false, "",i);
            childModelsList.add(childModel);
            i++; }

        if (menuModel.hasChildren) {
            childList.put(menuModel, childModelsList); }
        //----
        childModelsList = new ArrayList<>();
        menuModel = new MenuModel("Zona Oriente", true, true, "",i); //Menu of Python Tutorials
        headerList.add(menuModel);
        i++;
        for(int j=0; j<mercadoso.size(); j++){
            childModel  = new MenuModel(mercadoso.get(j).getNombre(), false, false, "",i);
            childModelsList.add(childModel);
            i++; }

        if (menuModel.hasChildren) {
            childList.put(menuModel, childModelsList); }
    }

    Peticiones peticion2,peticion3, peticion4;
    int i=0;


    //metodo que controla el funcionamiento de la barra de navegacion
    private void populateExpandableList(){

        expandableListAdapter = new Adaptador_ListaExpandible(this, headerList, childList);
        expandableListView.setAdapter(expandableListAdapter);
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                if (headerList.get(groupPosition).isGroup) {
                    if (!headerList.get(groupPosition).hasChildren) {
                        onBackPressed();
                    } }
                return false;
            }
        });

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

                if (childList.get(headerList.get(groupPosition)) != null) {
                    MenuModel model = childList.get(headerList.get(groupPosition)).get(childPosition);
                    seleccionado=model.getMenuName();

                    for(Mercado mer: mercadosc){

                        if(mer.getNombre().equals(model.getMenuName())){
                            la=mer.getLatitud();
                            lo=mer.getLongitud();
                            selecc=mer.getId_mercado();}
                    }
                    for(Mercado mer: mercadosch){
                        if(mer.getNombre().equals(model.getMenuName())){
                            la=mer.getLatitud();
                            lo=mer.getLongitud();
                            selecc=mer.getId_mercado();}
                    }
                    for(Mercado mer: mercadosn){
                        if(mer.getNombre().equals(model.getMenuName())){
                            la=mer.getLatitud();
                            lo=mer.getLongitud();
                            selecc=mer.getId_mercado(); }
                    }
                    for(Mercado mer: mercadoss){
                        if(mer.getNombre().equals(model.getMenuName())){
                            la=mer.getLatitud();
                            lo=mer.getLongitud();
                            selecc=mer.getId_mercado();}
                    }
                    for(Mercado mer: mercadoso){
                        if(mer.getNombre().equals(model.getMenuName())){
                            la=mer.getLatitud();
                            lo=mer.getLongitud();
                            selecc=mer.getId_mercado();}
                    }for(Mercado mer: mercadosp){
                        if(mer.getNombre().equals(model.getMenuName())){
                            la=mer.getLatitud();
                            lo=mer.getLongitud();
                            selecc=mer.getId_mercado();}
                    }
                    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                    drawer.closeDrawer(GravityCompat.START);
                    onBackPressed();
                    /**
                     * MANDAR A LLAMAR METODO**/

                    peticion2 = new Peticiones(getApplicationContext(),
                            MainActivity.getBase_url()+"Mercado/mercadoById/"+MainActivity.getLlave()+"/"+selecc);
                    peticion3 = new Peticiones(getApplicationContext(),
                            MainActivity.getBase_url()+"Mercado/imgFromMercado/"+MainActivity.getLlave()+"/"+selecc);
                    peticion4 = new Peticiones(getApplicationContext(),
                            MainActivity.getBase_url()+"Mercado/localesDelMercado/"+MainActivity.getLlave()+"/"+selecc);

                    new Thread() {
                        public void run() {
                            while (i<1000) {
                                try {
                                    runOnUiThread(new Runnable() {

                                        @Override
                                        public void run() {
                                            if(peticion2.getBanderita() && peticion3.getBanderita() && peticion4.getBanderita()) {
                                                principal.infomer = peticion2.getJSON();
                                                principal.galeri = peticion3.getJSON();
                                                comi.setMercado(getMercado());
                                                comi.refresh();
                                                comi2.setArrayImages(getGaleria());
                                                comi2.refresh();
                                                comi3.setArrayCategoria(getCategorias());
                                                comi3.refresh();
                                            }
                                        }
                                    });
                                    Thread.sleep(300);

                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                i++;
                            }


                        }
                    }.start();
                    return true;
                }
                return false;
            }
        });
    }



    private ArrayList<Mercado> mercadosch;
    private ArrayList<Mercado> mercadosc;
    private  ArrayList<Mercado> mercadosn;
    private  ArrayList<Mercado> mercadoss;
    private ArrayList<Mercado> mercadoso;
    private  ArrayList<Mercado> mercadosp;

    private void obtenerlosmercadosparaelmenu(){
        mercadosch = new ArrayList<Mercado>();mercadosc = new ArrayList<Mercado>();
        mercadoss = new ArrayList<Mercado>();mercadosn = new ArrayList<Mercado>();
        mercadoso = new ArrayList<Mercado>();mercadosp = new ArrayList<Mercado>();

        JSONObject objJson = lista;
        try{
            JSONArray listaJson = objJson.optJSONArray("mercados");
            for(int i=0; i< listaJson.length(); i++) {
                JSONObject obj_dato = listaJson.getJSONObject(i);
                int id_m = obj_dato.getInt("idMercado");
                String nombre = obj_dato.getString("nombre");
                String zona = obj_dato.getString("zona");
                Double latitudm = obj_dato.getDouble("latitud");
                Double longitudm = obj_dato.getDouble("longitud");

                Mercado temp = new Mercado(id_m, nombre, zona, latitudm, longitudm);

                if (zona.equals("CH")) {
                    mercadosch.add(temp);
                } else if (zona.equals("C")) {
                    mercadosc.add(temp);
                } else if (zona.equals("S")) {
                    mercadoss.add(temp);
                } else if (zona.equals("N")) {
                    mercadosn.add(temp);
                } else if (zona.equals("O")) {
                    mercadoso.add(temp);
                } else if (zona.equals("P")) {
                    mercadosp.add(temp);
                }
            }
        }catch (Exception ex){
            Toast.makeText(this,ex.toString(),Toast.LENGTH_LONG).show();
        }
        if(objJson!=null){
            prepareMenuData();
            populateExpandableList();
        }else{
            //Toast.makeText(this,"es nulo",Toast.LENGTH_LONG).show();
        }
    }

    public Mercado getMercado(){
        Mercado m = new Mercado();
        try{
            JSONArray listaJson = infomer.optJSONArray("mercado");
            JSONObject obj_dato = listaJson.getJSONObject(0);
            m = new Mercado(obj_dato.getString("nombre"),
                    obj_dato.getString("historia"),
                    obj_dato.getString("direccion"),
                    obj_dato.getString("horario"),
                    obj_dato.getString("imagen"));
        }catch (Exception ex){

        }
        return m;
    }

    public ArrayList<String> getGaleria(){
        ArrayList<String> galeria = new ArrayList<>();
        try{
            JSONArray listaJson = galeri.optJSONArray("imagenes");
            for(int i=0; i<listaJson.length(); i++) {
                JSONObject obj_dato = listaJson.getJSONObject(i);
                galeria.add(MainActivity.getBase_url()+obj_dato.getString("imagen"));
            }
        }catch (JSONException ex){
            ex.printStackTrace();
        }
        return galeria;
    }

    public ArrayList<Categoria> getCategorias(){
        ArrayList<Categoria> cate = new ArrayList<>();
        ArrayList<Local> l = new ArrayList<>();
        int contArray = 0;
        try {
            String categoriaActual = "";
            JSONArray listaJson = giros.optJSONArray("locales");
            for(int i=0; i<listaJson.length(); i++){
                JSONObject obj_dato = listaJson.getJSONObject(i);
                if(i == 0) categoriaActual = obj_dato.getString("nombreGiro");
                if(categoriaActual.equals(obj_dato.getString("nombreGiro"))){
                    l.add(new Local(Integer.parseInt(obj_dato.getString("idLocal")),
                            obj_dato.getString("nombre"),
                            obj_dato.getString("nombreGiro"),
                            obj_dato.getString("imagen")));
                }
                else{
                    cate.add(new Categoria(categoriaActual,l));
                    l.clear();
                }
            }
        }catch (JSONException e){
            e.printStackTrace();
        }
        return cate;
    }


    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case 0:
                    comi.setMercado(getMercado());
                    return comi;
                case 1:
                    comi2.setArrayImages(getGaleria());
                    return comi2;
                case 2:
                    comi3.setArrayCategoria(getCategorias());
                    return comi3;
            }

            return null;
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }
    }

}