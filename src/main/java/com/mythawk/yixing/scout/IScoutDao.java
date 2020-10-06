package com.mythawk.yixing.scout;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface IScoutDao {

    List<Scout> findByName(@Param("shoes_name")String name);
    String findImg(@Param("shoes_name")String name);
    List<Rank> getRank();
    int insertScout(Scout scout);
}
