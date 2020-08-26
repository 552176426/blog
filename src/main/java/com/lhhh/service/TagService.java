package com.lhhh.service;

import com.github.pagehelper.PageInfo;
import com.lhhh.bean.Tag;

import java.util.List;
import java.util.Map;

/**
 * @author: lhhh
 * @date: Created in 2020/7/30
 * @description: 标签接口
 * @version:1.0
 */
public interface TagService {
    /**
     * 新增标签
     * @param tag
     */
    void addTag(Tag tag);

    /**
     * 根据id查找
     * @param id
     * @return
     */
    Tag findById(Long id);

    /**
     * 根据name查找
     * @param name
     * @return
     */
    Tag findByName(String name);

    /**
     * 更新类型
     * @param tag
     */
    void updateTag(Tag tag);

    /**
     * 根据id查找
     * @param id
     */
    void deleteTagById(Long id);

    /**
     * 分页查询
     * @param pageNum  页码
     * @return
     */
    PageInfo<Tag> findByPage(Integer pageNum);

    List<Tag> findAll();

    List<Map> findDifTagsAndCount();

    List<Map> findAllDifTagsAndCount();

}
