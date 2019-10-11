package com.siit.JourneyPlanApp.DAO;

import com.siit.JourneyPlanApp.Mapper.ReservationMapper;
import com.siit.JourneyPlanApp.Model.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import javax.transaction.Transactional;
import java.sql.Date;
import java.util.List;

@Repository
@Transactional
public class ReservationDAO extends JdbcDaoSupport {

    @Autowired
    public ReservationDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    @Autowired
    private RoutesDAO routeDAO;

    public int insertNewReservation(long idRezervare, String numeClient, String prenumeClient, String email, String nrTelefon, String idUser, long idRuta, Date dataRezervare) {

        return this.getJdbcTemplate().update(
                "INSERT INTO rezervare VALUES (?, ?, ?, ?, ?, ?, ?, ?)", idRezervare, numeClient, prenumeClient, email, nrTelefon, idUser, idRuta, dataRezervare);

    }

    public long findMaxId() {
        // Select .. from App_User u Where u.User_Name = ?
        String sql = "Select MAX(id_rezervare) from rezervare";

        try {
            Long  rezervareInfo = this.getJdbcTemplate().queryForObject(sql, Long.class);
            if(rezervareInfo != null){
                return rezervareInfo.longValue();
            }else {
                return 0;
            }
        } catch (EmptyResultDataAccessException e) {
            return 0;
        }
    }

    public List<Reservation> findReservation(String dataRezervare, String numeRuta){

        String sql = "Select id_rezervare, nume_cl, prenume_cl, email, nr_telefon, id_user, rezervare.id_ruta, to_char(data_rezervare, 'dd-mm-yyyy') as data_rezervare from rezervare, users, rute WHERE rute.id_ruta=rezervare.id_ruta AND users.username = rezervare.id_user AND data_rezervare=? AND rute.id_ruta=? GROUP BY id_rezervare, rezervare.id_ruta";

        long idRuta = routeDAO.findIdByRouteName(numeRuta);


        Object[] params = new Object[] { dataRezervare, idRuta };
        ReservationMapper mapper = new ReservationMapper();
        try {
            List<Reservation> rezervareInfoList = this.getJdbcTemplate().query(sql, params, mapper);
            return rezervareInfoList;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }


    public List<Reservation> findUnAllocatedReservation(String dataRezervare, String numeRuta){

        String sql = "Select id_rezervare, nume_cl, prenume_cl, email, nr_telefon, id_user, rezervare.id_ruta, to_char(data_rezervare, 'dd-mm-yyyy') as data_rezervare from rezervare, users, rute WHERE rute.id_ruta=rezervare.id_ruta AND users.username = rezervare.id_user AND data_rezervare=? AND rute.id_ruta=? AND id_rezervare NOT IN (select alocari.id_rezervare from alocari,rezervare,masini where alocari.id_rezervare=rezervare.id_rezervare AND alocari.id_masina=masini.nr_masina) GROUP BY id_rezervare, rezervare.id_ruta";

        long idRuta = routeDAO.findIdByRouteName(numeRuta);


        Object[] params = new Object[] { Date.valueOf(dataRezervare), idRuta };
        ReservationMapper mapper = new ReservationMapper();
        try {
            List<Reservation> rezervareInfoList = this.getJdbcTemplate().query(sql, params, mapper);
            return rezervareInfoList;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<Reservation> findAllocatedReservation(String dataRezervare, String numeRuta, String nrMasina){

        String sql = "Select id_rezervare, nume_cl, prenume_cl, email, nr_telefon, id_user, rezervare.id_ruta, to_char(data_rezervare, 'dd-mm-yyyy') as data_rezervare from rezervare, users, rute WHERE rute.id_ruta=rezervare.id_ruta AND users.username = rezervare.id_user AND data_rezervare=? AND rute.id_ruta=? AND id_rezervare IN (select alocari.id_rezervare from alocari,rezervare,masini where alocari.id_rezervare=rezervare.id_rezervare AND alocari.id_masina=masini.nr_masina and alocari.id_masina=?) GROUP BY id_rezervare, rezervare.id_ruta";

        long idRuta = routeDAO.findIdByRouteName(numeRuta);


        Object[] params = new Object[] { Date.valueOf(dataRezervare), idRuta, nrMasina };
        ReservationMapper mapper = new ReservationMapper();
        try {
            List<Reservation> rezervareInfoList = this.getJdbcTemplate().query(sql, params, mapper);
            return rezervareInfoList;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }



    public List<Reservation> findReservation(String dataRezervare){

            String sql = "Select id_rezervare, nume_cl, prenume_cl, email, nr_telefon, id_user, rezervare.id_ruta, to_char(data_rezervare, 'dd-mm-yyyy') as data_rezervare from rezervare, users, rute WHERE rute.id_ruta=rezervare.id_ruta AND users.username = rezervare.id_user AND data_rezervare=? GROUP BY id_rezervare, rezervare.id_ruta";


        Object[] params = new Object[] { dataRezervare };
        ReservationMapper mapper = new ReservationMapper();
        try {
            List<Reservation> rezervareInfoList = this.getJdbcTemplate().query(sql, params, mapper);
            return rezervareInfoList;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }


}
