package com.siit.JourneyPlanApp.DAO;


import com.siit.JourneyPlanApp.Mapper.CarsMapper;
import com.siit.JourneyPlanApp.Model.Cars;
import net.bytebuddy.pool.TypePool;
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
public class CarsDAO extends JdbcDaoSupport {

    @Autowired
    public CarsDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }


    public int insertNewCar(String nr_masina, Integer nr_locuri) {

        return this.getJdbcTemplate().update(
                "INSERT INTO masini VALUES (?, ?)", nr_masina, nr_locuri);

    }


    public Cars findCars(String nrMasina) {
        // Select .. from App_User u Where u.User_Name = ?
        String sql = CarsMapper.BASE_SQL + " where nr_masina=?";

        Object[] params = new Object[] { nrMasina };
        CarsMapper mapper = new CarsMapper();
        try {
            Cars carInfo = this.getJdbcTemplate().queryForObject(sql, params, mapper);
            return carInfo;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }


    public List<Cars> findCarUnAllocated(String dataRezervare){

        String sql = "Select nr_masina, nr_locuri From masini m where m.nr_locuri > (SELECT COUNT(id_alocare) FROM alocari a where a.id_masina = m.nr_masina AND a.id_rezervare IN (SELECT id_rezervare from rezervare WHERE data_rezervare=?))";

        Object[] params = new Object[] { Date.valueOf(dataRezervare) };
        CarsMapper mapper = new CarsMapper();
        try{
            List<Cars> listCarsUnallocated = this.getJdbcTemplate().query(sql, params, mapper);
            return listCarsUnallocated;
        } catch(EmptyResultDataAccessException e){
            return null;
        }
    }


    public List<Cars> findAllCars(){

        String sql = CarsMapper.BASE_SQL;
        CarsMapper mapper = new CarsMapper();
        try {
            List<Cars> carsList = this.getJdbcTemplate().query(sql, mapper);
            return carsList;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }

    }

}
