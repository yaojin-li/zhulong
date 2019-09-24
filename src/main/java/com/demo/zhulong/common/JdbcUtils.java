package com.demo.zhulong.common;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * @Description: JdbcUtils工具类，连接数据库，获取数据库连接和释放数据库连接
 * @Author: Administrator
 * @Tags: 
 * @Date: 2017年8月1日 上午11:26:18
 */
public class JdbcUtils {

	// 读取jdbc.properties配置文件中的信息
	private static String jdbc_driver = null;
	private static String jdbc_url = null;
	private static String jdbc_username = null;
	private static String jdbc_password = null;

	// 静态域默认配置
	static {
		try {
			// 读取db.properties文件中的数据库连接信息
			InputStream in = JdbcUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
			
			//新建默认值为空的属性列表
			Properties prop = new Properties();
			prop.load(in);

			// 获取数据库连接驱动
			jdbc_driver = prop.getProperty("jdbc_driver");
			// 获取数据库连接URL地址
			jdbc_url = prop.getProperty("jdbc_url");
			// 获取数据库连接用户名
			jdbc_username = prop.getProperty("jdbc_username");
			// 获取数据库连接密码
			jdbc_password = prop.getProperty("jdbc_password");

			// 加载数据库驱动
			Class.forName(jdbc_driver);

		} catch (Exception e) {
			throw new ExceptionInInitializerError(e);
		}
	}


	/**
	 * @Description: 获取数据库连接对象
	 * @Author: Administrator
	 * @Tags: @return
	 * @Tags: @throws SQLException
	 * @Date: 2017年8月1日 上午11:33:17
	 * @return:Connection
	 */
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(jdbc_url, jdbc_username, jdbc_password);
	}



	/**
	 * @Description: 释放资源， 要释放的资源包括Connection数据库连接对象，负责执行SQL命令的Statement对象，存储查询结果的ResultSet对象
	 * @Author: Administrator
	 * @Tags: @param connection 数据库连接对象
	 * @Tags: @param statement 执行SQL命令的对象
	 * @Tags: @param resultSet 存储查询结果的对象
	 * @Date: 2017年8月1日 上午11:35:37
	 * @return: void
	 */
	public static void release(Connection connection, Statement statement, ResultSet resultSet) {
		
		if (resultSet != null) {
			try {
				// 关闭存储查询结果的ResultSet对象
				resultSet.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			resultSet = null;
		}
		
		if (statement != null) {
			try {
				// 关闭负责执行SQL命令的Statement对象
				statement.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			statement = null;
		}

		if (connection != null) {
			try {
				// 关闭Connection数据库连接对象
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			connection = null;
		}
	}
}
