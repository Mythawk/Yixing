package com.mythawk.yixing.com;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ComService implements IComService {

    @Resource
    private IComDao comDao;

    @Override
    public List<ComData> findById(int id) {
        return comDao.findById(id);
    }

    @Override
    public int insertCom(ComData com) {
        return comDao.insertCom(com);
    }
}
