package com.mythawk.yixing.scout;

import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface IScoutService {

   List<Scout> findByName(String name);
   String findImg(String name);
   List<Rank> getRank();
   int insertScout(Scout scout);
}
