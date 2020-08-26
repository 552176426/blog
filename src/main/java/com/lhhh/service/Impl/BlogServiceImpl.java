package com.lhhh.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lhhh.bean.Blog;
import com.lhhh.bean.Type;
import com.lhhh.dao.BlogDao;
import com.lhhh.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author: lhhh
 * @date: Created in 2020/7/30
 * @description:
 * @version:1.0
 */
@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    private BlogDao blogDao;

    @Override
    public Blog findById(Long id) {
        return blogDao.findById(id);
    }

    @Override
    public PageInfo<Blog> findByPage(Integer pageNum, Map map) {
        PageHelper.startPage(pageNum,8);
        List<Blog> blogs = blogDao.findByPage(map);
        PageInfo<Blog> pageInfo = new PageInfo<>(blogs);
        return pageInfo;
    }

    @Override
    public void addBlog(Map map) {
        blogDao.addBlog(map);
    }

    @Override
    public void updateBlog(Map map) {
        blogDao.updateBlog(map);
    }

    @Override
    public void deleteBlog(Long id) {
        blogDao.deleteBlog(id);
    }

    @Override
    public List<Blog> findAll() {
        return blogDao.findAll();
    }

    @Override
    public List<Type> findTypeName() {
        return blogDao.findTypeName();
    }

    @Override
    public void addBlogAndTags(long blogId, long tagsId) {
        blogDao.addBlogAndTags(blogId, tagsId);
    }

    @Override
    public void deleteBlogAndTagsByBlogId(Long id) {
        blogDao.deleteBlogAndTagsByBlogId(id);
    }

    @Override
    public List<Blog> findHot() {
        return blogDao.findHot();
    }

    @Override
    public PageInfo<Blog> findByTypeId(Integer pageNum,Long typeId) {
        PageHelper.startPage(pageNum,5);
        List<Blog> blogs = blogDao.findByTypeId(typeId);
        PageInfo<Blog> pageInfo = new PageInfo<>(blogs);
        return pageInfo;
    }

    @Override
    public PageInfo<Blog> findByTagId(Integer pageNum, Long tagId) {
        PageHelper.startPage(pageNum,5);
        List<Blog> blogs = blogDao.findByTagId(tagId);
        PageInfo<Blog> pageInfo = new PageInfo<>(blogs);
        return pageInfo;
    }

    @Override
    public List<String> findDistinctDate() {
        return blogDao.findDistinctDate();
    }

    @Override
    public List<Blog> findByDate(String date) {
        return blogDao.findByDate(date);
    }

    @Override
    public int findAllCount() {
        return blogDao.findAllCount();
    }
    @Override
    public void updateBlogViews(Long id){
        blogDao.updateBlogViews(id);
    }

}
