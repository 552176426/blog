package com.lhhh.bean;

import java.util.List;

/**
 * @author: lhhh
 * @date: Created in 2020/7/29
 * @description: 标签类
 * @version:1.0
 */
public class Tag {
    private Long id;
    private String name;
    /**
     *  所有博客
     *  一个标签对应多个博客
     */
    private List<Blog> blogs;

    public Tag() {
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
        return "Tag{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", blogs=" + blogs +
                '}';
    }
}
