package com.refill.base.constant.e;

/**
 * @author Peter Guo
 * @version 0.1
 * @ClassName OrderEnum
 * @Description 排序枚举类接口
 * @date 17/04/25
 */
public enum OrderEnum implements BaseEnum{

    /**
     * 降序
     */
    DESC("desc"),

    /**
     * 升序
     */
    ASC("asc");


    OrderEnum(String attr) {
		this.attr = attr;
    }

    private String attr;

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
