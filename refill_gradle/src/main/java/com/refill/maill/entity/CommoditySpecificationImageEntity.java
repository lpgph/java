package com.refill.maill.entity;
import com.refill.base.entity.BaseEntity;

/**
 * @ClassName CommoditySpecificationImageEntity
 *
 * @Description 商品规格图片实体类
 * @author Peter Guo
 * @date 17/05/02
 * @version 0.1
 *
 */
public class CommoditySpecificationImageEntity extends BaseEntity{

    /**
     * 商品规格图片自增长ID
     */
    private int commoditySpecificationImageId;

    /**
     * 图片路径
     */
    private String commoditySpecificationImagePath;

    /**
     * 商品规格图片类型0.正常1.默认选择
     */
    private int commoditySpecificationImageType;

    /**
     * 商品规格ID,依赖关系
     */
    private int commoditySpecificationImageCommoditySpecificationId;

    /**
     * 获取 商品规格图片自增长ID
     * @return commoditySpecificationImageId
     */
    public int getCommoditySpecificationImageId() {
        return commoditySpecificationImageId;
    }

    /**
     * 设置 商品规格图片自增长ID
     * @param commoditySpecificationImageId 商品规格图片自增长ID
     */
    public void setCommoditySpecificationImageId(int commoditySpecificationImageId) {
        this.commoditySpecificationImageId = commoditySpecificationImageId;
    }
    /**
     * 获取 图片路径
     * @return commoditySpecificationImagePath
     */
    public String getCommoditySpecificationImagePath() {
        return commoditySpecificationImagePath;
    }

    /**
     * 设置 图片路径
     * @param commoditySpecificationImagePath 图片路径
     */
    public void setCommoditySpecificationImagePath(String commoditySpecificationImagePath) {
        this.commoditySpecificationImagePath = commoditySpecificationImagePath;
    }
    /**
     * 获取 商品规格图片类型0.正常1.默认选择
     * @return commoditySpecificationImageType
     */
    public int getCommoditySpecificationImageType() {
        return commoditySpecificationImageType;
    }

    /**
     * 设置 商品规格图片类型0.正常1.默认选择
     * @param commoditySpecificationImageType 商品规格图片类型0.正常1.默认选择
     */
    public void setCommoditySpecificationImageType(int commoditySpecificationImageType) {
        this.commoditySpecificationImageType = commoditySpecificationImageType;
    }
    /**
     * 获取 商品规格ID
     * @return commoditySpecificationImageCommoditySpecificationId
     */
    public int getCommoditySpecificationImageCommoditySpecificationId() {
        return commoditySpecificationImageCommoditySpecificationId;
    }

    /**
     * 设置 商品规格ID
     * @param commoditySpecificationImageCommoditySpecificationId 商品规格ID
     */
    public void setCommoditySpecificationImageCommoditySpecificationId(int commoditySpecificationImageCommoditySpecificationId) {
        this.commoditySpecificationImageCommoditySpecificationId = commoditySpecificationImageCommoditySpecificationId;
    }

}