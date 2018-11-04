package com.oaxaca.turismo.mercados.activitys;
import android.app.ProgressDialog;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.oaxaca.turismo.mercados.MainActivity;
import com.oaxaca.turismo.mercados.R;
import com.oaxaca.turismo.mercados.clases.Mercado;
import de.hdodenhof.circleimageview.CircleImageView;
import io.behindthemath.justifiedtextview.JustifiedTextView;

public class Head extends Fragment {
    private TextView name,address,horario;
    private CircleImageView logo;
    private Typeface font;
    private JustifiedTextView history;
    private Mercado mercado;
    private ProgressDialog pDialog;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.head, container, false);
        name        = (TextView)view.findViewById(R.id.nameMarket);
        history    = (JustifiedTextView) view.findViewById(R.id.history);
        address     = (TextView)view.findViewById(R.id.address);
        logo= (CircleImageView) view.findViewById(R.id.logo);
        horario = (TextView) view.findViewById(R.id.hora);

        refresh();
        return view;
    }

    public void setMercado(Mercado mercado){

        if(mercado == null) {
            this.mercado = new Mercado("HOLO","JOLO","JILO","LOLO","KOKO");
        }
        else
            this.mercado = mercado;
    }

    public void refresh(){
        name.setText(mercado.getNombre()+"");
        history.setText(mercado.getHistoria()+"");
        address.setText(mercado.getDireccion()+"");
        Glide.with(getContext()).load(MainActivity.getBase_url()+mercado.getImag()).into(logo);
    }
}

