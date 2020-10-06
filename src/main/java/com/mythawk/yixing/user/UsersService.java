package com.mythawk.yixing.user;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class UsersService  implements IUsersService{
    @Resource
    private IUsersDao usersDao;

    @Override
    public Users findByNumb(String numb) {
        Users users = usersDao.findByNumb(numb);
        return users;
    }

    @Override
    public void insert(Users users) {
        usersDao.insertUsers(users);
    }

    @Override
    public void updatePwd(Users users) {
        usersDao.updatePwd(users);
    }

    @Override
    public void updateName(Users users) {
         usersDao.updateName(users);
    }

    @Override
    public void updateImage(Users users) {
        usersDao.updateImage(users);
    }
}
