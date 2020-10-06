package com.mythawk.yixing.sneaker;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ISneakerDao {

    List<Sneaker> findAll();
    List<Sneaker> findByName(@Param("name")String name);
    List<Sneaker> findByBand(@Param("band")String band);
    List<Sneaker> findByCategory(@Param("category")int category);
    Sneaker findSneaker(@Param("name")String name);
    int saveSneaker(Sneaker sneaker);
    void deleteById(@Param("id") Integer id);
    Sneaker findById(@Param("id") Integer id);
    int update(Sneaker sneaker);
}
