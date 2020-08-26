package com.lhhh.dao;

import com.lhhh.bean.Type;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 分类
 * @author lhhh
 */
@Repository
@Transactional
public interface TypeDao {
    /**
     * 新增类型
     * @param type
     */
    @Insert("insert into t_type(name) values(#{name})")
    void addType(Type type);

    @Select(("select * from t_type where id = #{id}"))
    Type findById(Long id);

    @Update("update t_type set name=#{name} where id=#{id}")
    void updateType(Type type);


    @Delete("delete from t_type where id = #{id}")
    void deleteTypeById(Long id);

    @Select("select * from t_type")
    List<Type> findAll();

    @Select("select * from t_type order by id desc")
    List<Type> findByPage();

    @Select("select * from t_type where name=#{name}")
    Type findByName(@Param("name") String name);

    @Select("SELECT t_blog.type_id typeId,t_type.name,count(t_blog.type_id) count from t_blog left join t_type on t_blog.type_id=t_type.id GROUP BY t_blog.type_id ORDER BY count desc limit 10")
    List<Map> findDifTypeAndCount();

    @Select("SELECT t_blog.type_id typeId,t_type.name,count(t_blog.type_id) count from t_blog left join t_type on t_blog.type_id=t_type.id GROUP BY t_blog.type_id ORDER BY count desc")
    List<Map> findAllDifTypeAndCount();



}
