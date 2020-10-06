package com.mythawk.yixing.share;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IShareService {
    List<Share> findAll();
    Share findById(int id);
    int insertShare(Share share);
    List<Share> findByName(String name);
    void  deleteById(Integer id);
}
