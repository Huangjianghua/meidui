package com.meiduimall.service.catalog.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.Test;

public class JDBCTest {

	@Test
	public void testJDBC() throws Exception {
		// 1.注册数据库驱动
		// --由于mysql在Driver类的实现中自己注册了一次,而我们又注册了一次,于是会导致MySql驱动被注册两次
		// --创建MySql的Driver对象时,导致了程序和具体的Mysql驱动绑死在了一起,在切换数据库时需要改动java代码
		// DriverManager.registerDriver(new Driver());
		Class.forName("com.mysql.jdbc.Driver");

		// 2.获取数据库连接
		Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.4.236:3306/meiduishop0302", "root",
				"meidui@2016.?!");

		// 3.获取传输器对象
		Statement stat = conn.createStatement();

		// 4.利用传输器传输sql语句到数据库中执行,获取结果集对象
		ResultSet rs = stat.executeQuery("select * from sysitem_item where item_id>1000 and item_id<2000");

		// 5.遍历结果集获取查询结果
		while (rs.next()) {
			int item_id = rs.getInt("item_id");
			String spec_desc = rs.getString("spec_desc");
			if (spec_desc != null && spec_desc.contains("spec_private_value_id")) {
				System.out.println("item_id: " + item_id);
				int indexOf = spec_desc.indexOf("spec_private_value_id");
				String temp = "";
				if (spec_desc.length() > indexOf + 40) {
					temp = spec_desc.substring(indexOf, indexOf + 40);
				} else {
					temp = spec_desc.substring(indexOf, spec_desc.length() - 1);
				}
				System.out.println("temp = " + temp);
			}
		}

		// 6.关闭资源
		rs.close();
		stat.close();
		conn.close();
		
		System.out.println("close");
	}
}
