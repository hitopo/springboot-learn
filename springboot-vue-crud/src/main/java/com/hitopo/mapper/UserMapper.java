package com.hitopo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hitopo.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hitopo
 * @since 2020-04-19
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
