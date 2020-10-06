package com.mythawk.yixing.sneaker;


import com.mythawk.yixing.login.ReturnInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/sneaker")
public class SneakerAction {
    @Autowired
    private ISneakerService sneakerService;


    @RequestMapping("/main")
    public ModelAndView sneakerMain(){
        System.out.println("**********");
        ModelAndView mv = new ModelAndView();
        mv.addObject("shoes",sneakerService.findAll());
        mv.setViewName("sneaker/sneaker-main");
        return mv ;
    }

    @RequestMapping("/save")
    @ResponseBody
    public ReturnInfo save(@RequestBody SneakerSave sneakerSave) {
        System.out.println(sneakerSave);
        ReturnInfo returnInfo = new ReturnInfo();
        Sneaker sneaker = new Sneaker();
        sneaker.setSneaker_id(sneakerSave.getId());
        sneaker.setSneaker_name(sneakerSave.getName());
        sneaker.setSneaker_band(sneakerSave.getBand());
        sneaker.setSneaker_price(Float.valueOf(sneakerSave.getPrice()));
        sneaker.setSneaker_img(sneakerSave.getImages());
        sneaker.setSneaker_category(Integer.parseInt(sneakerSave.getCategory()));
        int result =  sneakerService.saveSneaker(sneaker);
        if (result!=0) {
            returnInfo.setErrCode(result);
            returnInfo.setMsg("修改成功！");
            System.out.println("修改成功");
        }else {
            returnInfo.setErrCode(result);
        }
        return returnInfo ;
    }

    @RequestMapping("/sadd")
    public String supadd(){
        return "sneaker/sadd";
    }

    @RequestMapping(value = "/delete/{id}",method = RequestMethod.GET)
    public String delete(@PathVariable Integer id) {
        System.out.println(id);
        sneakerService.deleteById(id);
        return "redirect:/sneaker/main";
    }

    @RequestMapping(value = "/edit/{id}",method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable Integer id) {
        System.out.println("id= " + id);
        Sneaker sneaker = sneakerService.findById(id);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("sneaker/sedit");
        mv.addObject("s",sneaker);
        return mv;
    }


    /**
     *  查询所有鞋类，返回JSON
     */
    @RequestMapping(value = "/redata",method = RequestMethod.POST)
    @ResponseBody
    public Map requestSneaker(@RequestBody SneakerGet sneakerGet){
        String method = sneakerGet.getMethod();
        String content = sneakerGet.getContent();
        System.out.println(method+content);
        List<Sneaker> sneakerList = new ArrayList<>();
        switch (method){
            case "name":
                sneakerList = sneakerService.findByName(content);
                break;
            case "band":
                sneakerList = sneakerService.findByBand(content);
                break;
            case "category":
                sneakerList = sneakerService.findByCategory(Integer.parseInt(content));
                break;
            case "all":
                sneakerList = sneakerService.findAll();
                break;
        }
        Map map = new HashMap();
        map.put("redata",sneakerList);
        System.out.println(sneakerList);
        if (sneakerList != null ){
            map.put("status","ok");
        }else {
            map.put("status","false");
        }
        return map;
    }

    //图片上传测试
    @ResponseBody
    @RequestMapping("upload")
    public Map upload(MultipartFile file, HttpServletRequest request){

        String prefix="";
        String dateStr="";
        //保存上传
        OutputStream out = null;
        InputStream fileInput=null;
        try{
            if(file!=null){
                String originalName = file.getOriginalFilename();
                prefix=originalName.substring(originalName.lastIndexOf(".")+1);
                Date date = new Date();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                dateStr = simpleDateFormat.format(date);
                String filepath = "D:\\idea\\Yixing\\src\\main\\webapp\\image\\sneaker\\"+originalName;


                File files=new File(filepath);
                //打印查看上传路径
                System.out.println(filepath);
                if(!files.getParentFile().exists()){
                    files.getParentFile().mkdirs();
                }
                file.transferTo(files);
                Map<String,Object> map2=new HashMap<>();
                Map<String,Object> map=new HashMap<>();
                map.put("code",0);
                map.put("msg","");
                map.put("data",map2);
                map2.put("src","http://192.168.1.100:90/YiXing/image/sneaker/"+originalName);
                return map;
            }

        }catch (Exception e){
        }finally{
            try {
                if(out!=null){
                    out.close();
                }
                if(fileInput!=null){
                    fileInput.close();
                }
            } catch (IOException e) {
            }
        }
        Map<String,Object> map=new HashMap<>();
        map.put("code",1);
        map.put("msg","");
        return map;

    }

}
