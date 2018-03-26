package com.refill.shiro.token;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * 自定义第三方登陆token
 */
public class OAuth2Token implements AuthenticationToken {

    //类似于UsernamePasswordToken和CasToken；用于存储oauth2服务端返回的auth code。
    private String authCode;

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    private String principal;



    @Override
    public Object getPrincipal() {
        return this.authCode;
    }

    @Override
    public Object getCredentials() {
        return this.principal;
    }



    public OAuth2Token(String authCode,String principal) {
        this.authCode = authCode;
        this.principal = principal;
    }
    public OAuth2Token(String authCode) {
        this.authCode = authCode;
    }

    public OAuth2Token() {
    }


}
