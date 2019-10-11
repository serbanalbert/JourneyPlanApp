package com.siit.JourneyPlanApp.DAO;


import com.siit.JourneyPlanApp.Mapper.RouteMapper;
import com.siit.JourneyPlanApp.Model.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class RoutesDAO extends JdbcDaoSupport {

    @Autowired
    public RoutesDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    public int insertNewRoute(String numeRuta, Long idRuta) {

        return this.getJdbcTemplate().update(
                "INSERT INTO rute VALUES (?, ?)", idRuta, numeRuta);

    }

    public long findMaxId() {
        // Select .. from App_User u Where u.User_Name = ?
        String sql = "Select MAX(id_ruta) from rute";

        try {
            Long  routeInfo = this.getJdbcTemplate().queryForObject(sql, Long.class);

            if(routeInfo != null){
                return routeInfo.longValue();
            }
            else{
                return 0;
            }

        } catch (EmptyResultDataAccessException e) {
            return 0;
        }
    }

    public Route findRoute(String numeRuta){

        String sql = RouteMapper.BASE_SQL + " where nume_ruta=?";

        Object[] params = new Object[] { numeRuta };
        RouteMapper mapper = new RouteMapper();
        try {
            Route routeInfo = this.getJdbcTemplate().queryForObject(sql, params, mapper);
            return routeInfo;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public long findIdByRouteName(String numeRuta){

        String sql =  "Select id_ruta from rute where nume_ruta=?";
        Object[] params = new Object[] { numeRuta };
        try {
            long routeInfo = this.getJdbcTemplate().queryForObject(sql, params, Long.class);
            return routeInfo;
        } catch (EmptyResultDataAccessException e) {
            return 0;
        }
    }

    public List<Route> findAllRoutes(){

        String sql = RouteMapper.BASE_SQL;
        RouteMapper mapper = new RouteMapper();
        try {
            List<Route> routeInfo = this.getJdbcTemplate().query(sql, mapper);
            return routeInfo;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }

    }

}
