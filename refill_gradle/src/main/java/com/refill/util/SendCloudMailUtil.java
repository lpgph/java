package com.refill.util;

import com.alibaba.fastjson.JSONArray;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.BasicHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * sendcloud邮件工具类
 * 
 * @author 杨鹏(qq:2597079682)
 * @version 
 * 版本号：100-000-000<br/>
 * 创建日期：2016年1月18日 下午4:21:35<br/>
 * 历史修订：<br/>
 */
public class SendCloudMailUtil {
	
	final static Logger logger = LoggerFactory.getLogger(SendCloudMailUtil.class);
	
	/**
	 * 判断邮件是否发送成功
	 */
	private static String SUCCESS = "success";
	/**
	 * 判断邮件是否发送成功
	 */
	private static boolean isSuccess = false;
	
	
	/**
	 * 发送邮件验证码
	 * @param content 内容
	 * @param toUser 接收用户
	 * @return true发送成功 false 发送失败
	 * 使用方法：SendCloudMailUtil.sendErpNotifyMail("test", "dadao@iningmei.com");
	 */
	public static boolean sendErpNotifyMail(String content, String toUser) {
		//在内容中加上程序所在服务器和版本号
		String systemMark = PropertiesUtil.getConfig("systemMark", "config.properties");
		content = content + "<br>" + systemMark;
		if(systemMark.contains("DEV") || systemMark.contains("PRD") || systemMark.contains("MES")){
			List<String> code = new ArrayList<String> ();
			code.add(content);
			Map<String,List<String>> sub = new HashMap<String,List<String>>();
			sub.put("%code%",code);
			return SendCloudMailUtil.sendTemplate("mayn20151218","4W7EqnLAfexDJEg2", "MAYN_ERP_EXCEPTION","service@www.mayn.com.cn","宁美国度", toUser,sub);
		}
		else{
			logger.info("开发系统不做错误信息邮件发送" + systemMark);
			return false;
		}
		
	}
	
	
	/**
	 * 发送普通的文本或和html邮件
	 * @param apiUser 是调用接口发信时的帐号	账号一致为  mayn20151218
	 * @param apiKey 是调用接口发信时的密码		密码一致为 4W7EqnLAfexDJEg2
	 * @param from 发件人邮箱地址	一致为service@www.mayn.com.cn	宁美国度在sendcloud上的邮箱地址
	 * @param fromname 发件人姓名
	 * @param to 收件人地址
	 * @param subject 邮件标题.发送普通邮件时 不能为空
	 * @param html 邮件的内容.不能为空,可以是文本格式或者HTML格式
	 * @return true发送成功 false发送失败 失败原因可能有：1.发信的账号密码错误，2.客户提供的邮箱无效或不存在，3其他...
	 */
	public synchronized static boolean sendCommon(String apiUser, String apiKey, String from,String fromname, String to, String subject,
			String html){
		
		//sendcloud接口地址
		final String url = "http://sendcloud.sohu.com/webapi/mail.send.json";
		//将要访问的url字符串放入HttpPost中
		HttpPost httpPost = new HttpPost(url);
		
		//实例HttpClient 并执行带有HttpPost的方法,返回HttpResponse 响应，再进行操作
		HttpClientConnectionManager httpClientConnectionManager = new BasicHttpClientConnectionManager();
		HttpClient httpClient = HttpClientBuilder.create().setConnectionManager(httpClientConnectionManager).build();
		
		//向HttpPost中加入参数
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		// 不同于登录SendCloud站点的帐号，您需要登录后台创建发信子帐号，使用子帐号和密码才可以进行邮件的发送。
		params.add(new BasicNameValuePair("api_user", apiUser));//apiUser 是调用接口发信时的帐号	账号一致为  mayn20151218
		params.add(new BasicNameValuePair("api_key", apiKey));//apiKey 是调用接口发信时的密码		密码一致为 4W7EqnLAfexDJEg2
		params.add(new BasicNameValuePair("from", from));//from 发件人邮箱地址	一致为service@www.mayn.com.cn	宁美国度在sendcloud上的邮箱地址
		params.add(new BasicNameValuePair("fromname", fromname));//fromname 发件人姓名
		params.add(new BasicNameValuePair("to", to));//to 收件人地址
		params.add(new BasicNameValuePair("subject", subject));//subject 邮件标题. 发送普通邮件时不能为空
		params.add(new BasicNameValuePair("html", html));//html 邮件的内容.不能为空,可以是文本格式或者HTML格式
		params.add(new BasicNameValuePair("resp_email_id", "true"));//是否返回 emailId(emailId是 SendCloud 投递一封邮件, 返回的邮件编号, 可以对应到某一次请求的某一个收件人),有多个收件人时, 会返回 emailId 的列表

		//进行转码
		try {
			httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
			HttpResponse response = httpClient.execute(httpPost);
			// 处理响应
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				// 正常返回, 解析返回数据
				String entityUtils = EntityUtils.toString(response.getEntity());
				logger.debug(entityUtils);
				
				Map<String,Object> map= JSONArray.parseObject(entityUtils);
				logger.debug(String.valueOf(map.get("message")));
				if( !StringUtil.isBlank(map.get("message")) && map.get("message").equals(SUCCESS) ){
					isSuccess = true;
				}
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			httpPost.releaseConnection();
		}
		return isSuccess;
	}
	
	
	/**
	 * 发送带附件的普通文本或和html邮件
	 * @param apiUser 是调用接口发信时的帐号	账号一致为  mayn20151218
	 * @param apiKey 是调用接口发信时的密码		密码一致为 4W7EqnLAfexDJEg2
	 * @param from 发件人邮箱地址	一致为service@www.mayn.com.cn	宁美国度在sendcloud上的邮箱地址
	 * @param fromname 发件人姓名
	 * @param to 收件人地址 
	 * @param subject 邮件标题. 发送普通邮件时不能为空
	 * @param html 邮件的内容.不能为空,可以是文本格式或者HTML格式
	 * @param attachmentUrl 附件所在的url地址
	 * @return true发送成功 false发送失败 失败原因可能有：1.发信的账号密码错误，2.客户提供的邮箱无效或不存在，3其他...
	 */
	public synchronized static boolean sendCommonWithAttachment(String apiUser, String apiKey, String from, String fromname, String to, String subject,
			String html ,String attachmentUrl){
		//sendcloud接口地址
		final String url = "http://sendcloud.sohu.com/webapi/mail.send.json";

		//将要访问的url字符串放入HttpPost中
		HttpPost httpPost = new HttpPost(url);
		//实例HttpClient 并执行带有HttpPost的方法,返回HttpResponse 响应，再进行操作
		HttpClientConnectionManager httpClientConnectionManager = new BasicHttpClientConnectionManager();
		HttpClient httpClient = HttpClientBuilder.create().setConnectionManager(httpClientConnectionManager).build();
		
		MultipartEntityBuilder builder = MultipartEntityBuilder.create();
		builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);// 设置游览器兼容模式
		// 涉及到附件上传, 需要使用 MultipartEntity
		builder.addTextBody("api_user",apiUser, ContentType.TEXT_PLAIN.withCharset("UTF-8"));//apiUser 是调用接口发信时的帐号	账号一致为  mayn20151218
		builder.addTextBody("api_key",apiKey, ContentType.TEXT_PLAIN.withCharset("UTF-8"));//apiKey 是调用接口发信时的密码		密码一致为 4W7EqnLAfexDJEg2
		builder.addTextBody("to",to, ContentType.TEXT_PLAIN.withCharset("UTF-8"));//to 收件人地址
		builder.addTextBody("from",from, ContentType.TEXT_PLAIN.withCharset("UTF-8"));//from 发件人邮箱地址	一致为service@www.mayn.com.cn	宁美国度在sendcloud上的邮箱地址
		builder.addTextBody("fromname",fromname, ContentType.TEXT_PLAIN.withCharset("UTF-8"));//fromname 发件人姓名
		builder.addTextBody("subject",subject, ContentType.TEXT_PLAIN.withCharset("UTF-8"));//subject 邮件标题. 发送普通邮件时不能为空
		builder.addTextBody("html", html, ContentType.TEXT_PLAIN.withCharset("UTF-8"));//html 邮件的内容.不能为空,可以是文本格式或者HTML格式
		builder.addTextBody("resp_email_id","true");//是否返回 emailId(emailId是 SendCloud 投递一封邮件, 返回的邮件编号, 可以对应到某一次请求的某一个收件人),有多个收件人时, 会返回 emailId 的列表
		// 添加附件
 		File file = new File(attachmentUrl);
 		FileBody fileBody = new FileBody(file, ContentType.TEXT_PLAIN.withCharset("UTF-8"));
		builder.addPart("files",fileBody );//files:邮件附件. 发送附件时, 必须使用 multipart/form-data 进行 post 提交 (表单提交)
		
		try {
			//实例HttpClient 并执行带有HttpPost的方法,返回HttpResponse 响应，再进行操作
			httpPost.setEntity(builder.build());
			HttpResponse response = httpClient.execute(httpPost);
			
			// 处理响应
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				// 正常返回, 解析返回数据
				String entityUtils = EntityUtils.toString(response.getEntity());
				logger.debug(entityUtils);
				
				Map<String,Object> map= JSONArray.parseObject(entityUtils);
				logger.debug(String.valueOf(map.get("message")));
				if( !StringUtil.isBlank(map.get("message")) && map.get("message").equals(SUCCESS) ){
					isSuccess = true;
				}
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}finally {
			httpPost.releaseConnection();
		}
		return isSuccess;
	}

	
	/**
	 * 发送模板邮件,如验证码通用模板,...是发送的文件格式
	 * @param apiUser 是调用接口发信时的帐号	账号一致为  mayn20151218
	 * @param apiKey 是调用接口发信时的密码		密码一致为 4W7EqnLAfexDJEg2
	 * @param invokeName SendCloud上自定义邮件模板的调用名称
	 * @param from 发件人邮箱地址	一致为service@www.mayn.com.cn	宁美国度在sendcloud上的邮箱地址
	 * @param fromname 发件人姓名
	 * @param to 收件人地址
	 * @param sub 是substitution_vars模板替换变量中的自定义变量
	 * @return true发送成功 false发送失败 失败原因可能有：1.发信的账号密码错误，2.客户提供的邮箱无效或不存在，3其他...
	 */
	public synchronized static boolean sendTemplate(String apiUser, String apiKey, String invokeName, String from, String fromname, String to, 
			 Map<String,List<String>> sub) {

		//sendcloud接口地址
		final String url = "http://sendcloud.sohu.com/webapi/mail.send_template.json";

		List<String> toList = new ArrayList<String> ();
		toList.add(to);

		//将要访问的url字符串放入HttpPost中
		HttpPost httpPost = new HttpPost(url);
		
		//实例HttpClient 并执行带有HttpPost的方法,返回HttpResponse 响应，再进行操作
		HttpClientConnectionManager httpClientConnectionManager = new BasicHttpClientConnectionManager();
		HttpClient httpClient = HttpClientBuilder.create().setConnectionManager(httpClientConnectionManager).build();

		Map<String,Object> substitution_vars = new HashMap<String,Object>();
		substitution_vars.put("to",toList);
		substitution_vars.put("sub",JSONArray.toJSON(sub));
		
		//向HttpPost中加入参数
 		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("api_user", apiUser));//apiUser 是调用接口发信时的帐号	账号一致为  mayn20151218
		params.add(new BasicNameValuePair("api_key", apiKey));//apiKey 是调用接口发信时的密码		密码一致为 4W7EqnLAfexDJEg2
		params.add(new BasicNameValuePair("substitution_vars", JSONArray.toJSONString(substitution_vars)));//substitution_vars:模板替换变量. 在 use_maillist=false 时使用, 如: {"to": ["ben@ifaxin.com", "joe@ifaxin.com"],"sub":{"%name%": ["Ben", "Joe"],"%money%":[288, 497]}}
		params.add(new BasicNameValuePair("template_invoke_name", invokeName));//invokeName SendCloud上自定义邮件模板的调用名称
		params.add(new BasicNameValuePair("from", from));//from 发件人邮箱地址	一致为service@www.mayn.com.cn	宁美国度在sendcloud上的邮箱地址
		params.add(new BasicNameValuePair("fromname", fromname));//fromname 发件人姓名
		params.add(new BasicNameValuePair("use_maillist", "false"));//参数 to 是否支持地址列表, 默认为 false. 比如: to=users@maillist.sendcloud.org
		params.add(new BasicNameValuePair("resp_email_id", "true"));//是否返回 emailId(emailId是 SendCloud 投递一封邮件, 返回的邮件编号, 可以对应到某一次请求的某一个收件人),有多个收件人时, 会返回 emailId 的列表

		try {
			//进行转码
			httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
			HttpResponse response = httpClient.execute(httpPost);
			// 处理响应
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				// 正常返回, 解析返回数据
				String entityUtils = EntityUtils.toString(response.getEntity());
				logger.debug(entityUtils);
				
				Map<String,Object> map= JSONArray.parseObject(entityUtils);
				logger.debug(String.valueOf(map.get("message")));
				if( !StringUtil.isBlank(map.get("message")) && map.get("message").equals(SUCCESS) ){
					isSuccess = true;
				}
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			httpPost.releaseConnection();
		}
		return isSuccess;
	}
	
	/**
	 * 批量发送模板邮件,免费用户不支持批量发送
	 * @param apiUser 是调用接口发信时的帐号	账号一致为  mayn20151218
	 * @param apiKey 是调用接口发信时的密码		密码一致为 4W7EqnLAfexDJEg2
	 * @param invokeName SendCloud上自定义邮件模板的调用名称
	 * @param from 发件人邮箱地址	一致为service@www.mayn.com.cn	宁美国度在sendcloud上的邮箱地址
	 * @param fromname 发件人姓名
	 * @param to 收件人地址
	 * @return true发送成功 false发送失败 失败原因可能有：1.发信的账号密码错误，2.客户提供的邮箱无效或不存在，3其他...
	 */
	public synchronized static boolean sendTemplateMaillist(String apiUser, String apiKey, String invokeName, String from, String fromname,String to){

		//sendcloud接口地址
		final String url = "http://sendcloud.sohu.com/webapi/mail.send_template.json";

		//将要访问的url字符串放入HttpPost中
		HttpPost httpPost = new HttpPost(url);
		
		//实例HttpClient 并执行带有HttpPost的方法,返回HttpResponse 响应，再进行操作
		HttpClientConnectionManager httpClientConnectionManager = new BasicHttpClientConnectionManager();
		HttpClient httpClient = HttpClientBuilder.create().setConnectionManager(httpClientConnectionManager).build();

		//向HttpPost中加入参数
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("api_user", apiUser));//apiUser 是调用接口发信时的帐号	账号一致为  mayn20151218
		params.add(new BasicNameValuePair("api_key", apiKey));//apiKey 是调用接口发信时的密码		密码一致为 4W7EqnLAfexDJEg2
		params.add(new BasicNameValuePair("to", to));//to 收件人地址
		params.add(new BasicNameValuePair("template_invoke_name", invokeName));//invokeName SendCloud上自定义邮件模板的调用名称
		params.add(new BasicNameValuePair("from", from));//from 发件人邮箱地址	一致为service@www.mayn.com.cn	宁美国度在sendcloud上的邮箱地址
		params.add(new BasicNameValuePair("fromname", fromname));//fromname 发件人姓名
		params.add(new BasicNameValuePair("use_maillist", "true"));//参数 to 是否含有地址列表. 比如: to=ben@ifaxin.com;users@maillist.sendcloud.org
		params.add(new BasicNameValuePair("resp_email_id", "true"));//是否返回 emailId(emailId是 SendCloud 投递一封邮件, 返回的邮件编号, 可以对应到某一次请求的某一个收件人),有多个收件人时, 会返回 emailId 的列表

		try {
			//进行转码
			httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
			HttpResponse response = httpClient.execute(httpPost);
			// 处理响应
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				// 正常返回, 解析返回数据
				String entityUtils = EntityUtils.toString(response.getEntity());
				logger.debug(entityUtils);
				
				Map<String,Object> map= JSONArray.parseObject(entityUtils);
				logger.debug(String.valueOf(map.get("message")));
				if( !StringUtil.isBlank(map.get("message")) && map.get("message").equals(SUCCESS) ){
					isSuccess = true;
				}
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			httpPost.releaseConnection();
		}
		return isSuccess;
	}
	
	/**
	 * 发送带附件的模板邮件
	 * @param apiUser 是调用接口发信时的帐号	账号一致为  mayn20151218
	 * @param apiKey 是调用接口发信时的密码		密码一致为 4W7EqnLAfexDJEg2
	 * @param invokeName SendCloud上自定义邮件模板的调用名称
	 * @param from 发件人邮箱地址	一致为service@www.mayn.com.cn	宁美国度在sendcloud上的邮箱地址
	 * @param fromname 发件人姓名
	 * @param to 收件人地址
	 * @param attachmentUrl	附件所在的url地址
	 * @param sub 是substitution_vars模板替换变量中的自定义变量
	 * @return true发送成功 false发送失败 失败原因可能有：1.发信的账号密码错误，2.客户提供的邮箱无效或不存在，3其他...
	 */
	public synchronized static boolean sendTemplateWithAttachment(String apiUser, String apiKey, String invokeName, String from, String fromname, 
			String to, String attachmentUrl, Map<String,Object> sub){
		
		//sendcloud接口地址
		final String url = "http://sendcloud.sohu.com/webapi/mail.send_template.json";
		
		List<String> toList = new ArrayList<String> ();
		toList.add(to);

		//将要访问的url字符串放入HttpPost中
		HttpPost httpPost = new HttpPost(url);
		
		//实例HttpClient 并执行带有HttpPost的方法,返回HttpResponse 响应，再进行操作
		HttpClientConnectionManager httpClientConnectionManager = new BasicHttpClientConnectionManager();
		HttpClient httpClient = HttpClientBuilder.create().setConnectionManager(httpClientConnectionManager).build();
		
		Map<String,Object> substitution_vars = new HashMap<String,Object>();
		substitution_vars.put("to",toList);
		substitution_vars.put("sub",JSONArray.toJSON(sub));

		// 涉及到附件上传, 需要使用 MultipartEntityBuilder
		MultipartEntityBuilder builder = MultipartEntityBuilder.create();
		builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);// 设置游览器兼容模式
		builder.addTextBody("api_user",apiUser, ContentType.TEXT_PLAIN.withCharset("UTF-8"));//apiUser 是调用接口发信时的帐号	账号一致为  mayn20151218
		builder.addTextBody("api_key",apiKey, ContentType.TEXT_PLAIN.withCharset("UTF-8"));//apiKey 是调用接口发信时的密码		密码一致为 4W7EqnLAfexDJEg2
		builder.addTextBody("substitution_vars",JSONArray.toJSONString(substitution_vars), ContentType.TEXT_PLAIN.withCharset("UTF-8"));//substitution_vars:模板替换变量. 在 use_maillist=false 时使用, 如: {"to": ["ben@ifaxin.com", "joe@ifaxin.com"],"sub":{"%name%": ["Ben", "Joe"],"%money%":[288, 497]}}
		builder.addTextBody("template_invoke_name",invokeName, ContentType.TEXT_PLAIN.withCharset("UTF-8"));//invokeName SendCloud上自定义邮件模板的调用名称
		builder.addTextBody("from",from, ContentType.TEXT_PLAIN.withCharset("UTF-8"));//from 发件人邮箱地址	一致为service@www.mayn.com.cn	宁美国度在sendcloud上的邮箱地址
		builder.addTextBody("fromname",fromname, ContentType.TEXT_PLAIN.withCharset("UTF-8"));//fromname 发件人姓名
		builder.addTextBody("use_maillist","false", ContentType.TEXT_PLAIN.withCharset("UTF-8"));///参数 to 是否支持地址列表, 默认为 false. 比如: to=users@maillist.sendcloud.org
		builder.addTextBody("resp_email_id","true");//是否返回 emailId(emailId是 SendCloud 投递一封邮件, 返回的邮件编号, 可以对应到某一次请求的某一个收件人),有多个收件人时, 会返回 emailId 的列表

		// 添加附件
 		File file = new File(attachmentUrl);
 		FileBody fileBody = new FileBody(file, ContentType.TEXT_PLAIN.withCharset("UTF-8"));
		builder.addPart("files",fileBody );//files 邮件附件. 发送附件时, 必须使用 multipart/form-data 进行 post 提交 (表单提交)

		try {
			httpPost.setEntity(builder.build());
			HttpResponse response = httpClient.execute(httpPost);
			
			// 处理响应
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				// 正常返回, 解析返回数据
				String entityUtils = EntityUtils.toString(response.getEntity());
				logger.debug(entityUtils);
				
				Map<String,Object> map= JSONArray.parseObject(entityUtils);
				logger.debug(String.valueOf(map.get("message")));
				if( !StringUtil.isBlank(map.get("message")) && map.get("message").equals(SUCCESS) ){
					isSuccess = true;
				}
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}finally {
			httpPost.releaseConnection();
		}
		return isSuccess;
	}
	
}