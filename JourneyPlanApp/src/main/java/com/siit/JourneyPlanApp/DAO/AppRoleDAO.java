package com.siit.JourneyPlanApp.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class AppRoleDAO extends JdbcDaoSupport {

    @Autowired
    public AppRoleDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    public List<String> getRoleNames(String username) {
        String sql = "Select tip_acces " //
                + " from users, categ_acces " //
                + " where users.id_acces = categ_acces.id_acces and users.username = ? ";

        Object[] params = new Object[] { username };

        List<String> roles = this.getJdbcTemplate().queryForList(sql, params, String.class);

        return roles;
    }

}