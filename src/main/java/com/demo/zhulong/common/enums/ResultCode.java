package com.demo.zhulong.common.enums;

/**
 * @Description: --------------------------------------
 * @ClassName: ResultCode.java
 * @Date: 2019/11/16 22:33
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
public enum ResultCode {

    SUCCESS("0000", "success"),
    FAIL("0001", "fail"),
    EXCEPTION("0002", "exception"),

    SUCCESS_DELETE_FILE("0003", "文件删除成功"),
    FAIL_DELETE_FILE("0004", "文件删除失败"),
    EXCEPTION_DELETE_FILE("0005", "文件删除异常"),
    NOT_FOUND_FILE_IN_HDFS("0006", "HDFS 中文件路径不存在"),

    ;

    private String code;
    private String desc;

    private ResultCode(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }}
