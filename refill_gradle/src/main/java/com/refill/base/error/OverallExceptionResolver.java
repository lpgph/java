package com.refill.base.error;

import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

public class OverallExceptionResolver extends SimpleMappingExceptionResolver {

//    /**
//     * log4j日志记录
//     */
//    protected static final Logger LOG = Logger.getLogger(OverallExceptionResolver.class);
//
//
//    @Override
//    protected ModelAndView doResolveException(HttpServletRequest request,
//                                              HttpServletResponse response, Object handler, Exception ex) {
//
//        //错误信息
//        String errorLog = "<br>";
//
//        errorLog+="当前服务器IP: "+HttpUtil.getLocalIP()+"<br>";
//
//
//        //读取action异常后提供的信息
//        HttpSession session =  request.getSession();
//
//        errorLog+="sessionID: "+ session.getId()+"<br>"
//                +"session创建时间: "+ DateUtil.millisFmtToString(session.getCreationTime(),DateUtil.simple)+"<br>"
//                +"session上次访问时间: "+DateUtil.millisFmtToString(session.getLastAccessedTime(),DateUtil.simple)+"<br>";
//
//        Object employeeObj = session.getAttribute(SessionEnum.EMPLOYEE_SESSION.toString());
//        if(employeeObj!=null){
//            EmployeeEntity employee = (EmployeeEntity) employeeObj;
//            errorLog+="当前访客id: "+employee.getEmployeeId()+"<br>"
//                    +"当前访客姓名: "+employee.getEmployeeName()+"<br>";
//        }
//
//        errorLog+="客户Ip: "+ HttpUtil.getClientIp(request)+"<br>"
//                +"请求url: "+request.getRequestURL()+"<br>"
//                +"请求方式: "+request.getMethod()+"<br>"
//                +"请求参数: "+ JSONObject.toJSONString(request.getParameterMap())+"<br>";
//
//        errorLog+="异常类型: "+ex.getClass()+"<br>";
//        errorLog+="异常信息: "+ex.getMessage()+"<br>";
//        // 在异常堆栈跟踪中填充。
//        StackTraceElement[] trace = ex.getStackTrace();
//        errorLog+="异常描述: <br>";
//        for (StackTraceElement s : trace) {
//            errorLog += "at " + s + "\r<br>";
//        }
//
//        LOG.debug(errorLog);
//
//        //发送邮件
//        SendCloudMailUtil.sendErpNotifyMail("当前请求的url:"+request.getRequestURL()+"<br>"+
//                "错误信息：<br>"+errorLog,"service@mayn.com.cn");
//
//        // Expose ModelAndView for chosen error view.
//        String viewName = determineViewName(ex, request);
//        if (viewName != null) {
//            // Apply HTTP status code for error views, if specified.
//            // Only apply it if we're processing a top-level request.
//            Integer statusCode = determineStatusCode(request, viewName);
//            if (statusCode != null) {
//                applyStatusCodeIfPossible(request, response, statusCode);
//            }
//            return getModelAndView(viewName, ex, request);
//        }
//        else {
//            return null;
//        }
//    }
}