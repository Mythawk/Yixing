package com.mythawk.yixing.login;

import org.springframework.stereotype.Service;


@Service
public interface IUserService {
    User findByLoginName(String loginName);
    User findAll();
    ReturnInfo doupdate(UserInfo userInfo);
}
