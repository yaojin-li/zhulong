package com.demo.zhulong.common.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * @Description: 视频格式枚举类
 * --------------------------------------
 * @ClassName: VideoEnum.java
 * @Date: 2019/9/30 19:23
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
public enum VideoEnum {
    AVI("avi", "avi格式"),
    MP4("mp4", "mp4格式"),
    RMVB("rmvb", "rmvb格式"),
    WMV("wmv","wmv格式"),
    FLV("flv","flv格式"),
    ;

    private String name;
    private String desc;

    private VideoEnum(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }


    public static VideoEnum transfer(String name) throws Exception {
        if (StringUtils.isNotBlank(name)) {
            for (VideoEnum videoEnum : values()) {
                if (StringUtils.equalsIgnoreCase(videoEnum.getName(), name)) {
                    return videoEnum;
                }
            }
        }
        throw new Exception(String.format("illegal RiskLevelEnum[%s]", name));
    }

}
