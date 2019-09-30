package com.demo.zhulong.common.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * @Description: 音频格式枚举类
 * --------------------------------------
 * @ClassName: AudioEnum.java
 * @Date: 2019/9/30 19:43
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
public enum AudioEnum {
    MP3("mp3", "mp3格式"),
    WAV("wav", "wav格式"),
    MID("mid", "mid格式"),
    ;

    private String name;
    private String desc;

    private AudioEnum(String name, String desc) {
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


    public static AudioEnum transfer(String name) throws Exception {
        if (StringUtils.isNotBlank(name)) {
            for (AudioEnum audioEnum : values()) {
                if (StringUtils.equalsIgnoreCase(audioEnum.getName(), name)) {
                    return audioEnum;
                }
            }
        }
        throw new Exception(String.format("illegal RiskLevelEnum[%s]", name));
    }

}
