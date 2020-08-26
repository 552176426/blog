package com.lhhh.service.Impl;

import com.lhhh.bean.Comment;
import com.lhhh.dao.CommentDao;
import com.lhhh.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author: lhhh
 * @date: Created in 2020/8/25
 * @description:
 * @version:1.0
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDao commentDao;

    @Override
    public void add(Comment comment, int id) {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = format.format(date);
        comment.setCreateTime(time);
        comment.setAvatar("https://picsum.photos/id/0/100/100");
        commentDao.add(comment,id);
    }

    @Override
    public List<Comment> findByBlogId(int id) {
        return commentDao.findByBlogId(id);
    }
}
