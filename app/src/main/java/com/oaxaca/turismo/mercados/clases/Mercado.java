package com.oaxaca.turismo.mercados.clases;


public class Mercado  {
    private int id_mercado;
    private String nombre;
    private String zona;
    private Double latitud,longitud;
    private String historia;
    private String direccion;
    private String hora;
    private String imag;

    public Mercado (int id_mercado,String nombre,String zona,Double latitud,Double longitud){
        this.id_mercado=id_mercado;
        this.nombre=nombre;
        this.zona=zona;
        this.latitud=latitud;
        this.longitud=longitud;
    }

    public Mercado (String nombre,String zona,Double latitud,Double longitud){
        this.nombre=nombre;
        this.zona=zona;
        this.latitud=latitud;
        this.longitud=longitud;
    }

    public Mercado(String nombre, String historia, String direccion, String hora, String imag) {
        this.nombre = nombre;
        this.historia = historia;
        this.direccion = direccion;
        this.hora = hora;
        this.imag = imag;
    }

    public Mercado(){}

    public int getId_mercado(){
        return  id_mercado;
    }

    public String getNombre(){
        return nombre;}

    public String getZona() {
        return zona;
    }

    public Double getLatitud() {
        return latitud; }

    public Double getLongitud() {
        return longitud;
    }

    public void setHistoria(String h){
        this.historia = h;
    }
    public String getHistoria(){
        return historia;
    }

    public void setDireccion(String direccion){
        this.direccion = direccion;
    }
    public String getDireccion(){
        return direccion;
    }

    public void setImag(String img){
        this.imag = img;
    }
    public String getImag(){
        return imag;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

}

