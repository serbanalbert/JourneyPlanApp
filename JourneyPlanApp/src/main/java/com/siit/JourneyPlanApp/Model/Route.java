package com.siit.JourneyPlanApp.Model;


public class Route {

    private long idRuta;

    private String numeRuta;


    public Route(){

    }

    public Route(String numeRuta, long idRuta){
        this.numeRuta = numeRuta;
        this.idRuta = idRuta;
    }

    public String getNumeRuta(){ return numeRuta; }

    public long getIdRuta(){
        return idRuta;
    }

    public void setNumeRuta(String numeRuta){
        this.numeRuta = numeRuta;
    }

    public void setIdRuta(long idRuta){
        this.idRuta = idRuta;
    }

}
