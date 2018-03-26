package com.refill.shiro.realm;

import com.refill.people.biz.IUserBiz;
import com.refill.people.entity.UserEntity;
import com.refill.shiro.redis.RedisSessionDAO;
import com.refill.shiro.token.MaynUsernamePasswordToken;
import com.refill.util.StringUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 获取subject相关信息
 */
public class AdministratorRealm extends AuthorizingRealm {

    /**
     * 管理员业务层
     */
    @Autowired
    private IUserBiz userBiz;

//    /**
//     * 角色业务层
//     */
//    @Autowired
//    private IModelBiz modelBiz;

    /**
     * session存储管理
     */
    @Autowired
    RedisSessionDAO redisSessionDAO;

    /**
     * slf4j日志记录
     */
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 角色以及权限认证
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
//        LOG.debug("=================== //认证策略");
        //认证策略
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();

//        if(principals!=null){
//
//            //获取账号
//            String administratorAccount = (String) principals.getPrimaryPrincipal();
//            logger.debug("查找用户的角色和权限,当前帐号：{}",administratorAccount);
//            EmployeeEntity employee = employBiz.getByEmployeeCode(administratorAccount);
//            if(employee!=null){
//                //角色集合
//                Set<String> roles = new HashSet<>();
//                //角色的权限集合
//                Set<String> permissions;
//                //将员工的账号作为角色
//                roles.add(String.valueOf(employee.getEmployeeCode()));
//                if (!CollectionUtils.isEmpty(roles)) {
//                    //获取角色集视图
//                    authorizationInfo.setRoles(roles);
//                }
//                //获取模块的url地址
//                List<ModelEntity> modelList = modelBiz.queryModelByRoleId(employee.getEmployeeRoleId());
//                List<String> modelIds = new ArrayList<>();
//                for (ModelEntity model : modelList) {
//                    if(model!=null){
//                        String url = model.getModelUrl();
//                        if (!StringUtil.isBlank(url)) {
//                            modelIds.add(url);
//                        }
//                    }
//                }
//                permissions=new HashSet<>(modelIds);
//
//                if (!CollectionUtils.isEmpty(permissions)) {
//                    //获取权限集视图
//                    authorizationInfo.setStringPermissions(permissions);
//                }
//                logger.debug("===================存储账号角色以及账号的权限");
//            }
//        }
        return authorizationInfo;
    }

    /**
     * 获取身份验证信息
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        //获取账号
        String userAccount = (String) token.getPrincipal();

        Subject subject = SecurityUtils.getSubject();

        logger.debug("当前认证的帐号：{}",userAccount);

        UserEntity user = userBiz.getByUserAccount(userAccount);
        if (user == null) {
            throw new UnknownAccountException();//没找到帐号
        }

//        if(!StringUtil.isBlank(user.getUserSessionId())){
//            //检查当前是有人登陆
//            Session sessioned =  redisSessionDAO.getSeesion(user.getUserSessionId());
//            //属于员工自定义的token则检查是否是强制登陆，如果不是强制登陆则提示信息
//            if(token instanceof MaynUsernamePasswordToken){
//                MaynUsernamePasswordToken maynToken = (MaynUsernamePasswordToken) token;
//                if(sessioned!=null && !maynToken.isForcibly()){
////                    //清空当前登陆缓存
////                    clearCachedAuthorizationInfo(subject.getPrincipals());
//                    throw new LockedAccountException();//提示账号已登陆
//                }
//            }
//        }

        //交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配
        return  new SimpleAuthenticationInfo(
                user.getUserAccount(),//用户名
                user.getUserPassword(),//密码
                getName()  //realm name
        );
    }
}
