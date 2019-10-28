package edu.hubu.learn.web;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import edu.hubu.learn.entity.Music;
import edu.hubu.learn.service.MusicService;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
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

    @RequestMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable Long id) {
        musicService.deleteMusic(id);
        ModelAndView mav = new ModelAndView("redirect:/music/list");
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
        music.setAvatar("");
        musicService.addMusic(music);
        ModelAndView mav = new ModelAndView("redirect:/music/list");
        return mav;
    }

    @RequestMapping("/modify/{id}")
    public ModelAndView modifyMusic(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("music", musicService.getMusic(id));
        mav.setViewName("music_modify");
        return mav;
    }

    @RequestMapping("/do_modify")
    public ModelAndView doModifyMusic(Music music) {
        music.setAvatar("");
        musicService.modifyMusic(music);
        ModelAndView mav = new ModelAndView("redirect:/music/list");
        return mav;
    }

    @RequestMapping("/search")
    public ModelAndView searchMusic() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("music_search");
        return mav;
    }

    @RequestMapping("/do_search")
    public ModelAndView doSearchMusic(HttpServletRequest httpRequest) {
        ModelAndView mav = new ModelAndView();
        String keyword = httpRequest.getParameter("keyword");
        List<Music> musics = musicService.searchMusics(keyword);
        mav.addObject("musics", musics);
        mav.setViewName("musics");
        return mav;
    }

     @RequestMapping("/add_avatar/{id}")
     public ModelAndView addMusicAvatar(@PathVariable Long id) {
         ModelAndView mav = new ModelAndView();
         mav.addObject("music", musicService.getMusic(id));
         mav.setViewName("music_add_avatar");
         return mav;
     }

     @RequestMapping("/do_add_avatar/{id}")
     public ModelAndView doAddMusicAvatar(@RequestParam("avatar") MultipartFile file, @PathVariable Long id) {
         try {
             String fileName = file.getOriginalFilename();
             String filePath = ResourceUtils.getURL("classpath:").getPath() + "../../../resources/main/static/";
             File dest = new File(filePath + fileName);
             log.info(dest.getAbsolutePath());
             file.transferTo(dest);
             Music music = musicService.getMusic(id);
             music.setAvatar(fileName);
             musicService.modifyMusic(music);
         } catch (Exception e) {
             log.error("upload avatar error", e.getMessage());
         }
         return new ModelAndView("redirect:/music/list");
     }
}