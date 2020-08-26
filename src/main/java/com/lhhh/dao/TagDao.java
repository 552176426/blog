package com.lhhh.dao;

import com.lhhh.bean.Tag;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 标签
 * @author lhhh
 */
@Repository
@Transactional
public interface TagDao {
    /**
     * 新增标签
     * @param tag
     */
    @Insert("insert into t_tag(name) values(#{name})")
    void addTag(Tag tag);

    @Select(("select * from t_tag where id = #{id}"))
    Tag findById(Long id);

    @Update("update t_tag set name=#{name} where id=#{id}")
    void updateTag(Tag tag);


    @Delete("delete from t_tag where id = #{id}")
    void deleteTagById(Long id);

    @Select("select * from t_tag")
    List<Tag> findAll();

    @Select("select * from t_tag order by id desc")
    List<Tag> findByPage();

    @Select("select * from t_tag where name=#{name}")
    Tag findByName(@Param("name") String name);

    @Select(("SELECT * from t_tag where id in (SELECT tags_id from t_blog_tags where blogs_id=#{id})"))
    List<Tag> findByBlogId(long id);

    @Select("SELECT t_blog_tags.tags_id tagId,t_tag.name,COUNT(t_blog_tags.tags_id) count from t_blog_tags left JOIN t_tag on t_blog_tags.tags_id = t_tag.id GROUP BY t_blog_tags.tags_id ORDER BY COUNT(t_blog_tags.tags_id) desc limit 10")
    List<Map> findDifTagAndCount();

    @Select("SELECT t_blog_tags.tags_id tagId,t_tag.name,COUNT(t_blog_tags.tags_id) count from t_blog_tags left JOIN t_tag on t_blog_tags.tags_id = t_tag.id GROUP BY t_blog_tags.tags_id ORDER BY COUNT(t_blog_tags.tags_id) desc")
    List<Map> findAllDifTagAndCount();

}
