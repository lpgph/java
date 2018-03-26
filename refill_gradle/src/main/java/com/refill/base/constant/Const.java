package com.refill.base.constant;

import org.springframework.context.ApplicationContext;

import java.util.ResourceBundle;

/**
 * @ClassName Const
 *
 * @Description 基础枚举类
 * @author Peter Guo
 * @date 17/04/25
 * @version 0.1
 *
 */
public final class Const {

	/**
	 * 当前请求路径,BaseFilter赋值
	 */
	public static String BASE_URL;

	/**
	 * 项目名称,BaseFilter赋值
	 */
	public static String BASE;

	/**
	 * 项目物理路径,BaseFilter赋值
	 */
	public static String PROJECT_PATH;

	/**
	 * spring资源文件加载上下文对象
	 */
	public static ApplicationContext CONTEXT;

//	/**
//	 * BaseAction层对应的国际化资源文件
//	 */
//	public final static ResourceBundle RESOURCES = ResourceBundle.getBundle("com.mingsoft.base.action.resources");

	/**
	 * 服务器发布地址,带有http:// 在StrutsFilter类里面设置
	 */
	public static String HOST_URL = "";

	/**
	 * 默认编码格式
	 */
	public final static String UTF8 = "utf-8";

	/**
	 * 当前模块编码
	 */
	public final static String MODEL_CODE = "modelCode";

	/**
	 * 路径分割符
	 */
	public final static String SEPARATOR = "/";

	/**
	 * 分页参数名</br>
	 * 默认页码参数
	 */
	public final static String PAGE_NO = "page";

	/**
	 * 分页参数名</br>
	 * 默认页面显示条数
	 */
	public final static String PAGE_SIZE = "rows";

	/**
	 * 分页参数名</br>
	 * 排序方式
	 */
	public final static String ORDER = "order";

	/**
	 * 分页参数名</br>
	 * 排序字段
	 */
	public final static String SORT = "sort";
	
	
	/**
	 * 各模块的字段信息参数
	 */
	public final static String MODEL_FILED="modelFieldList";

}
