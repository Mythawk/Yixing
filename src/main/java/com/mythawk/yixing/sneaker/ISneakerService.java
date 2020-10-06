package com.mythawk.yixing.sneaker;

import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ISneakerService {

    List<Sneaker> findAll();
    List<Sneaker> findByName(String name);
    List<Sneaker> findByBand(String band);
    List<Sneaker> findByCategory(int category);
    Sneaker  findSneaker(String name);
    int saveSneaker(Sneaker sneaker);
    void deleteById(Integer id );
    Sneaker findById(Integer id);
}
