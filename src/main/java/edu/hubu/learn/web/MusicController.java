package edu.hubu.learn.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import edu.hubu.learn.entity.Music;
import edu.hubu.learn.service.MusicService;


@Controller
@RequestMapping("/music")
public class MusicController {

    @Autowired
    private MusicService musicService;


    @RequestMapping("/{id}")
    public ModelAndView music(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView();
        Music music = musicService.getMusic(id);
        mav.addObject("music", music);
        mav.setViewName("music");
        return mav;
    }

    @RequestMapping("/list")
    public ModelAndView musics() {
        ModelAndView mav = new ModelAndView();
        List<Music> musics = musicService.getMusics();
        mav.addObject("musics", musics);
        mav.setViewName("musics");
        return mav;
    }
    @RequestMapping("/add")
    public ModelAndView addMusic() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("music_add");
        return mav;
    }

    @RequestMapping("/do_add")
    public ModelAndView doAddMusicr(Music music) {
        musicService.addMusic(music);
        ModelAndView mav = new ModelAndView("redirect:/music/list");
        return mav;
    }
}