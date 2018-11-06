package com.oaxaca.turismo.mercados;

public class CardItem {

    private String mTitleResource;
    private int idmercado;
    private String urlimagen;

    public CardItem(String title,int id_mercado,String url) {
        mTitleResource = title;
        idmercado=id_mercado;
        urlimagen=url;
    }

    public String getTitle() {
        return mTitleResource;
    }
    public int getIdmercado() {
        return idmercado;
    }

    public String getUrlimagen() {
        return urlimagen;
    }
}