package com.mythawk.yixing.user;

import com.google.gson.JsonArray;
import com.mythawk.yixing.login.User;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping("/users")
public class UsersAction {

    @Autowired
    private IUsersService usersService;

    /**
     *  登录返回JSON
     */
    @RequestMapping(value = "/redata",method = RequestMethod.POST)
    @ResponseBody
    public Map requestAction(@RequestBody Usersget usersget){
        System.out.println(usersget.getNumb());
        String number = usersget.getNumb();
        Users users = usersService.findByNumb(number);
        JsonArray json= new JsonArray();

        Map map = new HashMap();
        map.put("redata",users);
        if (users.getPwd()== Integer.parseInt(usersget.getPwd())&&users!=null){
            map.put("status","ok");
        }else {
            map.put("status","false");
        }
        return map;
    }

    /**
     *  注册返回JSON
     */
    @RequestMapping(value = "/registered",method = RequestMethod.POST)
    @ResponseBody
    public Map requestRegistered(@RequestBody Usersget usersget){
        System.out.println(usersget.getNumb());
        String number = usersget.getNumb();
        Users users = usersService.findByNumb(number);
        Map map = new HashMap();
        if (users == null){
            Users users2 = new Users();
            users2.setNumb(usersget.getNumb());
            users2.setPwd(Integer.parseInt(usersget.getPwd()));
            users2.setName("未命名");
            users2.setImage("http://192.168.1.100:90/YiXing/image/361.png");
            usersService.insert(users2);
            map.put("redata",users2);
            map.put("status","ok");
        } else {
            map.put("redata",null);
            map.put("status","false");
        }
        return map;
    }

    /**
     *  忘记密码返回JSON
     */
    @RequestMapping(value = "/forget",method = RequestMethod.POST)
    @ResponseBody
    public Map requestForget(@RequestBody Usersget usersget){
        System.out.println(usersget.getNumb());
        String number = usersget.getNumb();
        Users users3 = usersService.findByNumb(number);
        Map map = new HashMap();
        if (users3 != null){
            Users users2 = new Users();
            users2.setNumb(usersget.getNumb());
            users2.setPwd(Integer.parseInt(usersget.getPwd()));
            usersService.updatePwd(users2);
            users3 = usersService.findByNumb(number);
            map.put("redata",users3);
            map.put("status","ok");
        } else {
            map.put("redata",null);
            map.put("status","false");
        }
        return map;
    }

    /**
     *  修改名称
     */
    @ResponseBody
    @RequestMapping(value = "/editName" , method = RequestMethod.POST)
    public Map requestName(@RequestBody Usersget usersget){
        System.out.println(usersget.getNumb()+usersget.getName());
        Users users = new Users();
        users.setName(usersget.getName());
        users.setNumb(usersget.getNumb());
        usersService.updateName(users);
        Users users1 = usersService.findByNumb(usersget.getNumb());
        Map map = new HashMap();
        if (users1.getName().equals(usersget.getName())){
            map.put("redata",users1);
            map.put("status","ok");
        }else {
            map.put("redata",null);
            map.put("status","false");
        }
        return map;
    }

    /**
     *  修改头像
     */
    @ResponseBody
    @RequestMapping(value = "/editImage" , method = RequestMethod.POST)
    public Map requestImage(@RequestBody ImageGet imageGet){

        System.out.println("getNumb"+imageGet.getNumb());
        FileOutputStream imageOutFile = null;
        try {
            byte[] imageByteArray = Base64.decodeBase64(imageGet.getImage());
            imageOutFile = new FileOutputStream(new File("D:\\idea\\Yixing\\src\\main\\webapp\\image\\user\\"+imageGet.getNumb()+".jpg"),false);
            imageOutFile.write(imageByteArray);
        } catch (IOException e) {
            e.printStackTrace();
            Map map = new HashMap();
            map.put("redata",null);
            map.put("status","false");
            return map;
        }finally {
            if (imageOutFile!=null){
                try {
                    System.out.println("there is close");
                    imageOutFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        String imagepath = "http://192.168.1.100:90/YiXing/image/user/"+imageGet.getNumb()+".jpg";
        Users users = new Users();
        users.setNumb(imageGet.getNumb());
        users.setImage(imagepath);

        usersService.updateImage(users);

        Map map = new HashMap();
        Users users1 = usersService.findByNumb(imageGet.getNumb());
        if (users1.getImage().equals(imagepath)){
            map.put("redata",users1);
            map.put("status","ok");
        }else {
            map.put("redata",null);
            map.put("status","false");
        }
        return map;
    }


}
