package com.lhhh.controller.admin;

import com.github.pagehelper.PageInfo;
import com.lhhh.bean.Tag;
import com.lhhh.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @author: lhhh
 * @date: Created in 2020/7/30
 * @description:
 * @version:1.0
 */
@Controller
@RequestMapping("/admin")
public class TagController {
    @Autowired
    private TagService tagService;

    @GetMapping("/tags/{pageNum}")
    public ModelAndView types(@PathVariable("pageNum") Integer pageNum){
        if (pageNum == null){
            pageNum=1;
        }
        PageInfo<Tag> pageInfo = tagService.findByPage(pageNum);
        ModelAndView mv = new ModelAndView();
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("admin/tags");
        return mv;
    }

    @GetMapping("/tags/update/{id}")
    public ModelAndView update(@PathVariable("id")Integer id){
        ModelAndView mv = new ModelAndView();
        Tag t = tagService.findById(id.longValue());
        mv.addObject("tag",t);
        mv.setViewName("admin/tag-update");
        return mv;
    }
    @PostMapping("/tags/update/{id}/submit")
    public String updateSubmit(@PathVariable("id")Integer id,String name,RedirectAttributes attributes){
        Tag tag = new Tag();
        Tag t = tagService.findByName(name);
        if (t!=null){
            attributes.addFlashAttribute("message","该标签已存在，请重新输入");
            return "redirect:/admin/tags/update/"+id;
        }
        tag.setId(id.longValue());
        tag.setName(name);
        tagService.updateTag(tag);
        attributes.addFlashAttribute("message","恭喜，更新成功");
        return "redirect:/admin/tags/1";
    }

    @GetMapping("/tags/delete/{id}/{pageNum}")
    public String delete(@PathVariable("id")Integer id,@PathVariable("pageNum")Integer pageNum,RedirectAttributes attributes){
        tagService.deleteTagById(id.longValue());
        attributes.addFlashAttribute("message","恭喜，删除成功");
        return "redirect:/admin/tags/"+pageNum;
    }

    @GetMapping("/tags/add")
    public String add(){
        return "admin/tag-add";
    }
    @PostMapping("/tags/add/submit")
    public String addsubmit(String name, RedirectAttributes attributes){
        Tag tag = new Tag();
        tag.setName(name);
        Tag t = tagService.findByName(name);
        if (t!=null){
            attributes.addFlashAttribute("message","该标签已存在，请重新输入");
            return "redirect:/admin/tags/add";
        }
        attributes.addFlashAttribute("message","恭喜，操作成功");
        tagService.addTag(tag);
        return "redirect:/admin/tags/1";

    }

}
