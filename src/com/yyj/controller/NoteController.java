package com.yyj.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.yyj.utils.PageUtil;
@WebServlet("/NoteController/*")
public class NoteController extends HttpServlet {

	public NoteController() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		/*
		Enumeration<String> enum1 = request.getParameterNames();
		while(enum1.hasMoreElements())
		{
			String name = enum1.nextElement();
			System.out.println(name);
		}
		*/
		
		PrintWriter out = response.getWriter();		
		String opt = request.getAttribute("opt").toString();
		INoteService service = new NoteServiceImpl();
		
		if( "queryByKeyWords".equals(opt) ){
			System.out.println("进来了");
			String isVerify = request.getParameter("isVerify");
			int typeId = 
					Integer.parseInt(
							request.getParameter("noteTypeId") == ""
							?"0":request.getParameter("noteTypeId")
								);
			String employeeName = 
					request.getParameter("employeeName");
			String operatorName = 
					request.getParameter("operatorName");
			String str_attendanceDate = 
					request.getParameter("leaveDate");
			
			Note note = new Note();
			note.setIsVerify(isVerify);
			note.setNoteTypeId(typeId);
			note.setEmployeeName(employeeName);
			note.setOperatorName(operatorName);
			
			if( str_attendanceDate != null&&"".equals(str_attendanceDate) == false){
				SimpleDateFormat sdf = new 
						SimpleDateFormat( "yyyy-MM-dd" );
				try {
					Date attendanceDate = sdf.parse(str_attendanceDate);
					note.setAttendanceDate(attendanceDate);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			
			System.out.println(note.toString());
			
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
			list.add(0, new AttendanceType(0 , "???" , "1"));
			String jsonString = JSON.toJSONString(list);
			out.print(jsonString);
		}
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
