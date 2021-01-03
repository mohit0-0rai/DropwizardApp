package com.dropapp.app.repository;

import com.dropapp.app.entity.User;
import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

import java.util.List;

@RegisterRowMapper(UserMapper.class)
public interface UserRepository {
    @RegisterRowMapper(UserMapper.class)
    @SqlQuery("select * from user")
    List<User> getUsers();

    @SqlQuery("select * from user where id = ?")
    User getUser(Integer id);

}
