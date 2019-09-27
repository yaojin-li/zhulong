package com.demo.zhulong.common.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * @Description: 图像格式枚举类
 * --------------------------------------
 * @ClassName: ImageEnum.java
 * @Date: 2019/9/27 19:33
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
public enum ImageEnum {
    JPG("jpg", "jpg格式"),
    PNG("png", "png格式"),
    JPEG("jpeg", "jpeg格式"),
    GIF("gif", "gif格式"),
    ;

    private String name;
    private String desc;

    private ImageEnum(String name, String desc) {
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


    public static ImageEnum transfer(String name) throws Exception {
        if (StringUtils.isNotBlank(name)) {
            for (ImageEnum imageEnum : values()) {
                if (StringUtils.equalsIgnoreCase(imageEnum.getName(), name)) {
                    return imageEnum;
                }
            }
        }
        throw new Exception(String.format("illegal RiskLevelEnum[%s]", name));
    }

}
