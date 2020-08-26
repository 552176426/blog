package com.lhhh.controller;

import com.lhhh.bean.Comment;
import com.lhhh.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author: lhhh
 * @date: Created in 2020/8/25
 * @description:
 * @version:1.0
 */
@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping("/comment/{id}")
    @ResponseBody
    public String addComment(@RequestBody Comment comment, @PathVariable("id")int id){
        commentService.add(comment,id);
        System.out.println(comment);
        System.out.println(id);
        return "{\"success\":true}";
    }
}
