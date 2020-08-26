package com.lhhh.controller;

import com.lhhh.bean.Blog;
import com.lhhh.service.BlogService;
import com.lhhh.service.TagService;
import com.lhhh.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;

/**
 * @author: lhhh
 * @date: Created in 2020/8/1
 * @description:
 * @version:1.0
 */
@Controller
public class FrontArchvesController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private TagService tagService;
    @Autowired
    private TypeService typeService;
    @GetMapping("/archives")
    public ModelAndView archieves(){
        HashMap<Object, List<Blog>> map = new HashMap<>();
        ModelAndView mv = new ModelAndView();
        List<String> dates = blogService.findDistinctDate();
        int count = blogService.findAllCount();
        for (String date : dates) {
            List<Blog> blogs = blogService.findByDate(date);
            map.put(date,blogs);
        }
        mv.addObject("map",map);
        mv.addObject("count",count);
        mv.setViewName("archives");
        return mv;
    }
}
