package com.refill.shiro.filter;

import com.refill.base.constant.e.SessionEnum;
import com.refill.people.biz.IUserBiz;
import com.refill.people.entity.UserEntity;
import com.refill.shiro.redis.RedisSessionDAO;
import com.refill.shiro.token.MaynUsernamePasswordToken;
import com.refill.util.HttpUtil;
import com.refill.util.StringUtil;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 表单登陆过滤器
 */
public class RefillFormAuthenticationFilter extends FormAuthenticationFilter {

    /**
     * session存储管理
     */
    @Autowired
    RedisSessionDAO redisSessionDAO;

    /**
     * 用户业务层接口
     */
    @Autowired
    private IUserBiz userBiz;

    /**
     * slf4j日志记录
     */
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    //是否强制登陆
    private String isForcibly;
    public String getIsForcibly() {
        return isForcibly;
    }

    public void setIsForcibly(String isForcibly) {
        this.isForcibly = isForcibly;
    }

    public boolean isForcibly(ServletRequest request) {
        return WebUtils.isTrue(request, getIsForcibly());
    }


    /**
     * 创建token
     * @param request
     * @param response
     * @return
     */
    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) {
        String username = getUsername(request);
        String password = getPassword(request);
        return new MaynUsernamePasswordToken(username,password,isForcibly(request));
    }


    /**
     * 登陆成功
     * @param token
     * @param subject
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {
        //保存session
        initCustomSessionAttributes(subject,request,token);
        WebUtils.getAndClearSavedRequest(request);
        //重定向到指定首页
        WebUtils.redirectToSavedRequest(request,response,this.getSuccessUrl());
        logger.debug("登陆成功,保存session,并跳转到首页: {}",this.getSuccessUrl());
        return false;
    }

    /**
     * 登陆失败
     * @param token
     * @param e
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
//        this.setFailureAttribute(request, e);
        if(e instanceof UnknownAccountException){
            request.setAttribute("error","账号或密码错误");
        }else if(e instanceof LockedAccountException){
            request.setAttribute("repeatError","重复登陆");
        }
        this.setFailureAttribute(request, e);
        logger.debug("登陆失败,失败具体原因是: {}",e.getMessage());
        return true;
    }

    /**
     * 添加用户session
     * @param token
     */
    protected void initCustomSessionAttributes(Subject subject, ServletRequest request, AuthenticationToken token) {
        //获取账号
        String userAccount = token.getPrincipal().toString();

        UserEntity user = userBiz.getByUserAccount(userAccount);
        //
//        if(!StringUtil.isBlank(user.getUserSessionId())){
//            //检查当前是有人登陆
//            Session sessioned =  redisSessionDAO.getSeesion(user.getUserSessionId());
//            //属于员工自定义的token则检查是否是强制登陆，如果不是强制登陆则提示信息
//            if(token instanceof MaynUsernamePasswordToken){
//                MaynUsernamePasswordToken maynToken = (MaynUsernamePasswordToken) token;
//                if(maynToken.isForcibly()){
//                    //删除已登陆的session
//                    redisSessionDAO.delete(sessioned);
//                }
//            }
//        }
        HttpSession session = ((HttpServletRequest) request).getSession();

//        //保持sessionId
//        user.setUserSessionId(session.getId());
//        //保持客户端ip
//        user.setUserLoginIP(HttpUtil.getClientIp((HttpServletRequest) request));
        //添加用户session
        session.setAttribute(SessionEnum.USER_SESSION.toString(), user);
        //更新员工的登陆状态
        userBiz.updateEntity(user);

        logger.debug("当前登录的帐号为："+userAccount+"SessionId   "+session.getId());
    }


}
