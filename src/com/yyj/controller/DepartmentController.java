package com.yyj.controller;
/**
 * @author Yanjiang
 * @create 2019-07-24 15:09
 */

import com.alibaba.fastjson.JSON;
import com.yyj.entity.Department;
import com.yyj.entity.Position;
import com.yyj.service.IDepartmentService;
import com.yyj.service.Impl.DepartmentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.sun.javafx.fxml.expression.Expression.add;

/**
 *@ClassName DepartmentController
 *@Description TODO
 *@Author Yanjiang
 *@Date 2019/7/24 15:09
 *@Version 1.0
 **/
@WebServlet("/DepartmentController/*")
public class DepartmentController extends HttpServlet{
    IDepartmentService departmentService=new DepartmentServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PrintWriter writer = resp.getWriter();

        String option = (String)req.getAttribute("opt");
        if("query".equals(option)){
            List<Department> list = departmentService.selectAll();
            String string= JSON.toJSONString(list);
            writer.print(string);
        }else if("queryone".equals(option)){
            int id = Integer.parseInt(req.getParameter("departmentID"));
            Department selectone = departmentService.selectOne(id);
            String s = JSON.toJSONString(selectone);
            writer.print(s);
        }else if("edit".equals(option)){
            int id=Integer.parseInt(req.getParameter("editDepartmentId"));
            String name=req.getParameter("editDepartmentName");
            Department department=new Department(id,name);
            boolean update = departmentService.update(department);
            writer.print(update);
        }else if("del".equals(option)){
            int id=Integer.valueOf(req.getParameter("departmentId"));
            boolean delete = departmentService.delete(id);
            writer.print(delete);
        }else if("add".equals(option)){
            String name = req.getParameter("departmentName");
            Department department=new Department();
            department.setDepartmentName(name);
            boolean add = departmentService.add(department);
            writer.print(add);
        }
        writer.flush();
        writer.close();
    }



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
    /*
    public void query(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Department> list = departmentService.selectAll();
        String string= JSON.toJSONString(list);
        resp.getWriter().print(string);
    }

    private void queryone(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
        int id=Integer.valueOf(req.getParameter("departmentId"));
        Department department = departmentService.queryone(id);
        Map map=new HashMap();
        map.put("department",department);
        String string= JSON.toJSONString(map);
    }
    private void edit(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
        int id=Integer.valueOf(req.getParameter("editDepartmentId"));
        String name=req.getParameter("editDepartmentName");
        Department department=new Department();
        department.setDepartmentId(id);
        department.setDepartmentName(name);
        int update = departmentService.update(department);
        if(update>0){
            resp.getWriter().print(true);
        }else {
            resp.getWriter().print(false);
        }
    }
    private void del(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
        int id=Integer.valueOf(req.getParameter("departmentId"));
        int delete = departmentService.delete(id);
        if(delete>0){
            resp.getWriter().print(true);
        }else {
            resp.getWriter().print(false);
        }
    }
    private void adddept(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int add = departmentService.add(department);
        if(add>0){
            resp.getWriter().print(true);
        }else {
            resp.getWriter().print(false);
        }
    }
    */
}
