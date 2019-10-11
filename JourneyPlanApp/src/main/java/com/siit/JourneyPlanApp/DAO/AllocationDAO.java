package com.siit.JourneyPlanApp.DAO;

import com.siit.JourneyPlanApp.Mapper.AllocationMapper;
import com.siit.JourneyPlanApp.Model.Allocation;
import com.siit.JourneyPlanApp.Model.Cars;
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
public class AllocationDAO extends JdbcDaoSupport {

    @Autowired
    public AllocationDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }



    public int insertNewAllocation(long idAlocare, long idRezervare, String nrMasina){

        return this.getJdbcTemplate().update(
                "INSERT INTO alocari VALUES (?, ?, ?)", idAlocare, idRezervare, nrMasina);

    }

    public long findMaxId(){
        String sql = "Select MAX(id_alocare) from alocari";

        try {
            Long  allocationInfo = this.getJdbcTemplate().queryForObject(sql, Long.class);
            if(allocationInfo != null){
                return allocationInfo.longValue();
            }else{
                return 0;
            }

        } catch (EmptyResultDataAccessException e) {
            return 0;
        }
    }

    public List<Allocation> getReservationByCarPlate(String idMasina){

        String sql = "Select alocari.id_alocare, alocari.id_rezervare, alocari.id_masina from alocari, masini, rezervare where alocari.id_rezervare = rezervare.id_rezervare AND alocari.id_masina = masini.nr_masina AND alocari.id_masina = ?";


        Object[] params = new Object[] { idMasina };
        AllocationMapper mapper = new AllocationMapper();
        try {
            List<Allocation> allocationInfoList = this.getJdbcTemplate().query(sql, params, mapper);
            return allocationInfoList;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }




}
