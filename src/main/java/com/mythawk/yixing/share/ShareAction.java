package com.mythawk.yixing.share;

import com.mythawk.yixing.scout.IScoutService;
import com.mythawk.yixing.scout.Scout;
import com.mythawk.yixing.sneaker.Sneaker;
import com.mythawk.yixing.user.IUsersService;
import com.mythawk.yixing.user.Users;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/share")
public class ShareAction {

    @Autowired
    private IShareService shareService;

    @Autowired
    private IUsersService usersService;

    @Autowired
    private IScoutService scoutService;


    @RequestMapping("/main")
    public ModelAndView sneakerMain(){
        System.out.println("**********");
        ModelAndView mv = new ModelAndView();
        mv.addObject("share",shareService.findAll());
        mv.setViewName("share/share-main");
        return mv ;
    }


    @RequestMapping(value = "/delete/{id}",method = RequestMethod.GET)
    public String delete(@PathVariable Integer id) {
        System.out.println(id);
        shareService.deleteById(id);
        return "redirect:/share/main";
    }

    /**
     *  查询所有，返回JSON
     */
    @RequestMapping(value = "/redata",method = RequestMethod.GET)
    @ResponseBody
    public Map requestShare(){
        Map map = new HashMap();
        List<Share> shareList = shareService.findAll();
        if (shareList != null){
            map.put("redata",shareList);
            map.put("status","ok");
        }else {
            map.put("redata",null);
            map.put("status","false");
        }
        return map;
    }

    /**
     *  根据ID查询，返回JSON
     */
    @RequestMapping(value = "/redataById",method = RequestMethod.POST)
    @ResponseBody
    public Map requestShareById(@RequestBody ShareIdBody idBody){
        Map map = new HashMap();
        Share shareList = shareService.findById(idBody.id);
        if (shareList != null){
            map.put("redata",shareList);
            map.put("status","ok");
        }else {
            map.put("redata",null);
            map.put("status","false");
        }
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/addShare" , method = RequestMethod.POST)
    public Map requestShare(@RequestBody  ShareBody shareBody){
        FileOutputStream imageOutFile = null;
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");
        String dataTime = dateFormat.format(date);
        SimpleDateFormat dateFormat2 = new SimpleDateFormat("YYYYMMddHHmmss");
        String dataTime2 = dateFormat2.format(date);
        try {
            byte[] imageByteArray = Base64.decodeBase64(shareBody.getImage());
            imageOutFile = new FileOutputStream(new File("D:\\idea\\Yixing\\src\\main\\webapp\\image\\share\\"+shareBody.getName()+dataTime2+".jpg"),false);
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
        String imagePath = "http://192.168.1.100:90/YiXing/image/share/"+shareBody.getName()+dataTime2+".jpg";

        Users users = usersService.findByNumb(shareBody.getNumb());
        Share share = new Share();
        share.setUser_phone(users.getNumb());
        share.setUser_name(users.getName());
        share.setUser_image(users.getImage());
        share.setDate(dataTime);
        share.setShare_topic(shareBody.getTopic());
        share.setShare_txt(shareBody.getShareText());
        share.setImg(imagePath);
        share.setShock(shareBody.getShock());
        share.setSupport(shareBody.getSupport());
        share.setParcel(shareBody.getParcel());
        share.setGrip(shareBody.getGrip());
        share.setDurable(shareBody.getDurable());
        share.setShoes_name(shareBody.getSneakerName());
        int sum = (share.getDurable()+share.getGrip()+share.getParcel()+share.getShock()+share.getSupport())/5;
        share.setScout((float)(Math.round((sum*10)))/10);

        Scout scout = new Scout();
        scout.setShoes_name(shareBody.getSneakerName());
        scout.setShock(shareBody.getShock());
        scout.setSupport(shareBody.getSupport());
        scout.setDurable(shareBody.getDurable());
        scout.setParcel(shareBody.getParcel());
        scout.setGrip(shareBody.getGrip());
        scout.setScout((float)(Math.round((sum*10)))/10);

        int result =  shareService.insertShare(share);
        int result2 = scoutService.insertScout(scout);
        System.out.println(result + result2);
        Map map = new HashMap();
        if (result!=0 && result2!=0){
            map.put("status","ok");
        }else {
            map.put("status",false);
        }
        return map;
    }


}
