package com.yyj.service.Impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import com.yyj.dao.NoteDao;
import com.yyj.entity.AttendanceType;
import com.yyj.entity.Employee;
import com.yyj.entity.Note;
import com.yyj.service.INoteService;
import com.yyj.utils.PageUtil;

public class NoteServiceImpl  implements INoteService {

	private NoteDao dao = new NoteDao();
	
	@Override
	public List<Note> queryByKeyWords(Note note, PageUtil util) {

		Object[] params = {};
		
		String sql = " select "
				+ "  	noteID,FillInTime , e1.CardNumber , e1.EmployeeName , "
				+ " 	TypeName , StartDate , StartTime , EndDate , EndTime, "
				+ " 	e2.EmployeeName operatorName  "
				+ " from  "
				+ " 	note left outer join employee e1 "
				+ " 	on note.EmployeeID = e1.EmployeeID  "
				+ " 	left outer join attendancetype  "
				+ " 	on note.NoteTypeID = attendancetype.TypeId  "
				+ " 	and attendancetype.TypeCategory = 1  "
				+ " 	left outer join employee e2  "
				+ " 	on note.OperatorID = e2.EmployeeID  "
				+ " where  0 = 0 " ;
				
		StringBuilder sbl = new StringBuilder(sql); 
		
		if( note.getIsVerify() != null && 
				"".equals(note.getIsVerify()) == false)
		{
			sbl.append(" and note.IsVerify = ? ");
			
			params = Arrays.copyOf(params, params.length + 1);
			params[ params.length-1 ] = note.getIsVerify();
		}
		
		if( note.getNoteTypeId() != 0 )
		{
			sbl.append(" and note.NoteTypeID = ? ");
			params = Arrays.copyOf(params, params.length + 1);
			params[ params.length-1 ] = note.getNoteTypeId();
		}
		
		if( note.getEmployeeName() != null && "".equals(note.getEmployeeName()) == false  )
		{
			sbl.append(" and e1.EmployeeName like ?  ");
			params = Arrays.copyOf(params, params.length + 1);
			params[ params.length-1 ] = "%" + note.getEmployeeName() + "%";
		}
		
		if( note.getOperatorName() != null && "".equals(note.getOperatorName()) == false )
		{
			sbl.append(" and e2.EmployeeName like ?  ");
			params = Arrays.copyOf(params, params.length + 1);
			params[ params.length-1 ] = "%" + note.getOperatorName()+ "%";
		}
		
		if( note.getAttendanceDate() != null  )
		{
			sbl.append(" and ( ? >= StartDate and ? <= EndDate )  ");
			params = Arrays.copyOf(params, params.length + 2);
			params[ params.length-2 ] = note.getAttendanceDate();
			params[ params.length-1 ] = note.getAttendanceDate();
		}
		
		sbl.append(" limit " + util.getStartIndex() + " , " + util.getPageSize());
		return dao.query(sbl.toString(), Note.class, params);
	}


	@Override
	public int getTotal(Note note) {
		
		Object[] params = {};
		
		String sql = " select "
				+ "  	count(*)"
				+ " from  "
				+ " 	note left outer join employee e1 "
				+ " 	on note.EmployeeID = e1.EmployeeID  "
				+ " 	left outer join attendancetype  "
				+ " 	on note.NoteTypeID = attendancetype.TypeId  "
				+ " 	and attendancetype.TypeCategory = 1  "
				+ " 	left outer join employee e2  "
				+ " 	on note.OperatorID = e2.EmployeeID  "
				+ " where  0 = 0 " ;
				
		StringBuilder sbl = new StringBuilder(sql); 
		
		if( note.getIsVerify() != null && 
				"".equals(note.getIsVerify()) == false)
		{
			sbl.append(" and note.IsVerify = ? ");
			
			params = Arrays.copyOf(params, params.length + 1);
			params[ params.length-1 ] = note.getIsVerify();
		}
		
		if( note.getNoteTypeId() != 0 )
		{
			sbl.append(" and note.NoteTypeID = ? ");
			params = Arrays.copyOf(params, params.length + 1);
			params[ params.length-1 ] = note.getNoteTypeId();
		}
		
		if( note.getEmployeeName() != null && "".equals(note.getEmployeeName()) == false  )
		{
			sbl.append(" and e1.EmployeeName like ?  ");
			params = Arrays.copyOf(params, params.length + 1);
			params[ params.length-1 ] = "%" + note.getEmployeeName() + "%";
		}
		
		if( note.getOperatorName() != null && "".equals(note.getOperatorName()) == false )
		{
			sbl.append(" and e2.EmployeeName like ?  ");
			params = Arrays.copyOf(params, params.length + 1);
			params[ params.length-1 ] = "%" + note.getOperatorName()+ "%";
		}
		
		if( note.getAttendanceDate() != null  )
		{
			sbl.append(" and ( ? >= StartDate and ? <= EndDate )  ");
			params = Arrays.copyOf(params, params.length + 2);
			params[ params.length-2 ] = note.getAttendanceDate();
			params[ params.length-1 ] = note.getAttendanceDate();
		}
		
		long l = dao.getCount(sbl.toString(), params);
		return (int)l;
	}

	@Override
	public List<Employee> queryAll(String employeeName) {
		return null;
	}

	@Override
	public List<AttendanceType> queryByCatecory(int typeCategory) {
		
		return dao.query("select * from attendancetype where typeCategory = ? ", 
				AttendanceType.class, new Object[]{typeCategory});
	}

	@Override
	public boolean insert(Note note) {
		String sql = " insert into Note ( EmployeeID , "
				+ "NoteTypeID , Cause , FillInTime "
				+ " , DirectorSign , adminstrationSign, "
				+ " PresidentSign , StartDate , "
				+ " StartTime , EndDate , EndTime , "
				+ " AdminID ,  OperatorID , IsVerify ) "
				+ " values (?,?,?,?,?,?,?,?,?,?,?,?,?,? )";

		return dao.update(sql,
				note.getEmployeeId() ,
				note.getNoteTypeId() ,
				note.getCause() ,
				note.getFillInTime(),
				note.getDirectorSign(),
				note.getAdminstrationSign(),
				note.getPresidentSign(),note.getStartDate(),
				note.getStartTime(),note.getEndDate(),
				note.getEndTime(),note.getAdminId(),
				note.getOperatorId(),note.getIsVerify());
	}

	@Override
	public Note queryone(int noteId) {
		String sql = "select "
				+ " note.EmployeeID  , "
				+ " e1.EmployeeName , e1.CardNumber , "
				+ " note.OperatorID ,note.cause,e2.EmployeeName OperatorName , "
				+ " e2.CardNumber  OperatorCardNumber ,  "
				+ " note.NoteTypeID , note.FillInTime , "
				+ " note.StartDate , note.StartTime, "
				+ " note.EndDate , note.EndTime , "
				+ " note.DirectorSign , note.AdminstrationSign,"
				+ " note.PresidentSign , note.IsVerify "
				+ " from note  inner join employee e1 "
				+ " on note.EmployeeID = e1.EmployeeID  "
				+ " inner join employee e2  "
				+ " on note.EmployeeID = e2.EmployeeID "
				+ " where noteId = ? ";

		return (Note) dao.get(sql, Note.class, new Object[]{noteId});
	}

	@Override
	public boolean delete(int noteId) {
		return false;
	}

}
