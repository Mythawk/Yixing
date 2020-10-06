package com.mythawk.yixing.share;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface IShareDao {

    List<Share> findAll();
    Share findById(@Param("id")int id);
    int insertShare(Share share);
    List<Share> findByName(@Param("name")String name);
    void deleteById(@Param("id")int id);
}
