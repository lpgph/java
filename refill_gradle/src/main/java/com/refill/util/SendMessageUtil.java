package com.refill.util;

import com.refill.base.constant.Const;
import com.refill.util.proxy.Proxy;
import com.refill.util.proxy.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 【短信发送实体类】
 * 
 * @author 史爱华
 * @version 版本号：【100-000-000】<br/>
 *          创建日期：2016年04月11日 <br/>
 *          历史修订：<br/>
 */
public class SendMessageUtil {

	final static Logger logger = LoggerFactory.getLogger(SendMessageUtil.class);

	
	/**
	 * 发送短信，给指定的手机
	 * @param phone 手机号码
	 * @param content 短信内容
	 * @return 是否发送成功
	 */
	public static boolean sendMessage(String phone,String content) {
		String sendUrl = " http://www.jc-chn.cn/smsSend.do?username={id}&password={pwd}&mobile={phone}&content={content}";
		sendUrl = sendUrl.replace("{id}", "nmgd");
		sendUrl = sendUrl.replace("{pwd}", "c117fd00c3a3bbce0025e980f9580f95");
		sendUrl = sendUrl.replace("{phone}", phone);
		sendUrl = sendUrl.replace("{content}", content); // 模块内容中的{content}替换为发送内容＋接口签名
		logger.debug(sendUrl);
		Result out = Proxy.get(sendUrl, null, null, Const.UTF8);
		if(out==null){
			return false;
		}
		return out.getContent().length()>0;
	}

}
