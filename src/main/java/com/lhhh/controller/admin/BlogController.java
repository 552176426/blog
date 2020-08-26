package com.lhhh.controller.admin;

import com.github.pagehelper.PageInfo;
import com.lhhh.bean.Blog;
import com.lhhh.bean.Tag;
import com.lhhh.bean.Type;
import com.lhhh.bean.User;
import com.lhhh.service.BlogService;
import com.lhhh.service.TagService;
import com.lhhh.service.TypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author: lhhh
 * @date: Created in 2020/7/30
 * @description:
 * @version:1.0
 */
@Controller
@RequestMapping("/admin")
public class BlogController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    @GetMapping("/blogs/{pageNum}")
    public ModelAndView blogs(@PathVariable("pageNum")Integer pageNum,@RequestParam Map map){
        String recommend = (String)map.get("recommend");
        if (recommend!=null && recommend.equals("on")){
            map.put("recommend",1);
        }
        List<Type> types = blogService.findTypeName();
        PageInfo<Blog> pageInfo = blogService.findByPage(pageNum, map);
        ModelAndView mv = new ModelAndView();
        mv.addObject("pageInfo",pageInfo);
        mv.addObject("types",types);
        mv.setViewName("admin/blogs");
        return mv;
    }

    @GetMapping("/blogs/all")
    public ModelAndView all(){
        List<Blog> blogs = blogService.findAll();
        System.out.println(blogs);
        ModelAndView mv = new ModelAndView();
        mv.addObject("blogs",blogs);
        mv.setViewName("admin/blogs");
        return mv;
    }

    @GetMapping("/blogs/delete/{id}")
    public String delete(@PathVariable("id")Integer id, RedirectAttributes attributes){
        blogService.deleteBlog(id.longValue());
        attributes.addFlashAttribute("message","恭喜，删除成功");
        return "redirect:/admin/blogs/1";
    }

    @GetMapping("/blogs/add")
    public ModelAndView add(){
        List<Type> types = typeService.findAll();
        List<Tag> tags = tagService.findAll();
        ModelAndView mv = new ModelAndView();
        mv.addObject("types",types);
        mv.addObject("tags",tags);
        mv.setViewName("/admin/blogs-add");
        return mv;
    }

    @PostMapping("/blogs/add/submit")
    public String addSubmit(@RequestParam Map map, RedirectAttributes attributes, HttpSession session){
        User user = (User)session.getAttribute("user");
        map.put("user_id",user.getId());
        String recommend = (String)map.get("recommend");
        String shareStatement = (String)map.get("shareStatement");
        String appreciation = (String)map.get("appreciation");
        String commentable = (String)map.get("commentable");
        String published = (String)map.get("published");
        if (published!=null && "true".equals(recommend)){
            attributes.addFlashAttribute("message","恭喜，发布成功");
            map.put("published",true);
        } else {
            attributes.addFlashAttribute("message","恭喜，保存成功");
            map.put("published",false);
        }
        if (recommend!=null && "on".equals(recommend)){
            map.put("recommend",true);
        } else {
            map.put("recommend",false);
        }
        if (shareStatement!=null && "on".equals(shareStatement)){
            map.put("shareStatement",true);
        } else {
            map.put("shareStatement",false);
        }
        if (appreciation!=null && "on".equals(appreciation)){
            map.put("appreciation",true);
        } else {
            map.put("appreciation",false);
        }
        if (commentable!=null && "on".equals(commentable)){
            map.put("commentable",true);
        } else {
            map.put("commentable",false);
        }
        Date date = new Date();
        map.put("create_time",date);
        map.put("update_time",date);
        map.put("views",0);
        String tagId =map.get("tagId").toString();
        String[] tagIds = tagId.split(",");
        blogService.addBlog(map);
        for (String tag_id : tagIds) {
            blogService.addBlogAndTags(Long.valueOf(map.get("id").toString()),Long.valueOf(tag_id));
        }

        return "redirect:/admin/blogs/1";
    }

    @GetMapping("/blogs/update/{id}")
    public ModelAndView update(@PathVariable("id")long id){
        System.out.println(id);
        Blog blog = blogService.findById(id);
        logger.info("------------blog : {}",blog);
        List<Type> types = typeService.findAll();
        List<Tag> tags = tagService.findAll();
        StringBuilder tagNames = new StringBuilder();
        int count=0;
        for (Tag tag : blog.getTags()) {
            String name = tag.getName();
            tagNames.append(name+",");
            count++;
        }
        tagNames = tagNames.deleteCharAt(tagNames.length()-1);
        ModelAndView mv = new ModelAndView();
        mv.addObject("types",types);
        mv.addObject("tags",tags);
        mv.addObject("blog",blog);
        mv.addObject("tagNames",tagNames);
        mv.addObject("count",count);
        mv.setViewName("/admin/blogs-update");
        return mv;
    }

    @PostMapping("/blogs/update/submit")
    public String updateSubmit(@RequestParam Map map, RedirectAttributes attributes, HttpSession session){
        User user = (User)session.getAttribute("user");
        map.put("user_id",user.getId());
        String recommend = (String)map.get("recommend");
        String shareStatement = (String)map.get("shareStatement");
        String appreciation = (String)map.get("appreciation");
        String commentable = (String)map.get("commentable");
        String published = (String)map.get("published");
        if (published!=null && "true".equals(published)){
            attributes.addFlashAttribute("message","恭喜，发布成功");
            map.put("published",true);
        } else {
            attributes.addFlashAttribute("message","恭喜，保存成功");
            map.put("published",false);
        }
        if (recommend!=null && "on".equals(recommend)){
            map.put("recommend",true);
        } else {
            map.put("recommend",false);
        }
        if (shareStatement!=null && "on".equals(shareStatement)){
            map.put("shareStatement",true);
        } else {
            map.put("shareStatement",false);
        }
        if (appreciation!=null && "on".equals(appreciation)){
            map.put("appreciation",true);
        } else {
            map.put("appreciation",false);
        }
        if (commentable!=null && "on".equals(commentable)){
            map.put("commentable",true);
        } else {
            map.put("commentable",false);
        }
        Date date = new Date();
        map.put("update_time",date);
        String tagId =map.get("tagId").toString();
        String[] tagIds = tagId.split(",");
        int count =Integer.parseInt(map.get("count").toString());
        Long blogId = Long.valueOf(map.get("blogId").toString());
        map.put("blogId",blogId);
        blogService.updateBlog(map);
        //更新t_blog_tags表
        //先删除
        blogService.deleteBlogAndTagsByBlogId(blogId);
        for (int i = count; i < tagIds.length; i++) {
            blogService.addBlogAndTags(blogId,Long.valueOf(tagIds[i]));
        }
        return "redirect:/admin/blogs/1";
    }

}
