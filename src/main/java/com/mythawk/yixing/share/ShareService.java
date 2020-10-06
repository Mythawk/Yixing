package com.mythawk.yixing.share;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ShareService implements IShareService {

    @Resource
    private IShareDao shareDao;

    @Override
    public List<Share> findAll() {
        return shareDao.findAll();
    }

    @Override
    public Share findById(int id) {
        return shareDao.findById(id);
    }

    @Override
    public int insertShare(Share share) {
        return shareDao.insertShare(share);
    }

    @Override
    public List<Share> findByName(String name) {
        return shareDao.findByName(name);
    }

    @Override
    public void deleteById(Integer id) {
        shareDao.deleteById(id);
    }
}
