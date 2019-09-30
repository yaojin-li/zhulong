package com.demo.zhulong.common.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * @Description: 文档类文件枚举类
 * --------------------------------------
 * @ClassName: DocEnum.java
 * @Date: 2019/9/30 19:30
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
public enum DocEnum {
    PDF("pdf", "pdf格式"),
    TXT("txt", "txt格式"),
    M3U8("m3u8","m3u8格式"),
    ZIP("zip","zip格式"),
    HTML("html", "html格式"),

    DOC("doc","doc格式"),
    DOCX("docx","docx格式"),
    XLSX("xlsx","xlsx格式"),
    XLS("xls","xls格式"),
    CSV("csv","csv格式"),
    PPT("ppt","ppt格式"),
    ;

    private String name;
    private String desc;

    private DocEnum(String name, String desc) {
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


    public static DocEnum transfer(String name) throws Exception {
        if (StringUtils.isNotBlank(name)) {
            for (DocEnum docEnum : values()) {
                if (StringUtils.equalsIgnoreCase(docEnum.getName(), name)) {
                    return docEnum;
                }
            }
        }
        throw new Exception(String.format("illegal RiskLevelEnum[%s]", name));
    }

}
