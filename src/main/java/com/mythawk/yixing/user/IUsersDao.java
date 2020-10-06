package com.mythawk.yixing.user;


import com.mythawk.yixing.login.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface IUsersDao {

    Users findByNumb(@Param("numb")String numb);
    void update(Users users);
    void insertUsers(Users users);
    void updatePwd(Users users);
    void updateName(Users users);
    void updateImage(Users users);
}
