package com.lhhh.dao;

import com.lhhh.bean.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: lhhh
 * @date: Created in 2020/7/29
 * @description:
 * @version:1.0
 */
@Repository
@Transactional
public interface UserDao {
    /**
     * 通过用户名密码登录
     * @param username
     * @param password
     * @return
     */
    @Select("select * from t_user where username=#{username} and password=#{password}")
    /*@Results({
            @Result(column = "create_time",property = "createTime",jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "update_time",property = "updateTime",jdbcType = JdbcType.TIMESTAMP)
    })*/
    User findUserByUsernameAndPassword(@Param("username") String username,@Param("password") String password);
}
