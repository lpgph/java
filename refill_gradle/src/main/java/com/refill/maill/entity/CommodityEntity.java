package com.refill.maill.entity;

import com.refill.basic.entity.InformationEntity;

/**
 * @author Peter Guo
 * @version 0.1
 * @ClassName CommodityEntity
 * @Description 商品信息实体类
 * @date 17/05/02
 */
public class CommodityEntity extends InformationEntity {

    /**
     * 商品价格
     */
    private Double commodityPrice;

    /**
     * 商品数量,有规格时自动计算,无规格时手动设置
     */
    private int commodityQuantity;

    /**
     * 获取 商品价格
     *
     * @return commodityPrice
     */
    public Double getCommodityPrice() {
        return commodityPrice;
    }

    /**
     * 设置 商品价格
     *
     * @param commodityPrice 商品价格
     */
    public void setCommodityPrice(Double commodityPrice) {
        this.commodityPrice = commodityPrice;
    }

    /**
     * 获取 商品数量,有规格时自动计算,无规格时手动设置
     *
     * @return commodityQuantity
     */
    public int getCommodityQuantity() {
        return commodityQuantity;
    }

    /**
     * 设置 商品数量,有规格时自动计算,无规格时手动设置
     *
     * @param commodityQuantity 商品数量,有规格时自动计算,无规格时手动设置
     */
    public void setCommodityQuantity(int commodityQuantity) {
        this.commodityQuantity = commodityQuantity;
    }

}
