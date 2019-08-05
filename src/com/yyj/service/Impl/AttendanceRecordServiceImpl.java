package com.yyj.service.Impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.yyj.dao.AttendanceRecordDao;
import com.yyj.entity.AttendanceRecord;
import com.yyj.entity.AttendanceType;
import com.yyj.service.IAttendanceRecordService;
import com.yyj.utils.JDBCUtil;

public class AttendanceRecordServiceImpl implements IAttendanceRecordService {

	AttendanceRecordDao dao = new AttendanceRecordDao();
	
	@Override
	public List<AttendanceRecord> queryByWords(AttendanceRecord attendancerecord) {
		
		String sql = " select  "
				+ " 	employee.EmployeeID, "
				+ " 	employee.EmployeeName, "
				+ " 	employee.CardNumber, "
				+ " 	department.DepartmentName , "
				+ " 	? attendanceDate , "
				+ " 	? AttendanceFlag , "
				+ " 	attendancerecord.AttendanceTypeID, "
				+ " 	attendancerecord.NoteID  "
				+ " from  "
				+ " 	employee left outer join attendancerecord  "
				+ " 	on employee.EmployeeID = attendancerecord.EmployeeID  "
				+ " 	and attendancerecord.AttendanceDate = ?  "
				+ " 	and attendancerecord.AttendanceFlag = ? "
				+ "  	inner join department  "
				+ " 	on employee.DepartmentID = department.DepartmentID  "
				+ " where employee.DepartmentID = ?  ";
		
		return dao.query(sql, AttendanceRecord.class, 
				new Object[]{ attendancerecord.getAttendanceDate() , 
			attendancerecord.getAttendanceFlag() , 
			attendancerecord.getAttendanceDate() , 
			attendancerecord.getAttendanceFlag() ,
			attendancerecord.getDepartmentId()});		
	}


	@Override
	public int getNoteId(int employeeId, Date attendanceDate,
			String attendanceFlag) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String d = sdf.format(attendanceDate) + (attendanceFlag.equals("上午")==true?"01":"02");
		
		String sql = " select "
				+ " 	noteId "
				+ " from note  where EmployeeID = ?  "
				+ " and ? >=  "
				+ " 	concat(DATE_FORMAT(StartDate,'%Y%m%d') ,  "
				+ " 	(case when StartTime = '上午' then '01' "
				+ " 	when StartTime = '下午' then  '02' "
				+ " 	end)) "
				+ " and ? <= concat(DATE_FORMAT(EndDate,'%Y%m%d') ,  "
				+ " 	(case when EndTime = '上午' then '01' "
				+ " 	when EndTime = '下午' then  '02' "
				+ " 	end)) "
				+ " limit 0,1";
		
		AttendanceRecord record = (AttendanceRecord) dao.get(sql, AttendanceRecord.class, new Object[]{ employeeId , d , d});
		if(record!=null){
			return record.getNoteID();
		}else{
			return 0;
		}
	}

	@Override
	public boolean batchUpdate(List<AttendanceRecord> list,
			AttendanceRecord record) {
		
		Connection conn = JDBCUtil.getConnection();
		try {
			conn.setAutoCommit(false);
			
			String sql = " delete from AttendanceRecord  "
					+ " where EmployeeID in (  "
					+ " 	select employeeId from employee "
					+ "	     where DepartmentID = ? "
				+ "  ) and AttendanceDate = ?  "
				+ "  and AttendanceFlag = ?  ";
		
		
			dao.update(conn , sql, new Object[]{
					record.getDepartmentId() ,
					record.getAttendanceDate(),
					record.getAttendanceFlag()
			});
			
			for(AttendanceRecord entity : list) {
				String sql_insert = " insert into AttendanceRecord "
						+ " ( EmployeeID , "
						+ "CardNumber , AttendanceDate , "
						+ " AttendanceFlag ,AttendanceTypeID ,"
						+ "AdminID , NoteID ) values (?,?,?,?,?,?,?)";
				
				boolean bln = dao.update(conn, sql_insert, 
						new Object[]{ entity.getEmployeeID() ,
						entity.getCardNumber() ,
						entity.getAttendanceDate(),
						entity.getAttendanceFlag(),
						entity.getAttendanceTypeID(),
						entity.getAdminID(),entity.getNoteID()}
						);
				if( bln == false )
				{
					conn.rollback();
				}
			}
			
			conn.commit();
			return true;
			
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}finally {
			try {
				conn.setAutoCommit(true);
				JDBCUtil.closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return false;
	}

	@Override
	public List<AttendanceType> getAllAttendanceType() {
		List query = dao.query("SELECT * FROM `attendancetype`", AttendanceType.class, null);
		return query;
	}
}
