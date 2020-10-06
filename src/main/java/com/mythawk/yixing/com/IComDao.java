package com.mythawk.yixing.com;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface IComDao {

    List<ComData> findById(@Param("share_id")int id);
    int insertCom(ComData com);

}
