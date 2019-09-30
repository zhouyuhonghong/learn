package edu.hubu.learn.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import edu.hubu.learn.entity.User;
import edu.hubu.learn.service.UserService;


@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    @RequestMapping("/user")
    public ModelAndView user(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView();
        User user = userService.getUser(id);
        mav.addObject("user", user);
        mav.setViewName("user");
        return mav;
    }

    @RequestMapping("/list")
    public ModelAndView users() {
        ModelAndView mav = new ModelAndView();
        List<User> users = userService.getUsers();
        mav.addObject("users", users);
        mav.setViewName("users");
        return mav;
    }
}