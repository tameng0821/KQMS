package com.yyj.controller;
/**
 * @author Yanjiang
 * @create 2019-07-24 15:09
 */

import com.alibaba.fastjson.JSON;
import com.yyj.entity.Position;
import com.yyj.service.Impl.PositionServiceImpl;
import com.yyj.service.IPositionService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 *@ClassName positionController
 *@Description TODO
 *@Author Yanjiang
 *@Date 2019/7/24 15:09
 *@Version 1.0
 **/
@WebServlet("/PositionController/*")
public class PositionController extends HttpServlet{
    IPositionService positionService=new PositionServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PrintWriter writer = resp.getWriter();

        String option = (String)req.getAttribute("opt");
        if("query".equals(option)){
            List<Position> list = positionService.selectAll();
            String string= JSON.toJSONString(list);
            writer.print(string);
        }else if("queryone".equals(option)){
            int id = Integer.parseInt(req.getParameter("positionID"));
            Position selectone = positionService.selectOne(id);
            String s = JSON.toJSONString(selectone);
            writer.print(s);
        }else if("edit".equals(option)){
            int id=Integer.parseInt(req.getParameter("editPositionId"));
            String name=req.getParameter("editPositionName");
            Position position=new Position(id,name);
            boolean update = positionService.update(position);
            writer.print(update);
        }else if("del".equals(option)){
            int id=Integer.valueOf(req.getParameter("positionId"));
            boolean delete = positionService.delete(id);
            writer.print(delete);
        }else if("add".equals(option)){
            String name = req.getParameter("positionName");
            Position position=new Position();
            position.setPositionName(name);
            boolean add = positionService.add(position);
            writer.print(add);
        }
        writer.flush();
        writer.close();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
