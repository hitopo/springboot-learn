package com.hitopo.mapper;

import com.hitopo.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author hitopo
 * @version v1.0
 * @classname UserMapperAno
 * @time 2020/4/18 10:25
 * @description 基于注解的UserMapper接口
 */
public interface UserMapperAno {
    @Select("SELECT * FROM user")
    @Results({
            @Result(property = "createTime", column = "create_time")
    })
    List<User> findAll();

    @Select("SELECT * FROM user WHERE id = #{id}")
    @Results({
            @Result(property = "createTime", column = "create_time")
    })
    User findOneById(Long id);

    @Insert("INSERT INTO user(username, password, create_time) VALUES (#{username},#{password},#{createTime})")
    void save(User user);

    @Update("UPDATE user SET username=#{username},password=#{password} WHERE id = #{id}")
    void update(User user);

    @Delete("DELETE FROM  user WHERE id = #{id}")
    void delete(Long id);
}
