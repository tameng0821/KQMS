package com.yyj.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.yyj.entity.AttendanceType;
import com.yyj.entity.Note;
import com.yyj.service.Impl.NoteServiceImpl;
import com.yyj.service.INoteService;
import com.yyj.utils.DateUtil;
import com.yyj.utils.PageUtil;
@WebServlet("/NoteController/*")
public class NoteController extends HttpServlet {
	INoteService service = new NoteServiceImpl();

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		String opt = request.getAttribute("opt").toString();

		
		if( "queryByKeyWords".equals(opt) ){
			String isVerify = request.getParameter("isVerify");
			int typeId = Integer.parseInt(request.getParameter("noteTypeId") == "" ?"0":request.getParameter("noteTypeId"));
			String employeeName = request.getParameter("employeeName");
			String operatorName = request.getParameter("operatorName");
			String str_attendanceDate = request.getParameter("leaveDate");

			Note note = new Note();
			note.setIsVerify(isVerify);
			note.setNoteTypeId(typeId);
			note.setEmployeeName(employeeName);
			note.setOperatorName(operatorName);
			
			if( str_attendanceDate != null&&"".equals(str_attendanceDate) == false){
				try {
					note.setAttendanceDate(DateUtil.getDate(str_attendanceDate,"yyyy-MM-dd"));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}

			int page = Integer.parseInt(request.getParameter("page"));
			int rows = Integer.parseInt(request.getParameter("rows"));

			PageUtil util = new PageUtil(page,rows);
			List<Note> list = service.queryByKeyWords(note, util);
			int count = service.getTotal(note);
			
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("total", count);
			map.put("rows", list);
			
			String jsonString = JSON.toJSONString(map);
			out.print(jsonString);
		}else if( "queryAll".equals(opt) ) {
			int page = Integer.parseInt(request.getParameter("page"));
			int rows = Integer.parseInt(request.getParameter("rows"));
			Note note = new Note();
			PageUtil util = new PageUtil(page,rows);
			
			List<Note> list = service.queryByKeyWords(note, util);
			int count = service.getTotal(note);
			
			HashMap<String, Object> map = 
					new HashMap<String, Object>();
			map.put("total", count);
			map.put("rows", list);
			
			String jsonString = JSON.toJSONString(map);
			out.print(jsonString);
		}else if("queryByCatecory".equals(opt)) {
			List<AttendanceType> list = service.queryByCatecory(1);
			list.add(0, new AttendanceType(0 , "全部" , "1"));
			String jsonString = JSON.toJSONString(list);
			out.print(jsonString);
		}else if("add".equals(opt)){
			String employeeId = request.getParameter("employeeId1");
			String operatorId = request.getParameter("operatorId1");
			String noteTypeId = request.getParameter("noteType");
			String fillInTime = request.getParameter("fillTime");
			String cause = request.getParameter("cause");
			String startDate = request.getParameter("startDate");
			String startTime = request.getParameter("startTime");
			String endDate = request.getParameter("endDate");
			String endTime = request.getParameter("endTime");
			String directorSign = request.getParameter("directorySign");
			String administrationSign = request.getParameter("administrationSign");
			String presidentSign = request.getParameter("presidentSign");
			String isVerify = request.getParameter("isVerify");
			System.out.println(employeeId);
			System.out.println(employeeId.trim());
			System.out.println(Integer.valueOf(employeeId));
			Note note = new Note();
			note.setEmployeeId(Integer.parseInt(employeeId));
			note.setNoteTypeId(Integer.parseInt(noteTypeId));
			note.setCause(cause);
			try {
				note.setFillInTime(DateUtil.getDate(fillInTime,"yyyy-MM-dd hh:mm:ss"));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			try {
				note.setStartDate(DateUtil.getDate(startDate,"yyyy-MM-dd"));
				note.setEndDate(DateUtil.getDate(endDate,"yyyy-MM-dd"));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			note.setStartTime(startTime);
			note.setEndTime(endTime);
			note.setDirectorSign(directorSign);
			note.setAdministrationSign(administrationSign);
			note.setPresidentSign(presidentSign);
			note.setIsVerify(isVerify);
			//应该是从Session里面获取
			note.setAdminId(1);
			note.setOperatorId(Integer.parseInt( operatorId.trim()));


			boolean bln = service.insert(note);

			HashMap<String , Object> map = new
					HashMap<String , Object>();
			map.put("status", bln);
			String jsonString = JSON.toJSONString(map);
			out.print(jsonString);
			out.flush();
			out.close();
		}
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}


}
