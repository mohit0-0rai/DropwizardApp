package com.dropapp.app.repository;

import com.dropapp.app.entity.User;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {
    @Override
    public User map(ResultSet rs, StatementContext ctx) throws SQLException {
        User user = new User();
        user.setEmail(rs.getString("email"));
        user.setCreationDate(rs.getTimestamp("creation_date"));
        user.setEmail(rs.getString("email"));
        user.setId(rs.getInt("id"));
        user.setPassword(rs.getString("password"));
        user.setLastLogin(rs.getTimestamp("last_login"));
        user.setPhoneNumber(rs.getString("phone_number"));

        return user;
    }
}
