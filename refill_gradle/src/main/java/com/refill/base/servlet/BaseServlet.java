package com.refill.base.servlet;

import com.alibaba.fastjson.JSONObject;
import com.refill.base.bean.ResultJsonBean;
import com.refill.base.constant.Const;
import com.refill.base.constant.e.BaseEnum;
import com.refill.base.constant.e.CookieEnum;
import com.refill.base.constant.e.SessionEnum;
import com.refill.util.StringUtil;
import org.apache.log4j.Logger;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author Peter Guo
 * @version 0.1
 * @ClassName BaseServlet
 * @Description 基础通用servlet
 * @date 17/04/25
 */
@SuppressWarnings("serial")
public abstract class BaseServlet extends HttpServlet {

    /*
     * log4j日志记录
     */
    public Logger LOG = Logger.getLogger(this.getClass());

    /**
     * 重写servlet销毁
     */
    public void destroy() {
        super.destroy();
    }

    /**
     * 重写servlet初始化
     */
    public void init() throws ServletException {
        super.init();
    }

    /**
     * 输出html文本
     *
     * @param request  HttpServletRequest对象
     * @param response HttpServletResponse 对象
     * @param html     html文本内容
     */
    protected void sendHtml(HttpServletRequest request, HttpServletResponse response, String html) {
        try {
            PrintWriter out = response.getWriter();
            response.setContentType("text/html");
            response.setCharacterEncoding("utf-8");
            out.write(html);
            out.close();
        } catch (IOException e) {
            LOG.error(e);
        }
    }

    /**
     * 获取用户真实IP
     *
     * @param request HttpServletRequest对象
     * @return 返回用户IP
     */
    public String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    /**
     * 获取请求的数据流，主要提供给微信平台接口使用
     *
     * @param request HttpServletRequest对象
     * @return 返回请求的数据流，例如：微信平台会返回JSON或xml字符串
     */
    public String readStreamParameter(HttpServletRequest request) {
        StringBuilder buffer = new StringBuilder();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(request.getInputStream(), "utf-8"));
            String line = null;
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != reader) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return buffer.toString();
    }

    /**
     * 输出json数据字符串
     *
     * @param response    HttpServletResponse 对象
     * @param jsonDataStr json数据对象
     */
    protected void outJson(HttpServletResponse response, Object jsonDataStr) {
        try {
            response.setContentType("application/json;charset=utf-8");
            PrintWriter out = response.getWriter();
            out.print(jsonDataStr);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置session
     *
     * @param request HttpServletRequest 对象
     * @param key     键SessionConst里面定义
     * @param obj     对象
     */
    protected void setSession(HttpServletRequest request, SessionEnum key, Object obj) {
        if (StringUtil.isBlank(obj.toString())) {
            return;
        }
        request.getSession().setAttribute(key.toString(), obj);
    }

    /**
     * 获取session
     *
     * @param request HttpServletRequest 对象
     * @param key     键SessionConst里面定义
     */
    protected Object getSession(HttpServletRequest request, SessionEnum key) {
        return request.getSession().getAttribute(key.toString());
    }

    /**
     * 设置Cookie的attr中的值
     *
     * @param request  HttpServletRequest对象
     * @param response HttpServletResponse 对象
     * @param key      枚举类中的值
     * @param value    存储对象
     */
    protected void setCookie(HttpServletRequest request, HttpServletResponse response, CookieEnum key, Object value) {
        request.getSession().setAttribute(key.toString(), value);
        Cookie cookie = new Cookie(key.name(), (String) value);
        cookie.setPath("/");
        cookie.setValue((String) value);
        response.addCookie(cookie);
    }

    /**
     * 获取项目路径
     *
     * @param request HttpServletRequest对象
     * @return 返回项目路径，例如：http://www.ip.com/projectName 后面没有反斜杠，后面接地址或参数必须加/
     */
    protected String getUrl(HttpServletRequest request) {
        String path = request.getContextPath();
        String basePath = request.getScheme() + "://" + request.getServerName();
        if (request.getServerPort() == 80) {
            basePath += path;
        } else {
            basePath += ":" + request.getServerPort() + path;
        }
        return basePath;
    }

    /**
     * 获取请求域名，域名不包括http请求协议头
     *
     * @param request HttpServletRequest对象
     * @return 返回域名地址
     */
    protected String getDomain(HttpServletRequest request) {
        String path = request.getContextPath();
        String domain = request.getServerName();
        if (request.getServerPort() == 80) {
            domain += path;
        } else {
            domain += ":" + request.getServerPort() + path;
        }
        return domain;
    }



    /**
     * 根据字段获取请求参数中的整型值
     *
     * @param request   HttpServletRequest对象
     * @param fieldName 字段名称
     * @return 返回请求到的整型值，获取不到返回0，也有例外，具体更具业务来定
     */
    protected int getInt(HttpServletRequest request, String fieldName) {
        if (!StringUtil.isInteger(request.getParameter(fieldName))) {
            return 0;
        }
        return Integer.parseInt(request.getParameter(fieldName));
    }

    /**
     * 根据字段获取请求参数中的整型值
     *
     * @param request      HttpServletRequest对象
     * @param fieldName    字段名称
     * @param defaultValue 默认值
     * @return 返回请求到的整型值，获取不到返回默认值
     */
    protected int getInt(HttpServletRequest request, String fieldName, int defaultValue) {
        if (!StringUtil.isInteger(request.getParameter(fieldName))) {
            return defaultValue;
        }
        return Integer.parseInt(request.getParameter(fieldName));
    }

    /**
     * 通过spring的webapplicationcontext上下文对象读取bean对象
     *
     * @param sc       上下文servletConext对象
     * @param beanName 要读取的bean的名称
     * @return 返回读取的对象，获取不到返回null
     */
    protected Object getBean(ServletContext sc, String beanName) {
        return WebApplicationContextUtils.getWebApplicationContext(sc).getBean(beanName);
    }

    /**
     * 获取当期项目物理路径
     *
     * @param request  HttpServletRequest对象
     * @param filePath 相对文件夹
     * @return 返回当前项目的物理路径
     */
    protected String getRealPath(HttpServletRequest request, String filePath) {
        if (filePath != null) {
            return request.getServletContext().getRealPath(File.separator) + File.separator + filePath;
        }
        return request.getServletContext().getRealPath(File.separator);
    }

//    /**
//     * 读取国际化资源文件
//     *
//     * @param key 资源文件键值
//     * @return 返回键对应的值
//     */
//    protected String getResString(String key) {
//        return Const.RESOURCES.getString(key);
//    }
//
//    /**
//     * 读取国际化资源文件
//     *
//     * @param key      资源文件键值
//     * @param fullStrs 需填充的值
//     * @return 返回键对应的值
//     */
//    protected String getResString(String key, String... fullStrs) {
//        String temp = Const.RESOURCES.getString(key);
//        for (int i = 0; i < fullStrs.length; i++) {
//            temp = temp.replace("{" + i + "}", fullStrs[i]);
//        }
//        return temp;
//    }

    /**
     * 将请求的request的参数重新组装。主要是将空值的替换成null,因为requestMap空值是"",这样处理有利于外部判断,
     * 同时将获取到的值映射到页面上
     *
     * @param request HttpServletRequest对象
     * @return 返回处理过后的数据
     */
    protected Map<String, Object> assemblyRequestMap(HttpServletRequest request) {
        Map<String, Object> params = new HashMap<String, Object>();
        Map<String, String[]> map = request.getParameterMap();
        Iterator<String> key = map.keySet().iterator();
        while (key.hasNext()) {
            String k = (String) key.next();
            String[] value = map.get(k);

            if (value.length == 1) {
                String temp = null;
                if (!StringUtil.isBlank(value[0])) {
                    temp = value[0];
                }
                params.put(k, temp);
                request.setAttribute(k, temp);
            } else if (value.length == 0) {
                params.put(k, null);
                request.setAttribute(k, null);
            } else if (value.length > 1) {
                params.put(k, value);
                request.setAttribute(k, value);
            }
        }
        return params;
    }

}
