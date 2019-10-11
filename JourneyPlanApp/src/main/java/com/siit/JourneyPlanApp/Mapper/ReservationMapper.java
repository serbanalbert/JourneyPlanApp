package com.siit.JourneyPlanApp.Mapper;

import com.siit.JourneyPlanApp.Model.Reservation;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReservationMapper implements RowMapper<Reservation> {

    public static final String BASE_SQL //
            = "Select id_rezervare, nume_cl, prenume_cl, email, nr_telefon, id_user, rute.id_ruta, data_rezervare from rezervare, users, rute WHERE rute.id_ruta=rezervare.id_ruta AND users.username = rezervare.id_user GROUP BY id_rezervare, rute.id_ruta";

    @Override
    public Reservation mapRow(ResultSet rs, int rowNum) throws SQLException {

//        Reservation rsrv = new Reservation();
//
//        rsrv.setIdRezervare(rs.getLong("id_rezervare"));
//        rsrv.setNumeClient(rs.getString("nume_cl"));
//        rsrv.setPrenumeClient(rs.getString("prenume_cl"));
//        rsrv.setEmail(rs.getString("email"));
//        rsrv.setNrTelefon(rs.getString("nr_telefon"));
//        rsrv.setIdUser(rs.getString("id_user"));
//        rsrv.setIdRuta(rs.getLong("id_ruta"));
//        rsrv.setDataRezervare(rs.getDate("data_rezervare"));
//
//        return rsrv;
        long idRezervare = rs.getLong("id_rezervare");;
        String numeClient = rs.getString("nume_cl");;
        String prenumeClient = rs.getString("prenume_cl");;
        String email = rs.getString("email");;
        String nrTelefon = rs.getString("nr_telefon");;
        String idUser = rs.getString("id_user");;
        long idRuta = rs.getLong("id_ruta");;
        Date dataRezervare = rs.getDate("data_rezervare");

        return new Reservation(idRezervare, numeClient, prenumeClient, email, nrTelefon, idUser, idRuta, dataRezervare);
    }
}
