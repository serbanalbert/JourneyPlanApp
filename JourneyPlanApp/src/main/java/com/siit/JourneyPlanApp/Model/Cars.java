package com.siit.JourneyPlanApp.Model;


public class Cars {

    private String nrMasina;

    private int nrLocuri;

    public Cars(){

    }

    public Cars(String nrMasina, int nrLocuri){
        this.nrMasina = nrMasina;
        this.nrLocuri = nrLocuri;
    }

    public String getNrMasina(){

        return nrMasina;
    }

    public int getNrLocuri(){
        return nrLocuri;
    }

    public void setNrMasina(String nrMasina){
        this.nrMasina = nrMasina;
    }

    public void setNrLocuri(Integer nrLocuri){
        this.nrLocuri = nrLocuri;
    }
}
