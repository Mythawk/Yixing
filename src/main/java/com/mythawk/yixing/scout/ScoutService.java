package com.mythawk.yixing.scout;

import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ScoutService  implements IScoutService {

    @Resource
    private IScoutDao scoutDao;

    @Override
    public List<Scout> findByName(String name) {
        return scoutDao.findByName(name);
    }

    @Override
    public List<Rank> getRank() {
        return scoutDao.getRank();
    }

    @Override
    public String findImg(String name) {
        return scoutDao.findImg(name);
    }

    @Override
    public int insertScout(Scout scout) {
        return scoutDao.insertScout(scout);
    }
}
