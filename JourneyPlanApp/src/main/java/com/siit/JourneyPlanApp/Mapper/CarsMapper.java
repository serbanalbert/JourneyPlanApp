package com.siit.JourneyPlanApp.Mapper;

import com.siit.JourneyPlanApp.Model.Cars;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CarsMapper implements RowMapper<Cars> {

        public static final String BASE_SQL //
                = "Select nr_masina, nr_locuri from masini ";

        @Override
        public Cars mapRow(ResultSet rs, int rowNum) throws SQLException {

            String nrMasina = rs.getString("nr_masina");
            Integer nrLocuri = rs.getInt("nr_locuri");

            return new Cars(nrMasina, nrLocuri);
        }

}
