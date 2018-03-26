package com.refill.basic.aop;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Controller;

/**
 * @ClassName: InterceptorAop
 * @Description: 在线客户统计
 * @author: Peter Guo
 * @date: 17/04/25
 * @version: 0.1
 *
 */
@Controller
@Aspect
public class InterceptorAop {
//
//    /**
//     * 使用slf4j管理日志
//     */
////    private Logger logger = LoggerFactory.getLogger(this.getClass());
//	/*
//	 * log4j日志记录
//	 */
//    protected final Logger LOG = Logger.getLogger(this.getClass());
//
//
//    @Pointcut("execution(* *..action.*Action.*(..))")
//    private void pointCut(){}
//
//    /**
//     *  监测action方法运行
//     * @param point
//     * @return
//     */
//    @Around("pointCut()")
//    public Object logAround(ProceedingJoinPoint point) throws Throwable {
//        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//
//        HttpSession session =  request.getSession();
//
//        String requestUrl = request.getRequestURL().toString();
//        //匹配当前请求
////        if(!requestUrl.matches(".*/session/.*")){
//            Map<String, Object> map = new HashMap<>();
//            map.put("sessionId", session.getId());
//            //获取用户session
//            EmployeeEntity employee = (EmployeeEntity) session.getAttribute(SessionEnum.EMPLOYEE_SESSION.toString());
//            if(employee!=null){
//                map.put("peopleCode", employee.getEmployeeCode());
//                map.put("peopleName", employee.getEmployeeName());
//            }
//            map.put("createTime", DateUtil.millisFmtToString(session.getCreationTime(),DateUtil.simple));
//            map.put("peopleIp", HttpUtil.getClientIp(request));
//            map.put("serverIp", HttpUtil.getLocalIP());
//            map.put("updateTime", DateUtil.millisFmtToString(session.getLastAccessedTime(),DateUtil.simple));
//            map.put("requestUrl",requestUrl);
//            //预期过期时间
//            map.put("expireTime", DateUtil.millisFmtToString(session.getLastAccessedTime()+(session.getMaxInactiveInterval()*1000),DateUtil.simple));
//            //添加当前session
//            session.setAttribute(SessionEnum.SESSION_MANAGER.toString(),map);
////        }
//
//        String log = "\n\n";
//
//        log+="sessionID......."+ session.getId()+"\n"
//                +"session创建时间......."+DateUtil.millisFmtToString(session.getCreationTime(),DateUtil.simple)+"\n"
//                +"session上次访问时间......."+DateUtil.millisFmtToString(session.getLastAccessedTime(),DateUtil.simple)+"\n";
//
////        Object employeeObj = session.getAttribute(SessionEnum.EMPLOYEE_SESSION.toString());
//
//        if(employee!=null){
////            EmployeeEntity employee = (EmployeeEntity) employeeObj;
//            log+="当前访客id......."+employee.getEmployeeId()+"\n"
//                    +"当前访客姓名......."+employee.getEmployeeName()+"\n";
//        }
//
//        log+="客户Ip......."+ HttpUtil.getClientIp(request)+"\n"
//                +"请求url......."+request.getRequestURL()+"\n"
//                +"请求方式......."+request.getMethod()+"\n"
//                +"请求参数......."+ JSONObject.toJSONString(request.getParameterMap())+"\n";
//
//        long before = System.currentTimeMillis();
//        log+="开始时间......."+ DateUtil.millisFmtToString(before,DateUtil.millis)+"\n";
//        //返回结果 return的值
//        Object o = point.proceed();
//        long after = System.currentTimeMillis();
//        log+="结束时间......."+DateUtil.millisFmtToString(after,DateUtil.millis)+"\n"
//                +"总耗时......."+(after - before)+"毫秒"+"\n";
//
//        if(StringUtil.isBlank(o)){
//            Object obj= request.getSession().getAttribute(SessionEnum.OUTJSON_SESSION.toString());
//            if(obj!=null){
//                String outJson = obj.toString();
//                //移除session
//                request.getSession().removeAttribute(SessionEnum.OUTJSON_SESSION.toString());
//
//                Map outJsonMap = JSONObject.parseObject(outJson, Map.class);
//                if(outJsonMap!=null){
//                    Object resultObj = outJsonMap.get("result");
//                    if(resultObj!=null){
//                        log+="请求结果(true/false)......."+resultObj.toString()+"\n";
//                        if(!Boolean.parseBoolean(resultObj.toString()))  log+="错误结果......."+outJson+"\n";
//                    }
//                }else{
//                    log+="无法识别是否成功......."+outJson+"\n";
//                }
//            }
//        }else{
//            log+="return结果......."+o.toString()+"\n";
//        }
//        LOG.info(log);
//        return o;
//    }
}
