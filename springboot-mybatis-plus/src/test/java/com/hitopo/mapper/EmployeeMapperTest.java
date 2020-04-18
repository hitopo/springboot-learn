package com.hitopo.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hitopo.entity.Employee;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

/**
 * @author hitopo
 * @version v1.0
 * @classname EmployeeMapperTest
 * @time 2020/4/18 15:21
 * @description 测试类
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class EmployeeMapperTest {
    @Autowired
    private EmployeeMapper employeeMapper;

    @Test
    public void testSelect() {
        // 根据主键查询单个对象
        Employee employee = employeeMapper.selectById(3L);
        log.info("selectById(),employee={}", employee);
        log.info("--------------------");

        // 查询所有列表
        List<Employee> employeeList1 = employeeMapper.selectList(null);
        log.info("selectList(),employeeList1={}", employeeList1);
        log.info("--------------------");

        //查询所有指定id的数据
        List<Long> ids = new ArrayList<>();
        ids.add(2L);
        ids.add(3L);
        ids.add(5L);
        List<Employee> employeeList2 = employeeMapper.selectBatchIds(ids);
        log.info("selectBatchIds(),employeeList2={}", employeeList2);
        log.info("--------------------");

        // 查询单个记录
        // 如果查询出来的不是一条记录会报错
        Employee tom = employeeMapper.selectOne(new QueryWrapper<Employee>().eq("last_name", "Tom"));
        log.info("selectOne(),employee={}", tom);
        log.info("--------------------");

        // 根据条件查询，条件封装在Map中
        Map<String, Object> map = new HashMap<>();
        map.put("last_name", "James");
        map.put("age", 18);
        List<Employee> james = employeeMapper.selectByMap(map);
        log.info("selectByMap(),employee={}", james);
        log.info("--------------------");
    }

    @Test
    public void testSave() {
        // 待保存的记录，注意此时没有创建主键和email属性
        Employee employee = new Employee();
        employee.setLastName("张三");
        employee.setAge(12);
        employee.setGender("1");
        int result = employeeMapper.insert(employee);
        log.info("保存成功，受影响的行数是：{}", result);
        log.info("employee.getId():{}", employee.getId());
    }

    @Test
    public void testUpdate() {
        Employee employee = new Employee();
        employee.setId(7L);
        employee.setLastName("李四");
        employee.setGender("0");
        employee.setEmail("123@gmail.com");
        int result = employeeMapper.updateById(employee);
        log.info("受影响的行数是：{}", result);
    }

    @Test
    public void testDelete() {
        int result1 = employeeMapper.deleteById(6L);
        int result2 = employeeMapper.deleteBatchIds(Arrays.asList(2L, 4L));
        Map<String, Object> map = new HashMap<>();
        map.put("age", 12);
        int result3 = employeeMapper.deleteByMap(map);
        log.info("deleteById()受影响的行数:{}", result1);
        log.info("deleteBatchIds()受影响的行数:{}", result2);
        log.info("deleteByMap()受影响的行数:{}", result3);
    }

    @Test
    public void testConditionalOps() {
        // 条件查询
        List<Employee> list = employeeMapper.selectList(new QueryWrapper<Employee>().between("age", 12, 25));
        log.info("selectList()，年龄在12-25的职工列表：{}", list);

        // 条件删除
        int result = employeeMapper.delete(new QueryWrapper<Employee>().eq("last_name", "张三"));
        log.info("delete(),删除lastname是张三的记录");

        // 条件修改
        Employee employee = new Employee();
        employee.setAge(20);
        employeeMapper.update(employee, new UpdateWrapper<Employee>().like("email", "@qq.com"));
        log.info("update(),修改email中包含@qq.com字符串的记录的年龄为20");
    }

    @Test
    public void testPage() {
        IPage<Employee> iPage = employeeMapper.selectPage(new Page<>(2, 3), null);
        log.info("数据：{}", iPage.getRecords());
        log.info("当前页：{}", iPage.getCurrent());
        log.info("页的大小：{}", iPage.getSize());
        log.info("总页数：{}", iPage.getPages());
        log.info("总记录数：{}", iPage.getTotal());
    }
}
