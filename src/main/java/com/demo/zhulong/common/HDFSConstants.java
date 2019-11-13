package com.demo.zhulong.common;

/**
 * @author: Administrator
 * @Tags:
 * @Description: HDFS公共配置
 * @Date: 2017年7月20日 上午11:39:34
 */
public class HDFSConstants {

	public static String NamenodeIP = "192.168.2.0";
	public static String NamenodePort = "9000";
	public static String HDFSPath = "/user/lxj/input/testfile2/";
	public static String HDFSUser = "lixiangjie";
	public static String nowUser = "Administrator";


	//以   hdfs:// + IP地址 + 端口号 + HDFS中的存储目录   的形式拼成上传文件在HDFS中的最终存储地址  --- hdfs://192.168.2.0:9000/user/lxj/input/testfile2/
	public static String HDFSAddress = "hdfs://" + NamenodeIP + ":" + NamenodePort + HDFSPath;

	//以   hdfs:// + IP地址 + 端口号     的形式拼成预览文件在HDFS中的最终地址  --- hdfs://192.168.2.0:9000
	public static String HDFSIPPort = "hdfs://" + NamenodeIP + ":" + NamenodePort;


}


