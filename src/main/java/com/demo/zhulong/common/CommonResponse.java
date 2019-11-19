package com.demo.zhulong.common;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 返回结果公共类
 * --------------------------------------
 * @ClassName: Results.java
 * @Date: 2019/11/16 22:02
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
public class CommonResponse implements Serializable {
    private static final long serialVersionUID = -8115461559148091288L;

    /**
     * 处理结果
     * */
    protected String result;

    /**
     * 处理信息
     */
    protected String msg;

    private Map<String, Object> jsonValues = new HashMap<String, Object>();

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * 增加输出属性至JsonObject对象中
     * @param key
     * @param value
     */
    public void addJsonObject(String key, Object value) {
        jsonValues.put(key, value);
    }

    /**
     * 增加输出属性至JsonObject对象中
     * @param values
     */
    public void addJsonObject(Map<String, Object> values) {
        if (values == null || values.size() == 0){
            return;
        }
        jsonValues.putAll(values);
    }

    /**
     * 获取所有JsonValues值
     * @return
     */
    public Map<String, Object> getJsonValues() {
        return this.jsonValues;
    }

    /**
     * 获取所有JsonValues其中的值
     * @param key
     * @return
     */
    public Object getJsonValueByKey(String key) {
        return this.jsonValues.get(key);
    }

}


