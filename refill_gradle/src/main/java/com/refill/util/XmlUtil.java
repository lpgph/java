package com.refill.util;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * xml解析工具类
 * 
 * @author 成卫雄(qq:330216230)
 * @version 版本号：100-000-000<br/>
 *          创建日期：2016年1月10日 下午3:24:28<br/>
 *          历史修订：<br/>
 */
public class XmlUtil {

	/**
	 * log4j
	 */
	protected final static Logger LOG = Logger.getLogger(XmlUtil.class);

	/**
	 * 将xml字符串，转换成document对象，便于获取</br>
	 * xml标签中不带属性值</br>
	 * 
	 * @param str
	 *            字符串
	 * @return 转换成功将返回Document，失败返回null
	 */
	public static Document stringToXml(String str) {
		try {
			return DocumentHelper.parseText(str);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 将ml字符串，转换成document对象</br>
	 * 同时根据key获取相应的值</br>
	 * xml标签中不带属性值</br>
	 * 
	 * @param str
	 *            字符串
	 * @param key
	 *            节点名称
	 * @return 转换成功将返回string，失败返回null
	 */
	public static String getString(String str, String key) {
		try {
			Document document = DocumentHelper.parseText(str);
			Element root = document.getRootElement();
			return root.elementTextTrim(key);
		} catch (Exception e) {
			e.printStackTrace();
			LOG.debug("Exception接受的xml值:" + str);
			LOG.debug("Exception读取的key:" + key);
		}
		return null;
	}

	/**
	 * 将字符串，转换成document对象，同时根据key获取相应的值</br>
	 * 当xml有子标签存在时调用</br>
	 * xml标签中不带属性值</br>
	 * 
	 * @param str
	 *            字符串
	 * @param key
	 *            节点名称
	 * @param sonKey
	 *            子节点名称
	 * @return 转换成功将返回string，失败返回null
	 */
	public static String getString(String str, String key, String sonKey) {
		try {
			Document document = DocumentHelper.parseText(str);
			Element root = document.getRootElement();
			for (Iterator<?> i = root.elementIterator(key); i.hasNext();) {
				Element foo = (Element) i.next();
				return foo.elementText(sonKey);
			}
		} catch (Exception e) {
			e.printStackTrace();
			LOG.debug("Exception接受的xml值:" + str);
			LOG.debug("Exception读取的key:" + key);
		}
		return null;
	}

	/**
	 * 解析xml,带自定义属性值
	 * 
	 * @param xml
	 *            接收到的xml
	 * @return 解析过后的map
	 */
	private Map<String, String> getUserInfo(String xml) {
		LOG.debug("云软xml:" + xml);
		Map<String, String> map = new HashMap<String, String>();
		try {
			Document doc = DocumentHelper.parseText(xml);
			Element root = doc.getRootElement();
			
			List<Element> listSystem = root.element("properties").element("system").elements();
			for (Element e : listSystem) {
				map.put(e.attribute("key").getValue(), StringUtil.decodeStringByUTF8(e.attribute("value").getValue()));
			}
			
			List<Element> listSession = root.element("properties").element("system").elements();
			for (Element e : listSession) {
				map.put(e.attribute("key").getValue(), StringUtil.decodeStringByUTF8(e.attribute("value").getValue()));
			}
			
			map.put("message", StringUtil.decodeStringByUTF8(root.elementText("message")));
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}

	/**
	 * 解析打印模版配置文件
	 * 
	 * @param xml
	 *            接收到的xml
	 * @param templateId
	 *            接收到的xml
	 * @return 解析过后的map
	 */
	public static Map<String, String> getTemplateConfigByTemplateId(String xml, String templateId) {
		Map<String, String> map = new HashMap<String, String>();

		try {
			Document doc = DocumentHelper.parseText(xml);
			Element root = doc.getRootElement();
			
			List<Element> listSystem = root.element("Templates").elements();
			for (Element e : listSystem) {
				String Id = e.attribute("id").getValue();
				if (Id.equalsIgnoreCase(templateId)) {
					List<Element> list = e.elements();
					for (Element eSon : list) {
						map.put(eSon.getName(), eSon.getStringValue());
					}
				}
			}
			
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return map;
	}

}
