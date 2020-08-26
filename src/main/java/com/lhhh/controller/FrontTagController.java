package com.lhhh.controller;

import com.github.pagehelper.PageInfo;
import com.lhhh.bean.Blog;
import com.lhhh.service.BlogService;
import com.lhhh.service.TagService;
import com.lhhh.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * @author: lhhh
 * @date: Created in 2020/8/1
 * @description:
 * @version:1.0
 */
@Controller
@RequestMapping("/tag")
public class FrontTagController {
    @Autowired
    private TypeService typeService;
    @Autowired
    private TagService tagService;
    @Autowired
    private BlogService blogService;


    @GetMapping({"/","/{pageNum}/{tagId}"})
    public ModelAndView type(@PathVariable(value = "pageNum",required = false) Integer pageNum,
                             @PathVariable(value = "tagId",required = false) Long tagId){
        ModelAndView mv = new ModelAndView();
        List<Map> tagMaps = tagService.findAllDifTagsAndCount();
        if (pageNum==null){
            pageNum=1;
        }
        if (tagId==null){
            tagId = Long.valueOf(tagMaps.get(0).get("tagId").toString());
        }
        PageInfo<Blog> pageInfo = blogService.findByTagId(pageNum, tagId);
        mv.addObject("tagMaps", tagMaps);
        mv.addObject("tI", tagId);
        mv.addObject("pageInfo", pageInfo);
        mv.setViewName("tags");
        return mv;
    }


}
