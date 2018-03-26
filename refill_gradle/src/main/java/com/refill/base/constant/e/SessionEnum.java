package com.refill.base.constant.e;

/**
 * @ClassName SessionEnum
 *
 * @Description session枚举类
 * @author Peter Guo
 * @date 17/04/25
 * @version 0.1
 *
 */
public enum SessionEnum implements BaseEnum {

	/**
	 * 模块idsession
	 */
	MODEL_ID_SESSION("model_id_session"),

	/**
	 * 模块名称
	 */
	MODEL_TITLE_SESSION("model_title_session"),

	/**
	 * session管理
	 */
	SESSION_MANAGER("session_manager"),

	/**
	 * 用户sesison
	 */
	USER_SESSION("user_session"),

	/**
	 * 输出流内容
	 */
	OUTJSON_SESSION("outJson_session"),

	/**
	 * 验证码session
	 */
	CODE_SESSION("rand_code");

	/**
	 * 设置session常量
	 * 
	 * @param attr
	 *            常量
	 */
	SessionEnum(String attr) {
		this.attr = attr;
	}

	private String attr;

	/**
	 * 返回SessionConst常量的字符串表示
	 * 
	 * @return 字符串
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return attr;
	}

	@Override
	public int toInt() {
		// TODO Auto-generated method stub
		return Integer.valueOf(attr);
	}

}
