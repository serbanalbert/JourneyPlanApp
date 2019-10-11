package com.siit.JourneyPlanApp.Mapper;

import com.siit.JourneyPlanApp.Model.AppUser;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AppUserMapper implements RowMapper<AppUser> {

    public static final String BASE_SQL //
            = "Select username, password from users ";

    @Override
    public AppUser mapRow(ResultSet rs, int rowNum) throws SQLException {

        String userName = rs.getString("username");
        String encrytedPassword = rs.getString("password");

        return new AppUser(userName, encrytedPassword);
    }

}
