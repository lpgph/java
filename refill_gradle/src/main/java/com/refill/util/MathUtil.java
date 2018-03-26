package com.refill.util;
import java.math.BigDecimal;

/**
 * 计算常量
 * 
 * @author 郭鹏辉
 * @version 版本号：100-000-000<br/>
 *          创建日期：2015年12月10日 下午3:59:45<br/>
 *          历史修订：<br/>
 */
public class MathUtil {

	/**
	 * 零
	 */
	public static final int ZERO = 0;

	/**
	 * 税率 17%
	 */
	public static final double TAX_RATE = 0.17;

	/**
	 * 获取税金 税金=含税金额-含税金额/(1+税率/100)<br>
	 * 税率存在数据库中统一用整数,在调用计算是需要除以100<br>
	 * 
	 * @param price
	 *            需要计算的金额
	 * @param tax
	 *            税率
	 * @return 税金
	 */
	public static double getTaxes(double price, double tax) {
		return price - price / (1 + tax / 100);
	}

	/**
	 * 获取去税后的金额 税金=含税金额-含税金额*(税率/100)<br>
	 * 税率存在数据库中统一用整数,在调用计算是需要除以100<br>
	 * 
	 * @param price
	 *            需要计算的金额
	 * @param tax
	 *            税率
	 * @return 去税后的金额税金
	 */
	public static double getNoTaxes(double price, double tax) {
		return price - getTaxes(price, tax);
	}

	/**
	 * double类型数据进行格式
	 * @param number 数据
	 * @param decimalLength 保留小数位长度
     * @return 格式化后的数据
     */
	public static double formatDouble(double number,int decimalLength){
		//判断number是否为空
		if(StringUtil.isBlank(number)){
			return 0;
		}
		BigDecimal bg = new BigDecimal(number);
		double numberFormat = 0;
		//判断保留的小数位长度是否大于等于0,如果不大于0,取0
		if(!StringUtil.isGreaterEqualZeroInteger(decimalLength)){
			numberFormat = bg.setScale(0, BigDecimal.ROUND_HALF_UP).doubleValue();
		}else{
			numberFormat = bg.setScale(decimalLength, BigDecimal.ROUND_HALF_UP).doubleValue();
		}

		return numberFormat;
	}
}
