package com.example.demo.dao;

import com.example.demo.bean.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserDao {

    @Select("select * from user where name = #{name}")
    User findByUserName(@Param("name") String name);

}