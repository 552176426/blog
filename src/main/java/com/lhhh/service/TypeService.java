package com.lhhh.service;

import com.github.pagehelper.PageInfo;
import com.lhhh.bean.Type;

import java.util.List;
import java.util.Map;

/**
 * @author: lhhh
 * @date: Created in 2020/7/30
 * @description: 类型接口
 * @version:1.0
 */
public interface TypeService {
    /**
     * 新增类型
     * @param type
     */
    void addType(Type type);

    /**
     * 根据id查找
     * @param id
     * @return
     */
    Type findById(Long id);

    /**
     * 根据name查找
     * @param name
     * @return
     */
    Type findByName(String name);

    /**
     * 更新类型
     * @param type
     */
    void updateType(Type type);

    /**
     * 根据id查找
     * @param id
     */
    void deleteTypeById(Long id);

    /**
     * 分页查询
     * @param pageNum  页码
     * @return
     */
    PageInfo<Type> findByPage(Integer pageNum);

    /**
     * 查询所有
     * @return
     */
    List<Type>  findAll();

    List<Map> findDifTypeAndCount();

    List<Map> findAllDifTypeAndCount();

}
