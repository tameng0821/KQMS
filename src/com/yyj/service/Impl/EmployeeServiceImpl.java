package com.yyj.service.Impl;
/**
 * @author Yanjiang
 * @create 2019-07-30 14:07
 */

import com.yyj.dao.EmployeeDao;
import com.yyj.entity.Employee;
import com.yyj.service.IEmployeetService;

import java.util.Arrays;
import java.util.List;

/**
 *@ClassName EmployeeServiceImpl
 *@Description TODO
 *@Author Yanjiang
 *@Date 2019/7/30 14:07
 *@Version 1.0
 **/
public class EmployeeServiceImpl implements IEmployeetService {
    private EmployeeDao employeeDao=new EmployeeDao();
    @Override
    public List<Employee> selectAll(int num, int size) {
        int index=(num-1)*size;
        String sql="select e.employeeID,e.employeeName,e.employeeGender,e.positionID,e.departmentID,e.cardNumber,e.employeeState,e.employeeMemo,p.positionName,d.departmentName FROM employee e left join position p ON e.positionID=p.positionID left JOIN department d on e.departmentID=d.departmentId LIMIT ?,?";
        return employeeDao.query(sql,Employee.class,new Object[]{index,size});
    }

    @Override
    public List<Employee> selectAllByPage(String empname,int departmentid) {
        System.out.println(empname+departmentid);
        Object[] params = {};
        String sql = "select e.employeeID,e.employeeName,e.employeeGender,e.positionID,e.departmentID,e.cardNumber,e.employeeState,e.employeeMemo,p.positionName,d.departmentName FROM employee e left join position p ON e.positionID=p.positionID left JOIN department d on e.departmentID=d.departmentId where 0=0";
        StringBuilder sbl = new StringBuilder(sql);
        if("".equals(empname)==false&&empname!=null){
            sbl.append(" and employeeName like ?");
            params = Arrays.copyOf(params, params.length + 1);
            params[ params.length-1 ] = "%" + empname + "%";
        }
        if(departmentid!=0){
            sbl.append(" and e.departmentID=?");
            params = Arrays.copyOf(params, params.length + 1);
            params[ params.length-1 ] = departmentid;
        }
        System.out.println(sbl.toString());
        return employeeDao.query(sbl.toString(),Employee.class,params);
    }

    @Override
    public boolean add(Employee employee) {
        String sql="INSERT INTO `kqms`.`employee` VALUES (null,?,?,?,?,?,?,?)";
        return employeeDao.update(sql, new Object[]{employee.getEmployeeName(),
                employee.getEmployeeGender(),
                employee.getPositionID(),
                employee.getDepartmentID(),
                employee.getCardNumber(),
                employee.getEmployeeState(),
                employee.getEmployeeMemo()}
        );
    }
    @Override
    public boolean delete(int id) {
        String sql="delete from employee where employeeID=?";
        return employeeDao.update(sql,new Object[]{id});
    }
    @Override
    public boolean update(Employee employee) {
        String sql="update employee set employeeName=?,employeeGender=?,positionID=?,departmentID=?,cardNumber=?,employeeState=?,employeeMemo=? where employeeID=?";
        return employeeDao.update(sql,new Object[]{employee.getEmployeeName(),
                employee.getEmployeeGender(),
                employee.getPositionID(),
                employee.getDepartmentID(),
                employee.getCardNumber(),
                employee.getEmployeeState(),
                employee.getEmployeeMemo(),
                employee.getEmployeeID()});
    }
    @Override
    public Employee selectOne(int id) {
        String sql="select e.employeeID,e.employeeName,e.employeeGender,e.positionID,e.departmentID,e.cardNumber,e.employeeState,e.employeeMemo,p.positionName,d.departmentName FROM employee e left join position p ON e.positionID=p.positionID left JOIN department d on e.departmentID=d.departmentId where employeeID=?";
        return (Employee) employeeDao.get(sql,Employee.class,new Object[]{id});
    }



    @Override
    public long getcount() {
        return employeeDao.getCount("select count(*) from employee",null);
    }
}
