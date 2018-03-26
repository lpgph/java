package com.refill.maill.entity;
import com.refill.base.entity.BaseEntity;

/**
 * @ClassName CommoditySpecificationEntity
 *
 * @Description 商品规格实体类
 * @author Peter Guo
 * @date 17/05/02
 * @version 0.1
 *
 */
public class CommoditySpecificationEntity extends BaseEntity{

    /**
     * 自增长ID
     */
    private int commoditySpecificationId;


    /**
     * 规格显示名称
     */
    private String commoditySpecificationTitle;


    /**
     * 所属商品ID,依赖关系
     */
    private int commoditySpecificationCommodityId;

    /**
     * 缩略图路径
     */
    private String commoditySpecificationIconPath;


    /**
     * 商品规格类型0.正常1.默认选择
     */
    private int commoditySpecificationType;


    /**
     * 商品价格
     */
    private Double commoditySpecificationPrice;


    /**
     * 数量
     */
    private int commoditySpecificationQuantity;



    /**
     * 获取 自增长ID
     * @return commoditySpecificationId
     */
    public int getCommoditySpecificationId() {
        return commoditySpecificationId;
    }

    /**
     * 设置 自增长ID
     * @param commoditySpecificationId 自增长ID
     */
    public void setCommoditySpecificationId(int commoditySpecificationId) {
        this.commoditySpecificationId = commoditySpecificationId;
    }
    /**
     * 获取 规格显示名称
     * @return commoditySpecificationTitle
     */
    public String getCommoditySpecificationTitle() {
        return commoditySpecificationTitle;
    }

    /**
     * 设置 规格显示名称
     * @param commoditySpecificationTitle 规格显示名称
     */
    public void setCommoditySpecificationTitle(String commoditySpecificationTitle) {
        this.commoditySpecificationTitle = commoditySpecificationTitle;
    }
    /**
     * 获取 所属商品ID
     * @return commoditySpecificationCommodityId
     */
    public int getCommoditySpecificationCommodityId() {
        return commoditySpecificationCommodityId;
    }

    /**
     * 设置 所属商品ID
     * @param commoditySpecificationCommodityId 所属商品ID
     */
    public void setCommoditySpecificationCommodityId(int commoditySpecificationCommodityId) {
        this.commoditySpecificationCommodityId = commoditySpecificationCommodityId;
    }
    /**
     * 获取 缩略图路径
     * @return commoditySpecificationIconPath
     */
    public String getCommoditySpecificationIconPath() {
        return commoditySpecificationIconPath;
    }

    /**
     * 设置 缩略图路径
     * @param commoditySpecificationIconPath 缩略图路径
     */
    public void setCommoditySpecificationIconPath(String commoditySpecificationIconPath) {
        this.commoditySpecificationIconPath = commoditySpecificationIconPath;
    }
    /**
     * 获取 商品规格类型0.正常1.默认选择
     * @return commoditySpecificationType
     */
    public int getCommoditySpecificationType() {
        return commoditySpecificationType;
    }

    /**
     * 设置 商品规格类型0.正常1.默认选择
     * @param commoditySpecificationType 商品规格类型0.正常1.默认选择
     */
    public void setCommoditySpecificationType(int commoditySpecificationType) {
        this.commoditySpecificationType = commoditySpecificationType;
    }
    /**
     * 获取 商品价格
     * @return commoditySpecificationPrice
     */
    public Double getCommoditySpecificationPrice() {
        return commoditySpecificationPrice;
    }

    /**
     * 设置 商品价格
     * @param commoditySpecificationPrice 商品价格
     */
    public void setCommoditySpecificationPrice(Double commoditySpecificationPrice) {
        this.commoditySpecificationPrice = commoditySpecificationPrice;
    }
    /**
     * 获取 数量
     * @return commoditySpecificationQuantity
     */
    public int getCommoditySpecificationQuantity() {
        return commoditySpecificationQuantity;
    }

    /**
     * 设置 数量
     * @param commoditySpecificationQuantity 数量
     */
    public void setCommoditySpecificationQuantity(int commoditySpecificationQuantity) {
        this.commoditySpecificationQuantity = commoditySpecificationQuantity;
    }

}