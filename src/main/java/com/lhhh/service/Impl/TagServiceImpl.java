package com.lhhh.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lhhh.bean.Tag;
import com.lhhh.dao.TagDao;
import com.lhhh.exception.NotFoundException;
import com.lhhh.service.TagService;
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
public class TagServiceImpl implements TagService {
    @Autowired
    private TagDao tagDao;

    @Override
    public void addTag(Tag tag) {
        tagDao.addTag(tag);
    }

    @Override
    public Tag findById(Long id) {
        return tagDao.findById(id);
    }

    @Override
    public Tag findByName(String name) {
        Tag tag = tagDao.findByName(name);
        return tag;
    }

    @Override
    public void updateTag(Tag tag) {
        Tag t = findById(tag.getId());
        if (t==null){
            throw new NotFoundException("无法通过该id找到标签");
        }
        tagDao.updateTag(tag);
    }

    @Override
    public void deleteTagById(Long id) {
        tagDao.deleteTagById(id);
    }

    @Override
    public PageInfo<Tag> findByPage(Integer pageNum) {
        PageHelper.startPage(pageNum,5);
        List<Tag> tags = tagDao.findByPage();
        PageInfo<Tag> pageInfo = new PageInfo(tags);
        System.out.println("总记录数："+pageInfo.getTotal());
        System.out.println("总页数："+pageInfo.getPages());
        return pageInfo;
    }

    @Override
    public List<Tag> findAll() {
        return tagDao.findAll();
    }

    @Override
    public List<Map> findDifTagsAndCount() {
        return tagDao.findDifTagAndCount();
    }

    @Override
    public List<Map> findAllDifTagsAndCount() {
        return tagDao.findAllDifTagAndCount();
    }
}
