package com.emall.dao;

import com.emall.model.User;
import org.apache.ibatis.annotations.Param;


public interface UserMapper {

    User getUser(@Param("username") String username, @Param("password") String password);
}
