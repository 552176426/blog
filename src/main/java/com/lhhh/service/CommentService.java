package com.lhhh.service;

import com.lhhh.bean.Comment;

import java.util.List;

public interface CommentService {

    void add(Comment comment, int id);

    List<Comment> findByBlogId(int id);
}
