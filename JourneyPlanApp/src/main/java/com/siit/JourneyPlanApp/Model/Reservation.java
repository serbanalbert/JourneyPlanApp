package com.siit.JourneyPlanApp.Model;

import java.sql.Date;

public class Reservation {

    private long idRezervare;

    private String numeClient;
    private String prenumeClient;

    private String email;
    private String nrTelefon;

    private String idUser;
    private long idRuta;

    private Date dataRezervare;

    public Reservation(){

    }


    public Reservation(long idRezervare, String numeClient, String prenumeClient, String email, String nrTelefon, String idUser, long idRuta, Date dataRezervare){
        this.idRezervare = idRezervare;
        this.numeClient = numeClient;
        this.prenumeClient = prenumeClient;
        this.email = email;
        this.nrTelefon = nrTelefon;
        this.idUser = idUser;
        this.idRuta = idRuta;
        this.dataRezervare = dataRezervare;
    }

    public Reservation(long idRezervare, String numeClient, String prenumeClient, String email, String nrTelefon, String idUser, long idRuta, String dataRezervare){
        this.idRezervare = idRezervare;
        this.numeClient = numeClient;
        this.prenumeClient = prenumeClient;
        this.email = email;
        this.nrTelefon = nrTelefon;
        this.idUser = idUser;
        this.idRuta = idRuta;
        this.dataRezervare = Date.valueOf(dataRezervare);
    }

    public Date getDataRezervare() {
        return dataRezervare;
    }

    public void setDataRezervare(Date dataRezervare) {
        this.dataRezervare = dataRezervare;
    }

    public long getIdRezervare() {
        return this.idRezervare;
    }

    public long getIdRuta() {
        return this.idRuta;
    }

    public String getEmail() {
        return this.email;
    }

    public String getIdUser() {
        return this.idUser;
    }

    public String getNrTelefon() {
        return this.nrTelefon;
    }

    public String getNumeClient() {
        return this.numeClient;
    }

    public String getPrenumeClient() {
        return this.prenumeClient;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setIdRezervare(long idRezervare) {
        this.idRezervare = idRezervare;
    }

    public void setIdRuta(long idRuta) {
        this.idRuta = idRuta;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public void setNrTelefon(String nrTelefon) {
        this.nrTelefon = nrTelefon;
    }

    public void setNumeClient(String numeClient) {
        this.numeClient = numeClient;
    }

    public void setPrenumeClient(String prenumeClient) {
        this.prenumeClient = prenumeClient;
    }


}
