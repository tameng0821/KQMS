package com.yyj.service;

import java.util.Date;
import java.util.List;


import com.yyj.entity.AttendanceRecord;
import com.yyj.entity.AttendanceType;

public interface IAttendanceRecordService {

	 public List<AttendanceRecord> queryByWords(AttendanceRecord attendancerecord);
	 
	 public int getNoteId(int employeeId, Date attendanceDate, String attendanceFlag);
	 
	 public boolean batchUpdate(List<AttendanceRecord> list,AttendanceRecord record);

	 public List<AttendanceType> getAllAttendanceType();
}
