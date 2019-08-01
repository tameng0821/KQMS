package com.yyj.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.yyj.utils.JDBCUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import org.apache.commons.dbutils.handlers.ScalarHandler;

public class BaseDao<T> {
	

	public <T> T get(String sql, Class<T> clazz, Object[] params) {
		T obj = null;
		Connection conn = null;
		try {
			conn = JDBCUtil.getConnection();
			QueryRunner qRunner = new QueryRunner();
			obj = qRunner.query(conn, sql, new BeanHandler<T>(clazz), params);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally
		{
			JDBCUtil.closeConnection();
		}
		return obj;
	}
	public <T> T get(Connection conn,String sql, Class<T> clazz, Object[] params) throws SQLException {
		     T obj = null;
			QueryRunner qRunner = new QueryRunner();
			obj = qRunner.query(conn, sql, new BeanHandler<T>(clazz), params);		
		    return obj;
	}

	public <T> List<T> query(String sql,
			Class<T> clazz, Object[] params) {
		List beans = null;
		Connection conn = null;
		try {
			conn = JDBCUtil.getConnection();
//			System.out.println(conn+"----------------");
			QueryRunner qRunner = new QueryRunner();
			beans = (List) qRunner.query(conn,
					sql, new BeanListHandler<T>(clazz), 
					params);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally
		{
			JDBCUtil.closeConnection();
		}
		return beans;
	}


	public boolean update(String sql, Object...params) {
		Connection conn = null;
		boolean flag = false;
		try {
			conn = JDBCUtil.getConnection();
			QueryRunner qRunner = new QueryRunner();
			int i = qRunner.update(conn, sql, params);
			if (i > 0) {
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally
		{
			JDBCUtil.closeConnection();
		}
		return flag;
	}

	public boolean update(Connection conn, String sql, Object[] params) throws SQLException {
		boolean flag = false;
		QueryRunner qRunner = new QueryRunner();
		int i = qRunner.update(conn, sql, params);
		if (i > 0) {
			flag = true;
		}
		return flag;
	}


	public boolean batchUpdate(Connection conn, String sql, Object[][] params) throws SQLException {
		QueryRunner qRunner = new QueryRunner();
		int result = 0;
		boolean flag = false;
		result = qRunner.batch(conn, sql, params).length;
		if (result > 0) {
			flag = true;
		}
		return flag;
	}
	

	public long getCount(String sql,Object[] params){
		long count =0L;
		Connection conn = null;
		try {
			conn = JDBCUtil.getConnection();
			QueryRunner qRunner = new QueryRunner();
			count  = (Long) qRunner.query(conn, sql, new ScalarHandler(), params);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally
		{
			JDBCUtil.closeConnection();
		}
		return count;
	}

   public Long getCurrentKey(String sql,Object[] params){
    	Connection conn = null;
		Long key = 0l;
		try {
			conn = JDBCUtil.getConnection();
			QueryRunner qRunner = new QueryRunner();
			key =(long)qRunner.insert(conn,sql, new ScalarHandler(1), params);
			 
		} catch (SQLException e) {
			e.printStackTrace();
		}finally
		{
			JDBCUtil.closeConnection();
		}
		return key;
    }
    
}


