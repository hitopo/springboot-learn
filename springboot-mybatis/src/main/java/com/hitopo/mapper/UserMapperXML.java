package com.hitopo.mapper;

import com.hitopo.entity.User;

import java.util.List;

/**
 * @author hitopo
 * @version v1.0
 * @classname UserMapperXML
 * @time 2020/4/18 11:03
 * @description XMLMapper类
 */
public interface UserMapperXML {

    List<User> findAll();

    User findOneById(Long id);

    void save(User user);

    void update(User user);

    void delete(Long id);
}
