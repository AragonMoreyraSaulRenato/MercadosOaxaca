package com.oaxaca.turismo.mercados.activitys;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.oaxaca.turismo.mercados.R;
import com.oaxaca.turismo.mercados.adapter.AdaptadorCategorias;
import com.oaxaca.turismo.mercados.clases.Categoria;
import com.oaxaca.turismo.mercados.clases.Local;

import java.util.ArrayList;

public class Lista extends Fragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recy);
        RecyclerView.LayoutManager recyManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(recyManager);
        RecyclerView.Adapter recyAdapter = new AdaptadorCategorias(getContext(),getArrayCategoria());
        recyclerView.setAdapter(recyAdapter);
        return view;
    }

    public ArrayList<Categoria> getArrayCategoria(){
        ArrayList<Local> lo = new ArrayList<>();
        lo.add(new Local("Local #1","https://c1.staticflickr.com/5/4636/25316407448_de5fbf183d_o.jpg"));
        lo.add(new Local("Local #2","https://i.redd.it/tpsnoz5bzo501.jpg"));
        lo.add(new Local("Local #3","https://i.redd.it/qn7f9oqu7o501.jpg"));
        lo.add(new Local("Local #4","https://i.redd.it/j6myfqglup501.jpg"));
        lo.add(new Local("Local #5","https://i.redd.it/0h2gm1ix6p501.jpg"));
        lo.add(new Local("Local #185","https://i.redd.it/k98uzl68eh501.jpg"));
        lo.add(new Local("Local #145","https://i.redd.it/glin0nwndo501.jpg"));
        lo.add(new Local("Local #141","https://i.redd.it/obx4zydshg601.jpg"));



        ArrayList<Categoria> ca = new ArrayList<>();
        ca.add(new Categoria("Comedores",lo));
        ca.add(new Categoria("Carnes",lo));
        ca.add(new Categoria("Antojitos",lo));
        ca.add(new Categoria("Flores",lo));
        ca.add(new Categoria("Chocolates",lo));
        ca.add(new Categoria("Panes",lo));
        return ca;
    }

}