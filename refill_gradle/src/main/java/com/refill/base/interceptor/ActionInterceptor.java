package com.refill.base.interceptor;

import com.refill.base.constant.Const;
import com.refill.base.constant.e.SessionEnum;
import com.refill.util.StringUtil;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Peter Guo
 * @version 0.1
 * @ClassName ActionInterceptor
 * @Description 所有action的拦截器，主要是设置base与basepath
 * @date 17/04/25
 */
public class ActionInterceptor extends HandlerInterceptorAdapter {

    protected Logger logger = Logger.getLogger(this.getClass());

    private static String BASE = "base";

    private static String BASE_PATH = "basePath";

    private static String MODEL_ID = "modelId";

    public static boolean IS_WINDOWS = false;

    static {
        if (System.getProperty("os.name").toLowerCase().indexOf("windows") > 0) {
            IS_WINDOWS = true;
        }
    }

    /**
     * 所有action的拦截,主要拦截base与basepath
     *
     * @param request  HttpServletRequest对象
     * @param response HttpServletResponse 对象
     * @param handler  处理器
     * @return 处理后返回true
     * @throws Exception 异常处理
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String modelId = request.getParameter(MODEL_ID); // 获取模块编号
        if (StringUtil.isInteger(modelId)) {
            request.getSession().setAttribute(SessionEnum.MODEL_ID_SESSION.toString(), modelId);
            request.getSession().setAttribute(SessionEnum.MODEL_TITLE_SESSION.toString(),
                    request.getParameter("modelTitle"));
        }
        Const.BASE = request.getServletContext().getContextPath();
        Const.PROJECT_PATH = request.getServletContext().getRealPath("/");
        request.setAttribute(BASE, Const.BASE);
        //项目地址
        request.setAttribute(BASE_PATH, request.getScheme() + "://" + request.getServerName()
                + (request.getServerPort() == 80 ? "" : ":" + request.getServerPort()) + Const.BASE);
        return true;
    }

}
