package com.yyj.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.yyj.entity.AttendanceRecord;
import com.yyj.entity.AttendanceType;
import com.yyj.service.IAttendanceRecordService;
import com.yyj.service.Impl.AttendanceRecordServiceImpl;

@WebServlet("/AttendanceRecordController/*")
public class AttendanceRecordController extends HttpServlet {
	
	IAttendanceRecordService service = new AttendanceRecordServiceImpl();
	
	/**
	 * Constructor of the object.
	 */
	public AttendanceRecordController() {
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
		String opt = request.getAttribute("opt").toString();
		if( "queryByWords".equals(opt)) {
			queryByWords(request,response);
		}else if("getNoteId".equals(opt)) {
			try {
				getNoteId(request,response);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}else if("batchUpdate".equals(opt)) {
			batchUpdate(request,response);
		}else  if("queryType".equals(opt)){
			queryType(request,response);
		}
	}

	private void queryType(HttpServletRequest request, HttpServletResponse response) throws IOException {
		List<AttendanceType> allAttendanceType = service.getAllAttendanceType();
		String s = JSON.toJSONString(allAttendanceType);
		PrintWriter out = response.getWriter();
		out.print(s);
		out.flush();
		out.close();
	}

	private void batchUpdate(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		int departmentId = Integer.parseInt(request.getParameter("QdepartmentId"));
		String attendanceDate = request.getParameter("QattendanceDate");
		String attendanceFlag = request.getParameter("QattendanceFlag");
		
		AttendanceRecord record = new AttendanceRecord();
		record.setDepartmentId(departmentId);
		record.setAttendanceDate(attendanceDate);
		record.setAttendanceFlag(attendanceFlag);
		
		String[] employees = request.getParameterValues("employeeId");
		String[] cardNumbers = request.getParameterValues("cardNumber");
		String[] attendanceTypeIds = request.getParameterValues("attendanceTypeId");
		String[] noteIds = request.getParameterValues("noteId");
		
		List<AttendanceRecord> list = new ArrayList<AttendanceRecord>();
		
		for (int i = 0; i < employees.length; i++) {
			AttendanceRecord bean = new AttendanceRecord();
			bean.setEmployeeID(Integer.parseInt(employees[i]));
			bean.setCardNumber(cardNumbers[i]);
			bean.setAttendanceDate(attendanceDate);
			bean.setAttendanceFlag(attendanceFlag);
			bean.setAttendanceTypeID(Integer.parseInt(attendanceTypeIds[i]) );
			bean.setAdminID(2);
			
			if( noteIds[i] != null && "".equals(noteIds[i]) == false && "undefined".equals(noteIds[i]) == false && "无请假单".equals(noteIds[i]) == false){
				bean.setNoteID( Integer.parseInt(
						noteIds[i]) );
			}
			
			list.add(bean);
		}
		
		boolean bln = service.batchUpdate(list, record);
		PrintWriter out = response.getWriter();
		out.print(bln);
		out.flush();
		out.close();
	}

	private void getNoteId(HttpServletRequest request, HttpServletResponse response) throws ParseException, IOException {
		int employeeId = Integer.valueOf(request.getParameter("employeeId").toString().trim());
		String attDate = request.getParameter("attendanceDate");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date attendanceDate = sdf.parse(attDate);
		String attendanceFlag = request.getParameter("attendanceFlag");
		int noteId = service.getNoteId(employeeId, attendanceDate, attendanceFlag);
		PrintWriter out = response.getWriter();
		out.print(noteId);
		out.flush();
		out.close();
	}

	private void queryByWords(HttpServletRequest request,HttpServletResponse response) throws IOException {
		int departmentId = Integer.parseInt(request.getParameter("departmentId"));
		String attendanceDate = request.getParameter("attendanceDate");
		String attendanceFlag = request.getParameter("attendanceFlag");
		AttendanceRecord record = new AttendanceRecord();
		
		record.setDepartmentId(departmentId);
		record.setAttendanceDate(attendanceDate);
		record.setAttendanceFlag(attendanceFlag);
		
		List<AttendanceRecord> list = service.queryByWords(record);
		PrintWriter out = response.getWriter();
		String jsonString = JSON.toJSONString(list);
		out.print(jsonString);
		out.flush();
		out.close();
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
