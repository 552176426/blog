package com.lhhh.controller.admin;

import com.github.pagehelper.PageInfo;
import com.lhhh.bean.Type;
import com.lhhh.service.TypeService;
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
public class TypeController {
    @Autowired
    private TypeService typeService;

    @GetMapping("/types/{pageNum}")
    public ModelAndView types(@PathVariable("pageNum") Integer pageNum){
        if (pageNum == null){
            pageNum=1;
        }
        PageInfo<Type> pageInfo = typeService.findByPage(pageNum);
        ModelAndView mv = new ModelAndView();
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("admin/types");
        return mv;
    }

    @GetMapping("/types/update/{id}")
    public ModelAndView update(@PathVariable("id")Integer id){
        ModelAndView mv = new ModelAndView();
        Type t = typeService.findById(id.longValue());
        mv.addObject("type",t);
        mv.setViewName("admin/type-update");
        return mv;
    }
    @PostMapping("/types/update/{id}/submit")
    public String updateSubmit(@PathVariable("id")Integer id,String name,RedirectAttributes attributes){
        Type type = new Type();
        Type t = typeService.findByName(name);
        if (t!=null){
            attributes.addFlashAttribute("message","该分类已存在，请重新输入");
            return "redirect:/admin/types/update/"+id;
        }
        type.setId(id.longValue());
        type.setName(name);
        typeService.updateType(type);
        attributes.addFlashAttribute("message","恭喜，更新成功");
        return "redirect:/admin/types/1";
    }

    @GetMapping("/types/delete/{id}/{pageNum}")
    public String delete(@PathVariable("id")Integer id,@PathVariable("pageNum")Integer pageNum,RedirectAttributes attributes){
        typeService.deleteTypeById(id.longValue());
        attributes.addFlashAttribute("message","恭喜，删除成功");
        return "redirect:/admin/types/"+pageNum;
    }

    @GetMapping("/types/add")
    public String add(){
        return "admin/type-add";
    }
    @PostMapping("/types/add/submit")
    public String addsubmit(String name, RedirectAttributes attributes){
        Type type = new Type();
        type.setName(name);
        Type t = typeService.findByName(name);
        if (t!=null){
            attributes.addFlashAttribute("message","该分类已存在，请重新输入");
            return "redirect:/admin/types/add";
        }
        attributes.addFlashAttribute("message","恭喜，操作成功");
        typeService.addType(type);
        return "redirect:/admin/types/1";

    }

}
