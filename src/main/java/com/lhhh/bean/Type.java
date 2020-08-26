package com.lhhh.bean;

import java.util.List;

/**
 * @author: lhhh
 * @date: Created in 2020/7/29
 * @description: 分类实体类
 * @version:1.0
 */
public class Type {
    private Long id;
    private String name;
    /**
     * 所有博客
     * 一个类型对应多个博客
     */
    private List<Blog> blogs;

    public Type() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Blog> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<Blog> blogs) {
        this.blogs = blogs;
    }

    @Override
    public String toString() {
        return "Type{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", blogs=" + blogs +
                '}';
    }
}
