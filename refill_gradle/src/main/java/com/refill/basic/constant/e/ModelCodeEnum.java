package com.refill.basic.constant.e;

import com.refill.base.constant.e.BaseEnum;

/**
 * @ClassName: 模块编号接口
 * @Description:
 *  * 模块编号说明：八位整型数据<br/>
 * 项目编号(2位)+模块编号(2位)+功能编号(2位)+子功能编号(2位)<br/>
 * 如：01(微信项目编号)01(微页面模块编号)01(模版管理编号)01(添加模版)<br/>
 * 若为：01010100则代表整个模块管理功能模块<br/>
 * @author: Peter Guo
 * @date: 17/04/25
 * @version: 0.1
 *
 */
public enum ModelCodeEnum implements BaseEnum {
	/**
	 * 角色模块
	 */
	ROLE("00020200");

	ModelCodeEnum(String code) {
		this.code = code;
	}

	private String code;

	public int toInt() {
		// TODO Auto-generated method stub
		return Integer.parseInt(code);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return code;
	}

}
