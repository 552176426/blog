package com.lhhh.controller;

import com.github.pagehelper.PageInfo;
import com.lhhh.bean.Blog;
import com.lhhh.service.BlogService;
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
@RequestMapping("/type")
public class FrontTypeController {
    @Autowired
    private TypeService typeService;
    @Autowired
    private BlogService blogService;


    @GetMapping({"/","/{pageNum}/{typeId}"})
    public ModelAndView type(@PathVariable(value = "pageNum",required = false) Integer pageNum,
                             @PathVariable(value = "typeId",required = false) Long typeId){
        ModelAndView mv = new ModelAndView();
        List<Map> typeMaps = typeService.findAllDifTypeAndCount();
        if (pageNum==null){
            pageNum=1;
        }
        if (typeId==null){
            typeId = Long.valueOf(typeMaps.get(0).get("typeId").toString());
        }
        PageInfo<Blog> pageInfo = blogService.findByTypeId(pageNum, typeId);
        mv.addObject("typeMaps", typeMaps);
        mv.addObject("tI", typeId);
        mv.addObject("pageInfo", pageInfo);
        mv.setViewName("types");
        return mv;
    }


}
