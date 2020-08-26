package com.lhhh.dao;

import com.lhhh.bean.Blog;
import com.lhhh.bean.Type;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 博客接口
 * @author lhhh
 */
@Repository
@Transactional
public interface BlogDao {


    Blog findById(Long id);

    @Select("select * from t_blog")
    List<Blog> findAll();

    List<Blog> findByPage(Map map);

    @Select("SELECT t_blog.type_id id,t_type.name from t_blog INNER JOIN t_type on t_blog.type_id=t_type.id GROUP BY id")
    List<Type> findTypeName();

    @Insert("insert into t_blog(description,appreciation,commentable,content,create_time,first_picture,flag,published,recommend,share_statement,title,update_time,views,user_id,type_id) " +
            "values(#{description},#{appreciation},#{commentable},#{content},#{create_time},#{firstPicture},#{flag},#{published},#{recommend},#{shareStatement},#{title},#{update_time},#{views},#{user_id},#{typeId})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void addBlog(Map map);

    @Update("update t_blog set appreciation=#{appreciation}," +
            "commentable=#{commentable}," +
            "content=#{content}," +
            "first_picture=#{firstPicture}," +
            "flag=#{flag}," +
            "published=#{published}," +
            "recommend=#{recommend}," +
            "share_statement=#{shareStatement}," +
            "title=#{title}," +
            "update_time=#{update_time}," +
            "user_id=#{user_id}," +
            "description=#{description}," +
            "type_id=#{typeId} where id=#{blogId}" )
    void updateBlog(Map map);

    @Delete("delete from t_blog where id=#{id}")
    void deleteBlog(Long id);

    @Insert("insert into t_blog_tags values (#{blogId},#{tagsId})")
    void addBlogAndTags(long blogId,long tagsId);

    @Delete("delete from t_blog_tags where blogs_id=${id}")
    void deleteBlogAndTagsByBlogId(Long id);

    @Select("select * from t_blog order by views desc limit 5")
    List<Blog> findHot();


    List<Blog> findByTypeId(Long id);

    List<Blog> findByTagId(Long id);

    @Select("select distinct DATE_FORMAT(create_time, '%Y-%m') from t_blog")
    List<String> findDistinctDate();

    @Select("select * from t_blog where create_time like concat(#{date},'%')")
    List<Blog> findByDate(@Param("date") String date);

    @Select("select count(*) from t_blog")
    int findAllCount();

    @Update("update t_blog set views=(views+1) where id=#{id}")
    void updateBlogViews(Long id);
}
