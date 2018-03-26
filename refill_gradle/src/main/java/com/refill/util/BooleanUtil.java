package com.refill.util;


/**
 * ERP全局BBoolean与数字转换
 */
public final class BooleanUtil{

	/**
	 * 将数字转换成boolean类型  为0是false 为1为true
	 * @param integer
	 * @return
     */
	public static boolean parseBoolean(int integer){
		return integer !=0;
	}

	/**
	 * 将boolean转换成int类型 1为true 0为false
	 * @param bol
	 * @return
     */
	public static int valueOf(boolean bol){
		return bol?1:0;
	}

}