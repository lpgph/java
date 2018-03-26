package com.refill.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * json格式通用处理类
 * 
 * @author 王天培QQ:78750478
 * @version 版本号：100-000-000<br/>
 *          创建日期：2012-03-15<br/>
 *          历史修订：<br/>
 */
public class JsonUtil {

	/**
	 * 将一个JSON数据转换为对应的JAVA对象</br>
	 * JSON数据中键的名称必须和对应JAVA对象中bean字段的名称一致</br>
	 * 
	 * @param <T>
	 *            java对象值
	 * @param jsonString
	 *            JSON 对象字符
	 * @param cls
	 *            对象class *
	 * @return 返回java对象
	 */
	public static <T> T getJsonToObject(String jsonString, Class<T> cls) {
		return JSONObject.parseObject(jsonString, cls);
	}

	/**
	 * 从json HASH表达式中获取一个map，改map支持嵌套功能
	 * 
	 * @param jsonString
	 *            json HASH表达式
	 * @return 返回map数组
	 */
	public static Map<String, Object> getMap4Json(String jsonString) {
		JSONObject jsonObject = JSONObject.parseObject(jsonString);
		Iterator<String> keyIter = jsonObject.keySet().iterator();
		String key;
		Object value;
		Map<String, Object> valueMap = new HashMap<String, Object>();

		while (keyIter.hasNext()) {
			key = (String) keyIter.next();
			value = jsonObject.get(key);
			valueMap.put(key, value);
		}

		return valueMap;
	}

	/**
	 * 从json HASH表达式中获取一个map，改map支持嵌套功能
	 * 
	 * @param jsonString
	 *            json HASH表达式
	 * @return 返回map数组
	 */
	public static Map<String, String> getMapJsonToStr(String jsonString) {
		JSONObject jsonObject = JSONObject.parseObject(jsonString);
		Iterator<String> keyIter = jsonObject.keySet().iterator();
		String key;
		String value;
		Map<String, String> valueMap = new HashMap<String, String>();

		while (keyIter.hasNext()) {
			key = (String) keyIter.next();
			value = jsonObject.get(key).toString();
			valueMap.put(key, value);
		}

		return valueMap;
	}

	/**
	 * 从json数组中得到相应java数组
	 * 
	 * @param jsonString
	 *            json字符串
	 * @return 返回java数组
	 */
	public static Object[] getObjectArray4Json(String jsonString) {
		JSONArray jsonArray = JSONArray.parseArray(jsonString);
		return jsonArray.toArray();
	}

	/**
	 * 将一个对象转换为JSON字符串
	 * 
	 * @param obj
	 *            JAVA Bean对象
	 * @return 返回json字符串
	 */
	public static String getObjectToJsonObject(Object obj) {
		return JSON.toJSON(obj).toString();
	}

	/**
	 * 从json对象集合表达式中得到一个java对象集合</br>
	 * JSON数据中键的名称必须和对应JAVA对象中bean字段的名称一致</br>
	 * 
	 * @param <T>
	 *            对象值
	 * @param jsonString
	 *            json字符串
	 * @param cls
	 *            JAVA Bean对象
	 * @return JAVA bean对象集合list
	 */
	public static <T> List<T> queryJsonToList(String jsonString, Class<T> cls) {
		List<T> list = JSONArray.parseArray(jsonString, cls);
		return list;
	}

	/**
	 * 从json数组中解析出java字符串数组
	 * 
	 * @param jsonString
	 *            json字符串
	 * @return 返回java字符串数字
	 */
	public static String[] getStringArray4Json(String jsonString) {
		JSONArray jsonArray = JSONArray.parseArray(jsonString);
		String[] stringArray = new String[jsonArray.size()];
		for (int i = 0; i < jsonArray.size(); i++) {
			stringArray[i] = jsonArray.get(i).toString();
		}
		return stringArray;
	}
	
	/**
	 * 将订单实体和订单详情json字符串，转换成对应的实体和详情map的集合
	 * 		json字符串类型如："{'stockAllot':{'stockAllotRemark':'测试'},'stockAllotDetailJson':[{'stockAllotDetailOutStockId':'17778','stockAllotDetailMaterialsQuantity':'8888'}]}"
	 * @param jsonString 实体和订单详细json字符串
	 * @param key1 订单实体key值
	 * @param key2 订单详情key值
	 * @param entity 订单实体类，如：Product.class
	 * @param detailEntity 订单详情实体类,如：ProductDetail.class
	 * @return 实体和详情的map集合，通过key1，key2值，分别取实体和详情集合
	 */
	public static Map<String, Object> getEntityAndListForJsonString(String jsonString, String key1, String key2, Class entityCls, Class detailCls){
		// 判断获取到的值是否存在
		if (StringUtil.isBlank(jsonString)) {
			return null;
		}
		
		//将获取到的json字符串转换成map对象
		Map<String, String> map = JsonUtil.getMapJsonToStr(jsonString);
		//获取订单实体的json字符串
		String entityString = map.get(key1);
		//获取订单详情的json字符串
		String detailListString = map.get(key2);
		//判断字符串是否为空
		if(StringUtil.isBlank(entityString) || StringUtil.isBlank(detailListString)){
			return null;
		}
		
		//返回调拨单实体，调拨单详情集合
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put(key1, JsonUtil.getJsonToObject(entityString, entityCls));
		resultMap.put(key2, JsonUtil.queryJsonToList(detailListString,detailCls));
		
		return resultMap;
	}
}