package com.mythawk.yixing.login;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;



@Mapper
@Repository
public interface IUserDao {
    User login(@Param("loginName") String loginName);
    User findAll();
    void update(User user);
}
