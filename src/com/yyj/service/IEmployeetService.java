package com.yyj.service;
/**
 * @author Yanjiang
 * @create 2019-07-24 17:22
 */

import com.yyj.entity.Department;
import com.yyj.entity.Employee;

import java.util.List;

/**
 *@ClassName IDepartmentService
 *@Description TODO
 *@Author Yanjiang
 *@Date 2019/7/24 17:22
 *@Version 1.0
 **/
public interface IEmployeetService {
    List<Employee> selectAll(int num,int size);
    List<Employee> selectAllByPage(String empname,int departmentid);
    boolean add(Employee employee);
    boolean delete(int id);
    boolean update(Employee employee);
    Employee selectOne(int id);
    long getcount();
}
