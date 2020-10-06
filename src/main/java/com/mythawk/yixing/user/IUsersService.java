package com.mythawk.yixing.user;

import org.springframework.stereotype.Service;

@Service
public interface IUsersService {

    Users findByNumb(String numb);
    void insert(Users users);
    void updatePwd(Users users);
    void updateName(Users users);
    void updateImage(Users users);
}
