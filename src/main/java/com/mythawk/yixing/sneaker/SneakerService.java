package com.mythawk.yixing.sneaker;


import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SneakerService implements ISneakerService {

    @Resource
    private ISneakerDao sneakerDao ;

    @Override
    public List<Sneaker> findAll() {
        List<Sneaker> sneakerList = sneakerDao.findAll();
        return sneakerList;
    }

    @Override
    public List<Sneaker> findByName(String name) {
        return sneakerDao.findByName(name);
    }

    @Override
    public List<Sneaker> findByBand(String band) {
        return sneakerDao.findByBand(band);
    }

    @Override
    public List<Sneaker> findByCategory(int category) {
        return sneakerDao.findByCategory(category);
    }

    @Override
    public Sneaker findSneaker(String name) {
        return sneakerDao.findSneaker(name);
    }

    @Override
    public int saveSneaker(Sneaker sneaker) {
        System.out.println(sneaker.getSneaker_id());
        if (sneaker.getSneaker_id() == null || sneaker.getSneaker_id() == 0){
            return sneakerDao.saveSneaker(sneaker);
        }else {
            return sneakerDao.update(sneaker);
        }

    }

    @Override
    public void deleteById(Integer id) {
        sneakerDao.deleteById(id);
    }

    @Override
    public Sneaker findById(Integer id) {
        return sneakerDao.findById(id);
    }
}
