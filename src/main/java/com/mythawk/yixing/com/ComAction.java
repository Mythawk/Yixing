package com.mythawk.yixing.com;

import com.mythawk.yixing.scout.Scout;
import com.mythawk.yixing.share.Share;
import com.mythawk.yixing.share.ShareIdBody;
import com.mythawk.yixing.sneaker.Sneaker;
import com.mythawk.yixing.user.IUsersService;
import com.mythawk.yixing.user.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/com")
public class ComAction {
   @Autowired
   private IComService comService;
   @Autowired
   private IUsersService usersService;
   /**
    *  根据用户上传数据，返回JSON,用于展示留言内容
    */
   @RequestMapping(value = "/getComById",method = RequestMethod.POST)
   @ResponseBody
   public Map requestCom(@RequestBody Map<String,String>ShareId){

      System.out.println(ShareId);
      int shareId = Integer.parseInt(ShareId.get("id"));
      List<ComData> comList = comService.findById(shareId);

      Map map = new HashMap();
      if (comList.size() != 0 ){
         map.put("status","ok");
         map.put("comData",comList);
      }else {
         map.put("status","false");
         map.put("comData",null);
      }
      return map;
   }

   /**
    *  根据ID查询，返回JSON
    */
   @RequestMapping(value = "/insertCom",method = RequestMethod.POST)
   @ResponseBody
   public Map requestForInsert(@RequestBody ComBody comBody){
      System.out.println(comBody);
      int shareId = comBody.getShare_id();
      ComData com = new ComData();
      //待处理：对com进行赋值
      Users users = usersService.findByNumb(comBody.getCom_num());
      com.setShare_id(comBody.getShare_id());
      com.setCom_num(users.getNumb());
      com.setCom_name(users.getName());
      com.setCom_floor(comBody.getCom_floor());
      com.setCom_img(users.getImage());
      com.setCom_text(comBody.getCom_text());
      Date date = new Date();
      SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
      String dataTime = dateFormat.format(date);
      com.setCom_time(dataTime);
      int result =  comService.insertCom(com);
      Map map = new HashMap();
      List<ComData> comList = comService.findById(shareId);
      if (result != 0){
         map.put("status","ok");
         map.put("comData",comList);
      }else {
         map.put("status","false");
         map.put("comData",comList);
      }
      return map;
   }


}
