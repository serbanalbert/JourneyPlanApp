package com.siit.JourneyPlanApp.Mapper;

import com.siit.JourneyPlanApp.Model.Route;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RouteMapper implements RowMapper<Route> {

    public static final String BASE_SQL //
            = "Select id_ruta, nume_ruta from rute ";

    @Override
    public Route mapRow(ResultSet rs, int rowNum) throws SQLException {

        String numeRuta = rs.getString("nume_ruta");
        Long idRuta = rs.getLong("id_ruta");

        return new Route(numeRuta, idRuta);
    }
}
