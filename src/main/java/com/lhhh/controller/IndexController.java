package com.lhhh.controller;


import com.github.pagehelper.PageInfo;
import com.lhhh.bean.Blog;
import com.lhhh.service.BlogService;
import com.lhhh.service.TagService;
import com.lhhh.service.TypeService;
import com.lhhh.utils.MarkDownUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * @author: lhhh
 * @date: Created in 2020/7/13
 * @description:
 * @version:1.0
 */
@Controller
public class IndexController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private TagService tagService;
    @Autowired
    private TypeService typeService;

    @GetMapping({"/{pageNum}","/"})
    public ModelAndView index(@PathVariable(value = "pageNum",required = false)Integer pageNum, Map map){
        if (pageNum==null){
            pageNum=1;
        }
        ModelAndView mv = new ModelAndView();
        PageInfo<Blog> pageInfo = blogService.findByPage(pageNum, map);
        List<Map> tagMaps = tagService.findDifTagsAndCount();
        List<Map> typeMaps = typeService.findDifTypeAndCount();
        List<Blog> hotBlogs = blogService.findHot();
        mv.addObject("tagMaps",tagMaps);
        mv.addObject("hotBlogs",hotBlogs);
        mv.addObject("typeMaps",typeMaps);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("index");
        return mv;
    }

    @GetMapping("/blog/{blogId}")
    public ModelAndView blog(@PathVariable("blogId")Long id){
        ModelAndView mv = new ModelAndView();
        blogService.updateBlogViews(id);
        Blog blog = blogService.findById(id);
        String content = blog.getContent();
        System.out.println(content);
        String markdownContent = MarkDownUtils.markdownToHtmlExtensions(content);
        blog.setContent(markdownContent);
        mv.addObject("blog",blog);
        mv.setViewName("blog");
        return mv;
    }
}
