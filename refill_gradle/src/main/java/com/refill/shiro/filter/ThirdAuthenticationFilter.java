package com.refill.shiro.filter;


import com.alibaba.fastjson.JSONObject;
import com.refill.shiro.token.OAuth2Token;
import com.refill.util.Base64Util;
import com.refill.util.StringUtil;
import org.apache.log4j.Logger;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * 第三方接口身份验证过滤器
 */
public class ThirdAuthenticationFilter extends AuthenticatingFilter {
    @Override
    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) throws Exception {
        return null;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        return false;
    }

//    /**
//     * 管理员业务层
//     */
//    @Autowired
//    private IEmployeeBiz employeeBiz;
//
//    /**
//     * 身份令牌
//     */
//    @Autowired
//    private IAccessTokenBiz accessTokenBiz;
//
//    /**
//     * 身份令牌url
//     */
//    @Autowired
//    private IAccessTokenUrlBiz accessTokenUrlBiz;
//
//    /*
//	 * log4j日志记录
//	 */
//    protected final Logger LOG = Logger.getLogger(this.getClass());
//
//    /**
//     * 令牌
//     */
//    public static final String CODE = "code";
//    /**
//     * 时间
//     */
//    public static final String DATE = "date";
//
//    /**
//     * 过滤名称，与spring-shiro中thirdAuthc名字保持一致
//     */
//    public static final String  AUTHC_NAME = "thirdAuthc";
//
//    public static final String DEFAULT_ACCESS_TOKEN = "access_token";
//
//    //授权码
//    private String authcCodeParam = DEFAULT_ACCESS_TOKEN;
//
//    public String getAuthcCodeParam() {
//        return authcCodeParam;
//    }
//
//    public void setAuthcCodeParam(String authcCodeParam) {
//        this.authcCodeParam = authcCodeParam;
//    }
//
//
//    //登陆失败页面
//    public static final String FAILURE_URL = "/login/do";
//
//    /**
//     * 创建token
//     * @param request
//     * @param response
//     * @return
//     * @throws Exception
//     */
//    @Override
//    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) throws Exception {
//        HttpServletRequest httpRequest = (HttpServletRequest) request;
//        String code = httpRequest.getParameter(authcCodeParam);
//        return new OAuth2Token(code);
//    }
//
//    /**
//     * 拒绝访问
//     * @param request
//     * @param response
//     * @return
//     * @throws Exception
//     */
//    @Override
//    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
//        //获取参数
//        String tokenStr = request.getParameter(authcCodeParam);
////        LOG.debug("获取到的code================="+str1);
//        if(StringUtil.isBlank(tokenStr)){
////            LOG.debug("跳转到登陆=================");
//            WebUtils.issueRedirect(request, response, ThirdAuthenticationFilter.FAILURE_URL);
//            return false;
//        }
//        //获取当前请求的地址
//        String postUrl = WebUtils.getPathWithinApplication(WebUtils.toHttp(request));
//
//        LOG.debug("当前访问的地址     "+postUrl);
//
//        //解密
//        String accessToken="";
//        try {
//            accessToken = new String(Base64Util.decode(tokenStr));
//        }finally {
//            if(StringUtil.isBlank(accessToken)){
//                WebUtils.issueRedirect(request, response, ThirdAuthenticationFilter.FAILURE_URL);
//                return false;
//            }
//        }
//
//        Map map = JSONObject.parseObject(accessToken,Map.class);
//        String access_token = (String) map.get(ThirdAuthenticationFilter.CODE);
//        if(StringUtil.isBlank(access_token)){
////            LOG.debug("跳转到登陆=================");
//            WebUtils.issueRedirect(request, response, ThirdAuthenticationFilter.FAILURE_URL);
//            return false;
//        }
//        //获取当前用户信息
//        EmployeeEntity employee = employeeBiz.getByEmployeeByAccessTokenCode(access_token);
//        if (employee == null) {
//            throw new UnknownAccountException();//没找到帐号
//        }
//
//        AccessTokenEntity accessTokenEntity = accessTokenBiz.getEntityByCode(access_token);
//        if(accessTokenEntity==null){
//            throw new UnknownAccountException();//没找到帐号
//        }
//
//        List<AccessTokenUrlEntity> list = accessTokenUrlBiz.queryListByAccessTokenId(accessTokenEntity.getAccessTokenId(),null,null,true);
//        List<String> urlRuleList = new ArrayList<>();
//        for (AccessTokenUrlEntity accessTokenUrl:list) {
//            urlRuleList.add(accessTokenUrl.getAccessTokenUrlAddress());
//        }
//        if(!checkUrl(postUrl,urlRuleList)){
//            throw new UnknownAccountException();//没找到帐号
//        }
//
//        String oldUrl= WebUtils.getPathWithinApplication(WebUtils.toHttp(request));
//        if(!StringUtil.isBlank(oldUrl)){
//            this.setSuccessUrl(oldUrl);
//        }
//        return true;
//    }
//
//
//    /**
//     * 判断url
//     * @param url 当前请求的url
//     * @param ruleUrls url规则
//     * @return ture 通过验证，false 验证失败
//     */
//    private boolean checkUrl(String url, List<String> ruleUrls){
//        Pattern pattern = Pattern.compile("\\*{1,2}");
//        boolean isUrl = false;
//        for (String ruleUrl:ruleUrls) {
//            for (String str : pattern.split(ruleUrl.trim())) {
//                if(url.trim().contains(str)){
//                    isUrl = true;
//                }else {
//                    isUrl = false;
//                   break;
//                }
//            }
//        }
//        return isUrl;
//    }
}
