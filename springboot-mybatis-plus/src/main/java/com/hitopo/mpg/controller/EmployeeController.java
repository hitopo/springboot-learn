package com.hitopo.mpg.controller;


import com.hitopo.mpg.beans.Employee;
import com.hitopo.mpg.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 * @author hitopo
 * @since 2020-04-18
 */
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/login")
    public Employee login() {
        return employeeService.getById(3L);
    }
}

