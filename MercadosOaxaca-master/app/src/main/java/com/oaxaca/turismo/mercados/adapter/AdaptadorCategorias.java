package com.oaxaca.turismo.mercados.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.oaxaca.turismo.mercados.R;
import com.oaxaca.turismo.mercados.clases.Categoria;
import com.oaxaca.turismo.mercados.clases.Local;

import java.util.ArrayList;


public class AdaptadorCategorias extends RecyclerView.Adapter{

    Context mContext;
    ArrayList<Categoria> categorias;

    public AdaptadorCategorias(Context mContext, ArrayList<Categoria> categorias) {
        this.mContext = mContext;
        this.categorias = categorias;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View contetView = LayoutInflater.from(mContext).inflate(R.layout.listen_categoria,null);
        return new HolderCategoria(contetView);
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        Categoria cat = categorias.get(i);
        HolderCategoria hc = (HolderCategoria) viewHolder;
        hc.txt.setText(cat.getNombre());
        ArrayList<String> [] info = getInfoLocales(cat.getLocales());

        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(mContext, info[0], info[1], true);
        hc.img.setLayoutManager(layoutManager);
        hc.img.setAdapter(adapter);

    }

    public ArrayList<String> [] getInfoLocales(ArrayList<Local> locals){
        ArrayList<String> nom = new ArrayList<>();
        ArrayList<String> img = new ArrayList<>();

        for(int i=0; i<locals.size(); i++){
            nom.add(locals.get(i).getNombre());
            img.add(locals.get(i).getImageUrl());
        }
        return new ArrayList[]{nom,img};
    }

    @Override
    public int getItemCount() {
        return categorias.size();
    }


    public static class HolderCategoria extends RecyclerView.ViewHolder{
        RecyclerView img;
        TextView txt;
        public HolderCategoria(View v){
            super(v);
            img = (RecyclerView) v.findViewById(R.id.recycler);
            txt = (TextView) v.findViewById(R.id.categoria);
        }
    }
}
