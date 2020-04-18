package com.hitopo.mpg.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hitopo.mpg.beans.Employee;
import com.hitopo.mpg.mapper.EmployeeMapper;
import com.hitopo.mpg.service.EmployeeService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hitopo
 * @since 2020-04-18
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {
}
