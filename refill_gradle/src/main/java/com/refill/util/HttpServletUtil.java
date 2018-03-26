package com.refill.util;

import com.alibaba.fastjson.JSONObject;
import com.refill.base.bean.OrderByBean;
import com.refill.base.bean.PageBean;
import com.refill.base.bean.ResultJsonBean;
import com.refill.base.constant.e.BaseEnum;
import com.refill.base.constant.e.SessionEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * @author Peter Guo
 * @version 0.1
 * @ClassName BaseAction
 * @Description 基类控制层
 * @date 17/05/02
 */
public final class HttpServletUtil {

    /**
     * slf4j日志记录
     */
    private static Logger logger = LoggerFactory.getLogger(HttpServletUtil.class);

    /**
     * 获取外部提交的整型值
     *
     * @param param 参数名称
     * @return 返回整型值，没找到返回null
     */
    public static Integer getInt(HttpServletRequest request, String param) {
        String value = request.getParameter(param);
        if (StringUtil.isInteger(value)) {
            return Integer.parseInt(value);
        } else {
            return null;
        }
    }

    /**
     * 获取外部提交的整型值</br>
     * 当获取的参数不存在时可自定义返回值</br>
     *
     * @param request HttpServletRequest对象
     * @param param   参数名称
     * @param def     默认值，如果参数不存在或不符合规则就用默认值替代
     * @return 返回整型值
     */
    public static Integer getInt(HttpServletRequest request, String param, int def) {
        String value = request.getParameter(param);
        if (StringUtil.isInteger(value)) {
            return Integer.parseInt(value);
        } else {
            return def;
        }
    }

    /**
     * 获取外部提交的布尔值
     *
     * @param request HttpServletRequest对象
     * @param param   参数名称
     * @return 返回布尔值，没找到返回null
     */
    public static Boolean getBoolean(HttpServletRequest request, String param) {
        String value = request.getParameter(param);
        if (!StringUtil.isBlank(value)) {
            return Boolean.parseBoolean(value);
        }
        return null;
    }

    /**
     * 获取外部提交的base64机密的整型值
     *
     * @param request HttpServletRequest对象
     * @param param   参数名称
     * @return 返回base64的整型值, 没找到返回null
     */
    public static Integer getIntBase64(HttpServletRequest request, String param) {
        String value = request.getParameter(param);
        if (StringUtil.isInteger(value)) {
            return Integer.parseInt(new String(Base64Util.decode(value)));
        } else {
            return null;
        }
    }

    /**
     * PageBean
     * @param request HttpServletRequest请求
     * @param count 数据总条数
     * @return PageBean
     */
    public static PageBean getPageBean(HttpServletRequest request, Integer count){
        Integer pageNo = getInt(request,"pageNo",0);
        Integer pageSize = getInt(request,"pageSize",10);
        if(!StringUtil.isBlank(count)){
            return  new PageBean(pageNo,pageSize,count);
        }
        return  new PageBean(pageNo,pageSize);
    }

    /**
     * 获取OrderBean集合
     * @param request HttpServletRequest请求
     * @param str 需要获取的集合名称
     * @return list集合
     */
    public static List<OrderByBean> getOrderBeanList(HttpServletRequest request, String str){
        //获取排序字段
        String orderMapStr = request.getParameter(str);
        //设置排序字段
        List<OrderByBean> orderList = null;
        if(!StringUtil.isBlank(orderMapStr)){
            orderList = JSONObject.parseArray(orderMapStr,OrderByBean.class);
        }
        return orderList;
    }



    /**
     * 输出json数据字符串
     *
     * @param response    HttpServletResponse对象
     * @param jsonDataStr 字符串
     */
    private static void outJson(HttpServletResponse response, Object jsonDataStr) {
        try {
            response.setContentType("application/json;charset=utf-8");
            //创建输出流
            PrintWriter out = response.getWriter();
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            request.getSession().setAttribute(SessionEnum.OUTJSON_SESSION.toString(), jsonDataStr);

            //打印数据
            out.print(jsonDataStr);
            out.flush();
            out.close();
            logger.debug(""+jsonDataStr);
        } catch (IOException e) {
            logger.error(""+e);
        }
    }

    /**
     * 输出json数据
     *
     * @param response HttpServletResponse对象
     * @param code     模块编号<br/>
     * @param flag     成功状态,true:成功、false:失败
     * @param msg      提示信息
     * @param data     数据
     */
    public static void outJson(HttpServletResponse response, BaseEnum code, Object data, boolean flag, String msg) {
        outJson(response, JSONObject.toJSON(new ResultJsonBean(code,data,flag,msg)));
    }


    /**
     * 输出json数据
     * @param response HttpServletResponse对象
     * @param resultJsonBean resultJsonBean对象
     */
    public static void outJson(HttpServletResponse response, ResultJsonBean resultJsonBean) {
        outJson(response, JSONObject.toJSON(resultJsonBean));
    }

    /**
     * 输出json数据
     *
     * @param response HttpServletResponse对象
     * @param code     模块编号
     * @param flag     成功状态,true:成功、false:失败
     * @param data      提示信息
     */
    public static void outJson(HttpServletResponse response, BaseEnum code, Object data, boolean flag) {
        outJson(response, code, data,flag);
    }


    /**
     * 输出json数据
     *
     * @param response HttpServletResponse对象
     * @param code     模块编号
     * @param flag     成功状态,true:成功、false:失败
     * @param msg      提示信息
     */
    public static void outJson(HttpServletResponse response, BaseEnum code, boolean flag, String msg) {
        outJson(response, code, null,flag, msg);
    }


    /**
     * 输出json数据
     *
     * @param response HttpServletResponse对象
     * @param code     模块编号<br/>
     * @param flag     成功状态,true:成功、false:失败
     */
    public static void outJson(HttpServletResponse response, BaseEnum code, boolean flag) {
        outJson(response, code, null, flag, null);
    }



    /**
     * 获取session的值
     *
     * @param request
     *            HttpServletRequest对象
     * @param key
     *            枚举类中的值
     * @return session中获取的对象
     */
    public static Object getSession(HttpServletRequest request, BaseEnum key) {
        return request.getSession().getAttribute(key.toString());
    }

    /**
     * 设置session的值
     *
     * @param request
     *            HttpServletRequest对象
     * @param key
     *            枚举类中的值
     */
    public static void setSession(HttpServletRequest request, BaseEnum key, Object value) {
        request.getSession().setAttribute(key.toString(), value);
    }

    /**
     * 移除session的值
     *
     * @param request
     *            HttpServletRequest对象
     * @param key
     *            枚举类中的值
     */
    public static void removeSession(HttpServletRequest request, BaseEnum key) {
        request.getSession().removeAttribute(key.toString());
    }

}
