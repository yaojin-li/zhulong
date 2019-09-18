package com.demo.zhulong.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

/**
 * @Description: 字符串工具类
 * --------------------------------------
 * @ClassName: StringUtil.java
 * @Date: 2019/9/17 20:19
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
public final class StringUtil {

    /**
     * @Description: HTML转换为普通文本
     * @Date:        2019/9/17 20:54
     * @Params:
     * @ReturnType:
     **/
    public static String html2Text(String inputString) {
        if (StringUtils.isEmpty(inputString)){
            return inputString;
        }
        // 含html标签的字符串
        String htmlStr = inputString;
        String textStr = "";
        java.util.regex.Pattern p_script;
        java.util.regex.Matcher m_script;
        java.util.regex.Pattern p_style;
        java.util.regex.Matcher m_style;
        java.util.regex.Pattern p_html;
        java.util.regex.Matcher m_html;

        try {
            // 定义script的正则表达式{或<script[^>]*?>[\\s\\S]*?<\\/script>
            String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>";
            // 定义style的正则表达式{或<style[^>]*?>[\\s\\S]*?<\\/style>
            String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>";
            // 定义HTML标签的正则表达式
            String regEx_html = "<[^>]+>";
            p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
            if (p_script == null){
                return inputString;
            }
            m_script = p_script.matcher(htmlStr);
            // 过滤script标签
            htmlStr = m_script.replaceAll("");
            p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
            m_style = p_style.matcher(htmlStr);
            // 过滤style标签
            htmlStr = m_style.replaceAll("");
            p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
            m_html = p_html.matcher(htmlStr);
            htmlStr = htmlStr.replace("  ", "");
            textStr = htmlStr.trim();
            textStr = htmlStr;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        // 返回文本字符串
        return textStr;
    }

}
