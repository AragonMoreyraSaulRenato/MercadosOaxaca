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

    private RecyclerView.Adapter recyAdapter;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager recyManager;
    ArrayList<Categoria> cate;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list, container, false);

        cate = new ArrayList<>();

        recyclerView = (RecyclerView) view.findViewById(R.id.recy);
        recyManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(recyManager);
        recyAdapter = new AdaptadorCategorias(getContext(),cate);
        recyclerView.setAdapter(recyAdapter);
        return view;
    }

    public void setArrayCategoria(ArrayList<Categoria> arrayCategoria){
        this.cate = arrayCategoria;
    }

    public void refresh(){
        recyAdapter.notifyDataSetChanged();
    }

}