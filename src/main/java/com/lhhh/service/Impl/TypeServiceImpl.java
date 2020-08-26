package com.lhhh.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lhhh.bean.Type;
import com.lhhh.dao.TypeDao;
import com.lhhh.exception.NotFoundException;
import com.lhhh.service.TypeService;
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
public class TypeServiceImpl implements TypeService {
    @Autowired
    private TypeDao typeDao;

    @Override
    public void addType(Type type) {
        typeDao.addType(type);
    }

    @Override
    public Type findById(Long id) {
        return typeDao.findById(id);
    }

    @Override
    public Type findByName(String name) {
        Type type = typeDao.findByName(name);
        return type;
    }

    @Override
    public void updateType(Type type) {
        Type t = findById(type.getId());
        if (t==null){
            throw new NotFoundException("无法通过该id找到分类");
        }
        typeDao.updateType(type);
    }

    @Override
    public void deleteTypeById(Long id) {
        typeDao.deleteTypeById(id);
    }

    @Override
    public PageInfo<Type> findByPage(Integer pageNum) {
        PageHelper.startPage(pageNum,5);
        List<Type> types = typeDao.findByPage();
        PageInfo<Type> pageInfo = new PageInfo(types);
        System.out.println("总记录数："+pageInfo.getTotal());
        System.out.println("总页数："+pageInfo.getPages());
        return pageInfo;
    }

    @Override
    public List<Type> findAll() {
        return typeDao.findAll();
    }

    @Override
    public List<Map> findDifTypeAndCount() {
        return typeDao.findDifTypeAndCount();
    }

    @Override
    public List<Map> findAllDifTypeAndCount() {
        return typeDao.findAllDifTypeAndCount();
    }
}
