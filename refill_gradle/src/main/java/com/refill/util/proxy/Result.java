package com.refill.util.proxy;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.zip.GZIPInputStream;

/**
 * 请求返回结果封装类
 * 
 * @author killfen
 * @version 1.0.0
 */
public class Result {
	/**
	 * 返回的cookie值
	 */
	private String cookie;

	/**
	 * 请求网页返回状态代码如果是200，则请求页面成功。
	 */
	private int statusCode;

	/**
	 * 获取返回的头部信息
	 */
	private HashMap<String, String> headerAll;

	/**
	 * 请求返回的实体内容
	 */
	private HttpEntity httpEntity;

	/**
	 * 读取返回的cookie值
	 * 
	 * @return 字符串
	 */
	public String getCookie() {
		return cookie;
	}

	/**
	 * 网站的访问状态
	 * 
	 * @return 200为访问页面成功。详细请查阅http协议的材料
	 */
	public int getStatusCode() {
		return statusCode;
	}

	/**
	 * 获取返回的头部信息
	 * 
	 * @return 头部信息集合
	 */
	public HashMap<String, String> getHeaders() {
		return headerAll;
	}

	public void setHeaders(Header[] headers) {
		headerAll = new HashMap<String, String>();
		for (int i = 0; i < headers.length; i++) {
			Header header = headers[i];
			headerAll.put(header.getName(), header.getValue());
		}
	}

	public HttpEntity getHttpEntity() {
		return httpEntity;
	}

	public void setHttpEntity(HttpEntity httpEntity) {
		this.httpEntity = httpEntity;
	}

	/**
	 * 返回页面内容
	 * 
	 * @param charset
	 *            页面编码
	 * @return 字符串。如果异常返回null
	 */
	public String getContent(String charset) {
		try {
			return EntityUtils.toString(httpEntity, charset);
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 返回页面内容
	 * 
	 * @return 字符串。如果异常返回null
	 */
	public String getContent() {
		try {
			return EntityUtils.toString(httpEntity);
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 如果页面是通过gzip压缩过的。可以使用该方法进行解压
	 * 
	 * @param charSet
	 *            字符编码
	 */
	public String getContentForGzip(String charSet) {
		if (httpEntity.getContentEncoding().getValue().indexOf("gzip") > -1) {
			try {
				GZIPInputStream gzipis = new GZIPInputStream(httpEntity.getContent());

				InputStreamReader isr = new InputStreamReader(gzipis, charSet); // 设置读取流的编码格式，自定义编码
				java.io.BufferedReader br = new java.io.BufferedReader(isr);
				String tempbf;
				StringBuffer sb = new StringBuffer();
				while ((tempbf = br.readLine()) != null) {
					sb.append(tempbf);
					sb.append("\r\n");
				}
				gzipis.close();
				isr.close();
				return sb.toString();
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public void setCookie(String cookie) {
		this.cookie = cookie;
	}

	public void setStatusCode(int statusCode2) {
		this.statusCode = statusCode2;
	}
}
