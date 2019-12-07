package com.demo.zhulong.config;

import org.apache.tomcat.jni.Directory;
import org.springframework.stereotype.Component;

/**
 * @Description: HDFS 常量配置
 * --------------------------------------
 * @ClassName: HdfsProperty.java
 * @Date: 2019/10/11 18:21
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
@Component
public class HdfsConfig {

    // @Value("${}")
    /**
     * HDFS 操作目录
     */
    private static final String BROWSE_DIRECTORY = "/user/hadoop/input/";

    private static final String HDFS_USER = "hadoop";

    private static final String MASTER_IP = "192.168.1.14";

    private static final String MASTER_PORT = "9000";

    private static final String SLAVE_ONE_IP = "192.168.1.13";

    private static final String SLAVE_ONE_PORT = "9000";


    /**
     * 以 hdfs:// + IP地址 + 端口号 的形式拼成预览文件在 HDFS 中的最终地址
     * eg: hdfs://192.168.2.0:9000
     */
    private static final String MASTER_IP_PORT = "hdfs://" + MASTER_IP + ":" + MASTER_PORT;

    private static final String SLAVE_ONE_IP_PORT = "hdfs://" + SLAVE_ONE_IP + ":" + SLAVE_ONE_PORT;


    /**
     * 以 hdfs:// + IP地址 + 端口号 + HDFS 中存储目录的形式拼成上传文件在 HDFS 中的最终存储地址
     * eg: hdfs://192.168.2.0:9000/user/lxj/input/testfile2/
     */
    private static final String MASTER_ADDRESS = "hdfs://" + MASTER_IP + ":" + MASTER_PORT + BROWSE_DIRECTORY;

    private static final String SLAVE_ONE_ADDRESS = "hdfs://" + SLAVE_ONE_IP + ":" + SLAVE_ONE_PORT + BROWSE_DIRECTORY;


    public static String getHdfsPath() {
        return BROWSE_DIRECTORY;
    }

    public static String getHdfsUser() {
        return HDFS_USER;
    }

    public static String getMasterIp() {
        return MASTER_IP;
    }

    public static String getMasterPort() {
        return MASTER_PORT;
    }

    public static String getSlaveOneIp() {
        return SLAVE_ONE_IP;
    }

    public static String getSlaveOnePort() {
        return SLAVE_ONE_PORT;
    }

    public static String getMasterIpPort() {
        return MASTER_IP_PORT;
    }

    public static String getSlaveOneIpPort() {
        return SLAVE_ONE_IP_PORT;
    }

    public static String getMasterAddress() {
        return MASTER_ADDRESS;
    }

    public static String getSlaveOneAddress() {
        return SLAVE_ONE_ADDRESS;
    }

}
