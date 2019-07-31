package com.yyj.service;
/**
 * @author Yanjiang
 * @create 2019-07-24 17:22
 */

import com.yyj.entity.Department;
import com.yyj.entity.Position;

import java.util.List;

/**
 *@ClassName IDepartmentService
 *@Description TODO
 *@Author Yanjiang
 *@Date 2019/7/24 17:22
 *@Version 1.0
 **/
public interface IDepartmentService {
    List<Department> selectAll();
    boolean add(Department department);
    boolean delete(int id);
    boolean update(Department department);
    Department selectOne(int id);
}
