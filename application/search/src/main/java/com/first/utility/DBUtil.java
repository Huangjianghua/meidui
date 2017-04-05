package com.first.utility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DBUtil {
	private static final Log log = LogFactory.getLog(DBUtil.class);

	private static DataSource dataSource = (DataSource) SpringUtil.webApplication.getBean("dataSource");
	
	/**
	 * 获取连接
	 * 
	 * @return
	 * @throws Exception
	 */
	public static Connection getConnection() throws Exception {
		return dataSource.getConnection();
	}

	/**
	 * 释放连接
	 * 
	 * @param rs
	 * @param st
	 * @param conn
	 */
	public static void free(ResultSet rs, Statement st, Connection conn) {
		try {
			if (rs != null)
				rs.close();
			if (st != null)
				st.close();
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			log.error("free异常:", e);
		} 

	}

	/**
	 * 普通SQL查询时间
	 * 
	 * @param sql
	 * @return
	 */
	public static List<Map<String, Object>> queryBySql(String sql) {
		Connection conn = null;
		List<Map<String, Object>> tempList = new ArrayList<Map<String, Object>>();
		try {
			conn = DBUtil.getConnection();
			Statement pstmt = conn.createStatement();
			ResultSet rs = pstmt.executeQuery(sql);
			ResultSetMetaData lineInfo = rs.getMetaData();
			int columnCount = lineInfo.getColumnCount();
			while (rs.next()) {
				Map<String, Object> map = new HashMap<String, Object>();
				for (int i = 1; i <= columnCount; i++) {
					String columeName = lineInfo.getColumnLabel(i);
					String columeType = lineInfo.getColumnTypeName(i);
					String columeValue = "datetime"
							.equalsIgnoreCase(columeType) ? StringUtil
							.formatDateTime(rs.getString(i)) : rs.getString(i);
					map.put(columeName, columeValue);
				}
				tempList.add(map);
			}
			DBUtil.free(rs, pstmt, conn);
		} catch (Exception e) {
			log.error("queryBySql异常:", e);
		}
		return tempList;
	}

	
	/**
	 * 普通SQL查询对象 
	 * @param sql
	 * @return
	 */
	public static List<Map<String, String>> queryObjectBySql(String sql) {
		Connection conn = null;
		ResultSet rs = null;
		Statement pstmt = null;
		//查询整个对象的键值对
		List<Map<String, String>> objList = new ArrayList<Map<String, String>>();
		
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.createStatement();
			rs = pstmt.executeQuery(sql);
			ResultSetMetaData lineInfo = rs.getMetaData();
			int columnCount = lineInfo.getColumnCount();
			while (rs.next()) {
				//单个属性的键值对
				Map<String, String> map = new HashMap<String, String>();
				for (int i = 1; i <= columnCount; i++) {
					String columeName = lineInfo.getColumnLabel(i);
					String columeValue = rs.getString(i);
					map.put(columeName, columeValue);
				}
				objList.add(map);
			}
			
		} catch (Exception e) {
			log.error("queryBySql异常:", e);
		}finally{
			DBUtil.free(rs, pstmt, conn);
		}
		return objList;
	}
	
	
	/**
	 * 更新SQL
	 * @param sql
	 * @return
	 */
	public static void updateSql(String sql) {
		Connection conn = null;
		PreparedStatement st = null;
		
		try {
			st = DBUtil.getConnection().prepareStatement(sql);
			st.executeUpdate();
		} catch (Exception e) {
			log.error("queryBySql异常:", e);
		}finally{
			DBUtil.free(null,st, conn);
		}
	}
	
	/**
	 * 预处理
	 * @param sql
	 * @param data
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public static int updateDateByPS(String sql, ArrayList data)
			throws Exception {
		Connection conn = null;
		PreparedStatement st = null;
		int num = 0;
		try {
			conn = DBUtil.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		try {
			st = conn.prepareStatement(sql);
			for (int i = 0; i < data.size(); i++) {
				st.setObject(i + 1, data.get(i));
			}
			num = st.executeUpdate();
		} catch (Exception e) {
			log.error("updateDateByPS异常:", e);
		} finally {
			DBUtil.free(null, st, conn);
		}
		return num;
	}

	/**
	 * 获取主键
	 * 
	 * @param table
	 * @return
	 */
	public static Long getId(String table) {
		String sql = "select seq_" + table + ".nextval as \"id\" from dual";
		Long iId = 0l;
		List<Map<String, Object>> list = DBUtil.queryBySql(sql.toString());
		if (list.size() > 0) {
			iId = Long.valueOf(list.get(0).get("id").toString());
		}
		return iId;
	}
}
