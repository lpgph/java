package com.refill.util;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 通用HTTP请求工具
 *
 * @author Penn Kwok
 * @version 1.0
 * @since version 1.0
 * Date: 4/19/16
 * Time: 15:26 PM
 */
public final class HttpUtil {


    /**
     * 获取客户端Ip
     *
     * @param request
     * @return
     */
    public static String getClientIp(HttpServletRequest request) {
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
        return ip.equals("0:0:0:0:0:0:0:1") ? "127.0.0.1" : ip;
    }

    /**
     * 获取服务器IP
     *
     * @return
     */
    public static String getLocalIP(){
        InetAddress addr;
        String ipAddrStr = "";
        try {
            addr = InetAddress.getLocalHost();
            byte[] ipAddr = addr.getAddress();
            for (int i = 0; i < ipAddr.length; i++) {
                if (i > 0) {
                    ipAddrStr += ".";
                }
                ipAddrStr += ipAddr[i] & 0xFF;
            }
        } catch (UnknownHostException e) {
           return null;
//            e.printStackTrace();
        }
        //System.out.println(ipAddrStr);
        return ipAddrStr;
    }
}
