package com.lhhh.dao;

import com.lhhh.bean.Comment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentDao {
    @Insert("insert into t_comment(avatar,content,create_time,email,nickname,blog_id) values(#{comment.avatar}," +
            "#{comment.content}," +
            "#{comment.createTime}," +
            "#{comment.email}," +
            "#{comment.nickname}," +
            "#{id})")
    public void add(@Param("comment") Comment comment,@Param("id") int id);

    @Select("select * from t_comment where blog_id=#{id}")
    List<Comment> findByBlogId(int id);
}
