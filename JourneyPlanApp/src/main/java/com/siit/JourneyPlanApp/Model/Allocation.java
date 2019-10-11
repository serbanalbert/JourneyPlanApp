package com.siit.JourneyPlanApp.Model;

public class Allocation {

    private long idAlocare;
    private long idRezervare;
    private String nrMasina;

    public Allocation(){

    }

    public Allocation(long idAlocare, long idRezervare, String nrMasina){
        this.idAlocare = idAlocare;
        this.idRezervare = idRezervare;
        this.nrMasina = nrMasina;

    }

    public long getIdAlocare() {
        return idAlocare;
    }

    public long getIdRezervare() {
        return idRezervare;
    }

    public String getNrMasina() {
        return nrMasina;
    }

    public void setIdAlocare(long idAlocare) {
        this.idAlocare = idAlocare;
    }

    public void setIdRezervare(long idRezervare) {
        this.idRezervare = idRezervare;
    }

    public void setNrMasina(String nrMasina) {
        this.nrMasina = nrMasina;
    }
}
