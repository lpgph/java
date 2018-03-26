package com.refill.util;

import com.refill.util.proxy.Proxy;
import com.refill.util.proxy.Result;

import java.util.Map;

/**
 * 代理请求工具类
 * 
 * @author 王天培QQ:78750478
 * @version 版本号：100-000-000<br/>
 *          创建日期：2012-03-15<br/>
 *          历史修订：<br/>
 */
public class ProxyUtil {

	/**
	 * 发送不带头部参数的post请求</br>
	 * 
	 * @param url
	 *            发送请求的地址
	 * @param encode
	 *            请求编码
	 * @param params
	 *            发送参数
	 * @return 返回请求内容
	 */
	public static String postToContent(String url, String encode, Map<String, String> params) {
		// 实例化请求代理
		Proxy proxy = new Proxy();
		// 通过代理发送请求
		Result result = proxy.post(url, null, params, encode);
		if (result == null) {
			return null;
		}
		return result.getContent(encode);
	}

	/**
	 * 获取post请求</br>
	 * 
	 * @param url
	 *            发送请求的地址
	 * @param encode
	 *            请求编码
	 * @param params
	 *            发送参数
	 * @return 返回请求内容
	 */
	public static String getToContent(String url, String encode, Map<String, String> params) {
		// 实例化请求代理
		Proxy proxy = new Proxy();
		// 通过代理发送请求
		Result result = proxy.get(url, null, params, encode);
		if (result == null) {
			return null;
		}
		return result.getContent(encode);
	}

}
