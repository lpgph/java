package com.refill.basic.constant.e;

import com.refill.base.constant.e.BaseEnum;

/**
 * @ClassName: MailEnum
 * @Description: 邮箱枚举类
 * @author: Peter Guo
 * @date: 17/04/25
 * @version: 0.1
 *
 */
public enum MailEnum implements BaseEnum {
	/**
	 * 文本</br>
	 * id:1</br>
	 * code:文本模版
	 */
	TEXT(1, "文本模版"),

	/**
	 * html模版</br>
	 * id:2</br>
	 * code:html模版
	 */
	HTML(2, "html模版");

	private String code;

	private int id;

	/**
	 * 构造方法
	 * 
	 * @param code
	 *            传入的枚举类型
	 */
	MailEnum(int id, String code) {
		this.code = code;
	}

	@Override
	public int toInt() {
		// TODO Auto-generated method stub
		return this.id;
	}

	public String toString() {
		return this.code;
	}

}
