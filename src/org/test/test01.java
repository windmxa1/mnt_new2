package org.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class test01 {
	private Connection conn = null;
	private PreparedStatement statement = null;

	public void connSQL() {
		String url = "jdbc:mysql://192.168.103.253:3306/zabbix?characterEncoding=UTF-8";
		String username = "zabbix";
		String password = "zabbix";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, username, password);

		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean insetSql(String sql) {
		try {
			statement = conn.prepareStatement(sql);
			statement.executeUpdate();
			return true;
		} catch (SQLException e) {
			System.out.println("插入数据库时出错：");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("插入时出错：");
			e.printStackTrace();
		}
		return false;
	}

	void deconnSQL() {
		try {
			if (conn != null)
				conn.close();
		} catch (Exception e) {
			System.out.println("关闭数据库问题 ：");
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		test01 t = new test01();
		try {
			t.connSQL();
			String sql = "insert into dc_events(host,clock,doorid,event,card_reader,cardid,is_read) values('"
					+ "111"
					+ "','"
					+ "1123"
					+ "','"
					+ 1
					+ "','"
					+ "123"
					+ "','"
					+ 213123 + "','" + 123123L + "','" + 0 + "')";
			t.insetSql(sql);
			t.deconnSQL();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}

	}

}
