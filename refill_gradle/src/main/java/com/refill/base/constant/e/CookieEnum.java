package com.refill.base.constant.e;

/**
 * @ClassName CookieEnum
 *
 * @Description cookie枚举类
 * @author Peter Guo
 * @date 17/04/25
 * @version 0.1
 *
 */
public enum CookieEnum implements BaseEnum {

	/**
	 * 分页cookie
	 */
	PAGENO_COOKIE("pageno_cookie"),

	/**
	 * 上次访问地址
	 */
	BACK_COOKIE("back_cookie");

	/**
	 * 设置CookieConst的常量
	 * 
	 * @param attr
	 *            常量
	 */
	CookieEnum(String attr) {
		this.attr = attr;
	}

	private Object attr;

	/**
	 * 返回该CookieConst常量的字符串表示
	 * 
	 * @return 字符串
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return attr.toString();
	}

	@Override
	public int toInt() {
		// TODO Auto-generated method stub
		return Integer.valueOf(attr.toString());
	}

}
