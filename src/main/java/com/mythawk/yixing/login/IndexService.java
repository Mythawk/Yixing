package com.mythawk.yixing.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class IndexService implements IIndexService{
    @Autowired
    private IUserService userService;

    @Override
    public ReturnInfo doLogin(LoginInfo loginInfo) {
        ReturnInfo returnInfo = new ReturnInfo();
        User user = userService.findByLoginName(loginInfo.getLoginName());
        if (user == null || user.getId() == null){
            returnInfo.setErrCode(1);
            returnInfo.setMsg("该用户不存在");
        }else if (user.getPwd().equals(loginInfo.getPassword()) ){
            loginInfo.setId(user.getId());
            loginInfo.setPassword(null);
            returnInfo.setData(user);
        }
        else {
            returnInfo.setErrCode(3);
            returnInfo.setMsg("密码错误");
        }
        return returnInfo;
    }
}
