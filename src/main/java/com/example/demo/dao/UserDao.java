package com.example.demo.dao;

import com.example.demo.bean.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {

    @Select("select * from user where name = #{name}")
    User findByUserName(@Param("name") String name);

    @Insert("insert into user(`name`,`tag`) values (#{name}, #{tag})")
    int insertUser(User user);

}