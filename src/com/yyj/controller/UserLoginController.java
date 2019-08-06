package com.yyj.controller;
/**
 * @author Yanjiang
 * @create 2019-08-05 17:57
 */

import com.alibaba.fastjson.JSON;
import com.mysql.cj.Session;
import com.yyj.entity.Admin;
import com.yyj.service.IAdminService;
import com.yyj.service.Impl.AdminServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *@ClassName UserLoginController
 *@Description TODO
 *@Author Yanjiang
 *@Date 2019/8/5 17:57
 *@Version 1.0
 **/
@WebServlet("/userLoginController/*")
public class UserLoginController extends HttpServlet {

    IAdminService adminService=new AdminServiceImpl();

    /**
     * 首页通过ajax发送post请求获取到刚才登陆用户的信息
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out=resp.getWriter();
        out.print(JSON.toJSONString(req.getSession().getAttribute("user")));
    }

    /**
     * //get里面验证账户名密码并重定向到首页
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String opt = (String) req.getAttribute("opt");
        if("login".equals(opt)){
            String user = req.getParameter("username");
            String password = req.getParameter("password");
            Admin login = adminService.login(user, password);
            if(login!=null&&"1".equals(login.getAdminState())){
                req.getSession().setAttribute("user",login);
                resp.sendRedirect("/main.html");
                return;
            }
        }else {
            req.setAttribute("errorMsg","用户名或者密码错误");
            // 请求转发
            resp.sendRedirect("/index.html");
            return;
        }
    }
}
