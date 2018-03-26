package com.refill.shiro.token;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * 自定义员工登陆token
 */
public class MaynUsernamePasswordToken extends UsernamePasswordToken {
    //是否强制登陆
    private boolean isForcibly = false;

    public boolean isForcibly() {
        return isForcibly;
    }

    public void setForcibly(boolean forcibly) {
        isForcibly = forcibly;
    }


    public MaynUsernamePasswordToken(String username, String password,boolean isForcibly) {
        this(username, password != null?password.toCharArray():null, false, null,isForcibly);
    }

    public MaynUsernamePasswordToken(String username, char[] password, boolean rememberMe, String host,boolean isForcibly) {
        super.setRememberMe(false);
        super.setUsername(username);
        super.setPassword(password);
        super.setRememberMe(rememberMe);
        super.setHost(host);
        this.isForcibly =isForcibly;
    }

}
