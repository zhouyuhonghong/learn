package edu.hubu.learn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.hubu.learn.dao.MusicDao;
import edu.hubu.learn.entity.Music;

@Service
public class MusicService {

    @Autowired
    private MusicDao musicDao;

    public Music getMusic(Long id) {
        return musicDao.findById(id).get();
    }
}