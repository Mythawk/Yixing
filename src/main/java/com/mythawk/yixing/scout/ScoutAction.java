package com.mythawk.yixing.scout;

import com.mythawk.yixing.share.IShareService;
import com.mythawk.yixing.share.Share;
import com.mythawk.yixing.sneaker.ISneakerService;
import com.mythawk.yixing.sneaker.Sneaker;
import com.mythawk.yixing.sneaker.SneakerGet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/scout")
public class ScoutAction {

    @Autowired
    private IScoutService scoutService;

    @Autowired
    private ISneakerService sneakerService;

    @Autowired
    private IShareService shareService;

    /**
     *  根据用户上传数据，返回JSON
     */
    @RequestMapping(value = "/getScoutByName",method = RequestMethod.POST)
    @ResponseBody
    public Map requestScout(@RequestBody Map<String,String>scoutName){

        System.out.println(scoutName);
        String shoesName = scoutName.get("name");
        List<Scout> scoutList = new ArrayList<>();
        scoutList = scoutService.findByName(shoesName);

        Scout scout = new Scout();
        if (scoutList.size()!=0) {
            scout = getAvgScout(scoutList);
        }
        scout.setShoes_name(shoesName);
        Sneaker sneaker = sneakerService.findSneaker(shoesName);

        List<Share> shareList = shareService.findByName(shoesName);

        Map map = new HashMap();
        map.put("scoutData",scout);
        map.put("sneakerData",sneaker);
        map.put("shareData",shareList);
        if (scout != null ){
            map.put("status","ok");
        }else {
            map.put("status","false");
        }
        return map;
    }
    /**
     *  根据用户上传数据，返回JSON
     */
    @RequestMapping(value = "/getScoutRank",method = RequestMethod.GET)
    @ResponseBody
    public Map requestRank(){

        List<Rank> rankList = new ArrayList<>();
        rankList = scoutService.getRank();

        for (Rank rank : rankList){
            String img = null ;
            if (rank.getRank_name() != null){
                img = scoutService.findImg(rank.getRank_name());
            }
            if (img != null){
                rank.setRank_img(img);
            }
            float scout =  (float)(Math.round((rank.getRank_scout()*10)))/10;
            rank.setRank_scout(scout);
        }

        Map map = new HashMap();
        map.put("rankData",rankList);
        if (rankList != null ){
            map.put("status","ok");
        }else {
            map.put("status","false");
        }
        return map;
    }



    private Scout getAvgScout(List<Scout>scoutList){

        int shock =0,parcel=0,support=0,grip=0,durable=0;
        float scout= 0;


        for (Scout scout1 : scoutList) {
            shock +=  scout1.getShock();
            parcel += scout1.getParcel();
            support += scout1.getSupport();
            grip += scout1.getGrip();
            durable += scout1.getDurable();
            scout += scout1.getScout();
        }
        Scout scout2 = new Scout();
        scout2.setShock(shock/scoutList.size());
        scout2.setParcel(parcel/scoutList.size());
        scout2.setSupport(support/scoutList.size());
        scout2.setGrip(grip/scoutList.size());
        scout2.setDurable(durable/scoutList.size());
        scout = (float)(Math.round((scout/scoutList.size()*10)))/10;
        scout2.setScout(scout);
        System.out.println("Test:"+scout2);
        return scout2;
    }



}
