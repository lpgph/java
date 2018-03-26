package com.refill.util;

import java.lang.reflect.Array;
import java.util.*;


/**
 * StringUtil <br/>
 * Create Date: 3/21/18 9:32 AM <br/>
 * 
 * @author Peter Guo
 * @version 0.1
 **/
public final class StringUtil {

    /**
     * 判断对象是否为空, 支持 字符串 数组  集合 map 其他对象
     *
     * @param obj object
     * @return 为空返回true，否则返回false
     */
    public static boolean isBlank(Object obj) {
        /*
         * 先判断是否是字符串 ,是字符串则清空空格
         * 然后通过Objects.hashCode()可以判断是否为null,也可辨出其他对象是为空 比如 map
         * 然后判断是否数组或集合
         * */
        //如果是字符串
        if (obj instanceof String) obj = ((String) obj).trim();
        if(Objects.hashCode(obj) == 0) return true;
        //判断是否是数组
        if (obj.getClass().isArray() && Array.getLength(obj) > 0) {
            return false;
        }
        //判断是否是集合
        return !(obj instanceof Iterable) || !((Iterable) obj).iterator().hasNext();
    }


}
