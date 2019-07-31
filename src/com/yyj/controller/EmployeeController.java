package com.yyj.controller;
/**
 * @author Yanjiang
 * @create 2019-07-24 15:09
 */

import com.alibaba.fastjson.JSON;
import com.yyj.entity.Employee;
import com.yyj.entity.Position;
import com.yyj.service.IEmployeetService;
import com.yyj.service.IPositionService;
import com.yyj.service.Impl.EmployeeServiceImpl;
import com.yyj.service.Impl.PositionServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

/**
 *@ClassName positionController
 *@Description TODO
 *@Author Yanjiang
 *@Date 2019/7/24 15:09
 *@Version 1.0
 **/
@WebServlet("/EmployeeController/*")
public class EmployeeController extends HttpServlet{
    IEmployeetService employeetService=new EmployeeServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        String option = (String)req.getAttribute("opt");
        if("query".equals(option)){
            int i=0;
            String s = req.getParameter("employeeName");
            String s1 = req.getParameter("departmentID");
            if(!"".equals(s1)&&s1!=null){
               i = Integer.parseInt(s1);
            }
            List<Employee> employees = employeetService.selectAllByPage(s, i);
            String s2 = JSON.toJSONString(employees);
            System.out.println(s2);
            writer.print(s2);

        }else if("queryone".equals(option)){
            int id = Integer.parseInt(req.getParameter("employeeID"));
            Employee selectone = employeetService.selectOne(id);
            String s = JSON.toJSONString(selectone);
            writer.print(s);
        }else if("edit".equals(option)){
            //employeeID
            //employeeName
            //employeeGender
            //positionID
            //departmentID
            //cardNumber
            //employeeState
            //employeeMemo
            int employeeID=Integer.parseInt(req.getParameter("employeeID"));
            String employeeName = req.getParameter("employeeName");
            String employeeGender = req.getParameter("employeeGender");
            int positionID = Integer.parseInt(req.getParameter("positionID"));
            int departmentID = Integer.parseInt(req.getParameter("departmentID"));
            String cardNumber = req.getParameter("cardNumber");
            String employeeState = req.getParameter("employeeState");
            String employeeMemo = req.getParameter("employeeMemo");
            Employee employee=new Employee(employeeID,employeeName,employeeGender,positionID,departmentID,cardNumber,employeeState,employeeMemo);
            boolean update = employeetService.update(employee);
            writer.print(update);
        }else if("del".equals(option)){
            int id=Integer.valueOf(req.getParameter("employeeID"));
            boolean delete = employeetService.delete(id);
            writer.print(delete);
        }else if("add".equals(option)){
            String employeeName = req.getParameter("employeeName");
            String employeeGender = req.getParameter("employeeGender");
            int positionID = Integer.parseInt(req.getParameter("positionID"));
            int departmentID = Integer.parseInt(req.getParameter("departmentID"));
            String cardNumber = req.getParameter("cardNumber");
            String employeeState = req.getParameter("employeeState");
            String employeeMemo = req.getParameter("employeeMemo");
            Employee employee=new Employee(null,employeeName,employeeGender,positionID,departmentID,cardNumber,employeeState,employeeMemo);
            boolean add = employeetService.add(employee);
            writer.print(add);
            }else if("queryByPage".equals(option)){
//            String empname = req.getParameter("employeeName");
//            String departmentId = req.getParameter("departmentID");
            int num = Integer.parseInt(req.getParameter("page"));
            int size = Integer.parseInt(req.getParameter("rows"));
//          if("".equals(empname) && "".equals(departmentId)){
            List<Employee> list = employeetService.selectAll(num,size);
            HashMap<String,Object> map=new HashMap<String,Object>();
            map.put("total",employeetService.getcount());
            map.put("rows",list);
            String string= JSON.toJSONString(map);
            writer.print(string);
//            }else{
//                int id=Integer.parseInt(departmentId);
//                List<Employee> list = employeetService.selectAllByPage(empname,id,num,size);
//                HashMap<String,Object> map=new HashMap<String,Object>();
//                map.put("total",employeetService.getcount());
//                map.put("rows",list);
//                String string= JSON.toJSONString(map);
//                writer.print(string);
//            }
        }
        writer.flush();
        writer.close();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
