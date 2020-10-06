package com.mythawk.yixing.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

@Controller
@RequestMapping("/user")
public class UserAction {
    @Resource
    private IUserService userService;


    @RequestMapping("/adminAuto")
    public ModelAndView adminAuto(){
        System.out.println("**********");
        ModelAndView mv = new ModelAndView();
      //  mv.addObject("users",userService.findAll(null));
        mv.setViewName("manage/admin-info");
        return mv ;
    }
    @RequestMapping("/update")
    @ResponseBody
    public ReturnInfo dogLogin(@RequestBody UserInfo userInfo) {
        ReturnInfo returnInfo = null;
        System.out.println(userInfo);
        returnInfo = userService.doupdate(userInfo);
        return returnInfo ;
    }

}
