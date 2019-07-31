package com.yyj.service.Impl;
/**
 * @author Yanjiang
 * @create 2019-07-24 17:23
 */

import com.yyj.dao.DepartmentDao;
import com.yyj.entity.Department;
import com.yyj.service.IDepartmentService;

import java.util.List;

/**
 *@ClassName DepartmentServiceImpl
 *@Description TODO
 *@Author Yanjiang
 *@Date 2019/7/24 17:23
 *@Version 1.0
 **/
public class DepartmentServiceImpl implements IDepartmentService {
    private DepartmentDao departmentDao=new DepartmentDao();

    @Override
    public List<Department> selectAll() {
        String sql="select departmentID,departmentName from department";
        return departmentDao.query(sql, Department.class, null);
    }

    @Override
    public boolean add(Department department) {
        String sql="INSERT INTO `kqms`.`department` VALUES (null,?)";
        return departmentDao.update(sql, new Object[]{department.getDepartmentName()});
    }

    @Override
    public boolean update(Department department){
        String sql="update department set departmentName=? where departmentId=?";
        return departmentDao.update(sql,new Object[]{department.getDepartmentName(),department.getDepartmentId()});
    }

    @Override
    public Department selectOne(int id) {
        String sql="select * from department where departmentId=?";
        return (Department)departmentDao.get(sql,Department.class,new Object[]{id});
    }
    @Override
    public boolean delete(int id) {
        String sql="delete from department where departmentId=?";
        return departmentDao.update(sql,new Object[]{id});
    }

}
