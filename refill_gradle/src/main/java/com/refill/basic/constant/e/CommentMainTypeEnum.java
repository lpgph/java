package com.refill.basic.constant.e;

import com.refill.base.constant.e.BaseEnum;

/**
 * @author Peter Guo
 * @version 0.1
 * @ClassName CommentMainTypeEnum
 * @Description 评论主体类别
 * @date 17/05/02
 */
public enum CommentMainTypeEnum implements BaseEnum {

    /**
     * 文本</br>
     * id:1</br>
     * code:文本信息
     */
    INFORMATION(1, "文本信息"),

    /**
     * 商品信息</br>
     * id:2</br>
     * code:商品信息
     */
    COMMODITY(2, "商品信息");

    private String code;

    private int id;

    /**
     * 构造方法
     *
     * @param code
     *            传入的枚举类型
     */
    CommentMainTypeEnum(int id, String code) {
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
