package com.yyj.service;

import com.yyj.entity.AttendanceType;
import com.yyj.entity.Employee;
import com.yyj.entity.Note;
import com.yyj.utils.PageUtil;

import java.util.List;


public interface INoteService {
	public List<Note> queryByKeyWords(
            Note note, PageUtil util);
	
	public int getTotal(Note note);
	
	public List<Employee> queryAll(String employeeName);
	
	public List<AttendanceType> queryByCatecory(
            int typeCategory);

	public boolean insert(Note note);
	
	public Note queryone(int noteId);
	
	public boolean delete(int noteId);
}
