package com.siit.JourneyPlanApp.Mapper;

import com.siit.JourneyPlanApp.Model.Allocation;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AllocationMapper implements RowMapper<Allocation> {

    public static final String BASE_SQL =

            "Select id_alocare, rezervare.id_rezervare, id_masina from alocari, rezervare, masini where alocari.id_masina=masini.nr_masina AND alocari.id_rezervare=rezervare.id_rezervare";

    @Override
    public Allocation mapRow(ResultSet rs, int rowNum) throws SQLException {
        long idAlocare = rs.getLong("id_alocare");
        long idRezervare = rs.getLong("id_rezervare");
        String nrMasina = rs.getString("id_masina");

        return new Allocation(idAlocare,idRezervare,nrMasina);
    }
}
