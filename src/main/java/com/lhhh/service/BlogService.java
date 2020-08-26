package com.lhhh.service;

import com.github.pagehelper.PageInfo;
import com.lhhh.bean.Blog;
import com.lhhh.bean.Type;

import java.util.List;
import java.util.Map;

/**
 * @author: lhhh
 * @date: Created in 2020/7/30
 * @description:
 * @version:1.0
 */
public interface BlogService {

    Blog findById(Long id);

    PageInfo<Blog> findByPage(Integer pageNum, Map map);

    void addBlog(Map map);

    void updateBlog(Map map);

    void deleteBlog(Long id);

    List<Blog> findAll();

    List<Type> findTypeName();

    void addBlogAndTags(long blogId,long tagsId);

    void deleteBlogAndTagsByBlogId(Long id);

    List<Blog> findHot();

    PageInfo<Blog> findByTypeId(Integer pageNum,Long typeId);

    PageInfo<Blog> findByTagId(Integer pageNum,Long tagId);

    List<String> findDistinctDate();

    List<Blog> findByDate(String date);

    int findAllCount();

    void updateBlogViews(Long id);


}
