package com.demo.zhulong.filter;

import com.demo.zhulong.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;
import java.util.Vector;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * @Description: XSS 过滤
 * --------------------------------------
 * @ClassName: XSSFilter.java
 * @Date: 2019/9/17 20:10
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/

@WebFilter(urlPatterns = "/*", filterName = "XssFilter")
public class XSSFilter implements Filter {
    private static final Logger logger = LoggerFactory.getLogger(WebConfiguration.class);

    //不需要过滤的路径集合
    private String exclude = null;
    //匹配不需要过滤路径的正则表达式
    private Pattern pattern = null;


    public void setExclude(String exclude) {
        this.exclude = exclude;
        pattern = Pattern.compile(getRegStr(this.exclude));
    }

    /**
     * 将传递进来的不需要过滤得路径集合的字符串格式化成一系列的正则规则
     *
     * @param str 不需要过滤的路径集合
     * @return 正则表达式规则
     */
    private String getRegStr(String str) {
        if (org.apache.commons.lang3.StringUtils.isNotBlank(str)) {
            // 以分号进行分割
            String[] excludes = str.split(";");
            int length = excludes.length;
            for (int i = 0; i < length; i++) {
                String tmpExclude = excludes[i];
                //对点、反斜杠和星号进行转义
                tmpExclude = tmpExclude.replace("\\", "\\\\").replace(".", "\\.").replace("*", ".*");

                tmpExclude = "^" + tmpExclude + "$";
                excludes[i] = tmpExclude;
            }
            return org.apache.commons.lang3.StringUtils.join(excludes, "|");
        }
        return str;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("========过滤器初始化========");
        String excludeUrl = "/static/*";
        setExclude(excludeUrl);
        logger.info("========filter exclude===" + excludeUrl);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse response,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String requestURI = request.getRequestURI();
        if (org.apache.commons.lang3.StringUtils.isNotBlank(requestURI)) {
            requestURI = requestURI.replace(request.getContextPath(), "");
        }
        if (pattern.matcher(requestURI).matches()) {
            filterChain.doFilter(request, response);
        } else {
            EscapeScriptwrapper escapeScriptwrapper = new EscapeScriptwrapper(request);
            filterChain.doFilter(escapeScriptwrapper, response);
        }
    }

    @Override
    public void destroy() {
        logger.info("===过滤器销毁===");
    }


    /**
     * 继承HttpServletRequestWrapper，创建装饰类，以达到修改HttpServletRequest参数的目的
     */
    private class EscapeScriptwrapper extends HttpServletRequestWrapper {
        private Map<String, String[]> parameterMap;  //所有参数的Map集合

        public EscapeScriptwrapper(HttpServletRequest request) {
            super(request);
            parameterMap = request.getParameterMap();
        }

        //重写几个HttpServletRequestWrapper中的方法

        /**
         * 获取所有参数名
         *
         * @return 返回所有参数名
         */
        @Override
        public Enumeration<String> getParameterNames() {
            Vector<String> vector = new Vector<String>(parameterMap.keySet());
            return vector.elements();
        }

        /**
         * 获取指定参数名的值，如果有重复的参数名，则返回第一个的值
         * 接收一般变量 ，如text类型
         *
         * @param name 指定参数名
         * @return 指定参数名的值
         */
        @Override
        public String getParameter(String name) {
            String[] results = parameterMap.get(name);
            if (results == null || results.length <= 0) {
                return null;
            } else {
                return escapeXSS(results[0]);
            }
        }

        /**
         * 获取指定参数名的所有值的数组，如：checkbox的所有数据
         * 接收数组变量 ，如checkobx类型
         */
        @Override
        public String[] getParameterValues(String name) {
            String[] results = parameterMap.get(name);
            if (results == null || results.length <= 0) {
                return null;
            } else {
                int length = results.length;
                for (int i = 0; i < length; i++) {
                    results[i] = escapeXSS(results[i]);
                }
                return results;
            }
        }

        /**
         * 过滤字符串中的js脚本
         * 解码：StringEscapeUtils.unescapeXml(escapedStr)
         */
        private String escapeXSS(String str) {
            str = StringUtils.html2Text(str);
            return str;
        }

    }

}
