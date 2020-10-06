package com.mythawk.yixing.com;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IComService {

    List<ComData> findById(int id);
    int insertCom(ComData com);

}
