package edu.hubu.learn.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.hubu.learn.entity.Music;

public interface MusicDao extends JpaRepository<Music, Long> {

}