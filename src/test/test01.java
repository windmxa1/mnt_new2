package test;

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
		String url = "jdbc:mysql://192.168.1.117:3306/zabbix?characterEncoding=UTF-8";
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

		File file = new File("E:/doorLog.txt");
		BufferedReader reader = null;
		test01 t = new test01();
		try {
			t.connSQL();
			System.out.println("以行为单位读取文件内容，一次读一整行：");
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			int line = 1;
			// 一次读入一行，直到读入null为文件结束
			while ((tempString = reader.readLine()) != null) {
				// 显示行号
				if (!tempString.equals("")) {
					System.out.println("line " + line + ": " + tempString);
					String s[] = tempString.split("-");
					String sql = "";
					if (s.length>6) {
						sql = "insert into dc_events(host,clock,doorid,event,card_reader,cardid) values('"
								+ s[0]
								+ "' ,'"
								+ s[1]
								+ "' , '"
								+ Integer.parseInt(s[3])
								+ "','"
								+ s[5]
								+ "','"
								+ Integer.parseInt(s[7])
								+ "','"
								+ Long.parseLong(s[9]) + "')";
					} else {
						sql = "insert into dc_events(host,clock,doorid,event) values('"
								+ s[0]
								+ "' ,'"
								+ s[1]
								+ "' , '"
								+ Integer.parseInt(s[3])
								+ "','"
								+ s[5]
								+ "')";
					}
					if (t.insetSql(sql) == true) {
						System.out.println("insert successfully");
					}
					line++;
				}
			}
			reader.close();
			t.deconnSQL();

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}

	}

}
