package com.mythawk.yixing.login;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserService implements  IUserService {
    @Resource
    private IUserDao userDao;

    @Override
    public User findAll() {
        return userDao.findAll();
    }

    @Override
    public User findByLoginName(String loginName) {
        return userDao.login(loginName);
    }

    @Override
    public ReturnInfo doupdate(UserInfo userInfo) {
        ReturnInfo returnInfo = new ReturnInfo();
        User user = userDao.findAll();
        if (user.getPwd().equals(userInfo.getPassword1())&&userInfo.getPassword2().equals(userInfo.getPassword3())){
            user.setPwd(userInfo.getPassword2());
            userDao.update(user);
            returnInfo.setErrCode(0);
            returnInfo.setMsg("修改成功！");
        }else {
            returnInfo.setErrCode(1);
            returnInfo.setMsg("修改失败！");
        }
        return returnInfo;
    }
}
