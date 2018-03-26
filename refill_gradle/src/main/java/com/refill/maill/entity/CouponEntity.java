package com.refill.maill.entity;

import com.refill.base.entity.BaseEntity;

import java.util.Date;

/**
 * @author Peter Guo
 * @version 0.1
 * @ClassName CouponEntity
 * @Description 优惠券实体类
 * @date 17/05/02
 */
public class CouponEntity extends BaseEntity {

    /**
     * 商品优惠券自增长ID
     */
    private int couponId;

    /**
     * 商品优惠券标题
     */
    private String couponTitle;

    /**
     * 商品优惠券折扣金额
     */
    private Double couponPrice;

    /**
     * 优惠券使用类型: 0 全平台 1 仅限商户 2 仅限平台
     */
    private int couponUseType;

    /**
     * 商品优惠券折扣类型0.金额折扣,如优惠100元 1.打折折扣,如打7折
     */
    private int couponPriceType;

    /**
     * 商品券有效日期类型:1.以发放时间开始,指定天数内失效2. 指定时间段
     */
    private int couponDateType;

    /**
     * 有效天数
     */
    private int couponValidDay;


    /**
     * 开始日期
     */
    private Date couponStartDate;


    /**
     * 结束日期
     */
    private Date couponEndDate;


    /**
     * 创建时间
     */
    private Date couponCreateDate;


    /**
     * 创建人ID
     */
    private int couponCreatePeopleId;

    /**
     * 最后更新时间
     */
    private Date couponUpdateDate;

    /**
     * 最后更新人ID
     */
    private int couponUpdatePeopleId;

    /**
     * 模块ID
     */
    private int couponModuleId;


    /**
     * 获取 商品优惠券自增长ID
     *
     * @return couponId
     */
    public int getCouponId() {
        return couponId;
    }

    /**
     * 设置 商品优惠券自增长ID
     *
     * @param couponId 商品优惠券自增长ID
     */
    public void setCouponId(int couponId) {
        this.couponId = couponId;
    }

    /**
     * 获取 商品优惠券标题
     *
     * @return couponTitle
     */
    public String getCouponTitle() {
        return couponTitle;
    }

    /**
     * 设置 商品优惠券标题
     *
     * @param couponTitle 商品优惠券标题
     */
    public void setCouponTitle(String couponTitle) {
        this.couponTitle = couponTitle;
    }

    /**
     * 获取 商品优惠券折扣金额
     *
     * @return couponPrice
     */
    public Double getCouponPrice() {
        return couponPrice;
    }

    /**
     * 设置 商品优惠券折扣金额
     *
     * @param couponPrice 商品优惠券折扣金额
     */
    public void setCouponPrice(Double couponPrice) {
        this.couponPrice = couponPrice;
    }

    /**
     * 获取 优惠券使用类型: 0 全平台 1 仅限商户 2 仅限平台
     *
     * @return couponUseType
     */
    public int getCouponUseType() {
        return couponUseType;
    }

    /**
     * 设置 优惠券使用类型: 0 全平台 1 仅限商户 2 仅限平台
     *
     * @param couponUseType 优惠券使用类型: 0 全平台 1 仅限商户 2 仅限平台
     */
    public void setCouponUseType(int couponUseType) {
        this.couponUseType = couponUseType;
    }

    /**
     * 获取 商品优惠券折扣类型0.金额折扣,如优惠100元 1.打折折扣,如打7折
     *
     * @return couponPriceType
     */
    public int getCouponPriceType() {
        return couponPriceType;
    }

    /**
     * 设置 商品优惠券折扣类型0.金额折扣,如优惠100元 1.打折折扣,如打7折
     *
     * @param couponPriceType 商品优惠券折扣类型0.金额折扣,如优惠100元 1.打折折扣,如打7折
     */
    public void setCouponPriceType(int couponPriceType) {
        this.couponPriceType = couponPriceType;
    }

    /**
     * 获取 商品券有效日期类型:1.以发放时间开始,指定天数内失效2. 指定时间段
     *
     * @return couponDateType
     */
    public int getCouponDateType() {
        return couponDateType;
    }

    /**
     * 设置 商品券有效日期类型:1.以发放时间开始,指定天数内失效2. 指定时间段
     *
     * @param couponDateType 商品券有效日期类型:1.以发放时间开始,指定天数内失效2. 指定时间段
     */
    public void setCouponDateType(int couponDateType) {
        this.couponDateType = couponDateType;
    }

    /**
     * 获取 有效天数
     *
     * @return couponValidDay
     */
    public int getCouponValidDay() {
        return couponValidDay;
    }

    /**
     * 设置 有效天数
     *
     * @param couponValidDay 有效天数
     */
    public void setCouponValidDay(int couponValidDay) {
        this.couponValidDay = couponValidDay;
    }

    /**
     * 获取 开始日期
     *
     * @return couponStartDate
     */
    public Date getCouponStartDate() {
        return couponStartDate;
    }

    /**
     * 设置 开始日期
     *
     * @param couponStartDate 开始日期
     */
    public void setCouponStartDate(Date couponStartDate) {
        this.couponStartDate = couponStartDate;
    }

    /**
     * 获取 结束日期
     *
     * @return couponEndDate
     */
    public Date getCouponEndDate() {
        return couponEndDate;
    }

    /**
     * 设置 结束日期
     *
     * @param couponEndDate 结束日期
     */
    public void setCouponEndDate(Date couponEndDate) {
        this.couponEndDate = couponEndDate;
    }

    /**
     * 获取 创建时间
     *
     * @return couponCreateDate
     */
    public Date getCouponCreateDate() {
        return couponCreateDate;
    }

    /**
     * 设置 创建时间
     *
     * @param couponCreateDate 创建时间
     */
    public void setCouponCreateDate(Date couponCreateDate) {
        this.couponCreateDate = couponCreateDate;
    }

    /**
     * 获取 创建人ID
     *
     * @return couponCreatePeopleId
     */
    public int getCouponCreatePeopleId() {
        return couponCreatePeopleId;
    }

    /**
     * 设置 创建人ID
     *
     * @param couponCreatePeopleId 创建人ID
     */
    public void setCouponCreatePeopleId(int couponCreatePeopleId) {
        this.couponCreatePeopleId = couponCreatePeopleId;
    }

    /**
     * 获取 最后更新时间
     *
     * @return couponUpdateDate
     */
    public Date getCouponUpdateDate() {
        return couponUpdateDate;
    }

    /**
     * 设置 最后更新时间
     *
     * @param couponUpdateDate 最后更新时间
     */
    public void setCouponUpdateDate(Date couponUpdateDate) {
        this.couponUpdateDate = couponUpdateDate;
    }

    /**
     * 获取 最后更新人ID
     *
     * @return couponUpdatePeopleId
     */
    public int getCouponUpdatePeopleId() {
        return couponUpdatePeopleId;
    }

    /**
     * 设置 最后更新人ID
     *
     * @param couponUpdatePeopleId 最后更新人ID
     */
    public void setCouponUpdatePeopleId(int couponUpdatePeopleId) {
        this.couponUpdatePeopleId = couponUpdatePeopleId;
    }

    /**
     * 获取 模块ID
     *
     * @return couponModuleId
     */
    public int getCouponModuleId() {
        return couponModuleId;
    }

    /**
     * 设置 模块ID
     *
     * @param couponModuleId 模块ID
     */
    public void setCouponModuleId(int couponModuleId) {
        this.couponModuleId = couponModuleId;
    }

}