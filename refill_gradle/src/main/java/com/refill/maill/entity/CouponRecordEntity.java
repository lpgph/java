package com.refill.maill.entity;
import com.refill.base.entity.BaseEntity;

import java.util.Date;

/**
 * @ClassName CouponRecordEntity
 *
 * @Description 商品优惠券记录实体类
 * @author Peter Guo
 * @date 17/05/02
 * @version 0.1
 *
 */
public class CouponRecordEntity extends BaseEntity{


    /**
     * 商品优惠券记录自增长ID
     */
    private int couponRecordId;

    /**
     * 商品优惠券ID
     */
    private int couponRecordCouponId;

    /**
     * 用户ID
     */
    private int couponRecordUserId;

    /**
     * 优惠券创建时间
     */
    private Date couponRecordCreateDate;

    /**
     * 优惠券有效开始时间
     */
    private Date couponRecordStartDate;

    /**
     * 优惠券有效结束时间
     */
    private Date couponRecordEndDate;

    /**
     * 优惠券状态:0.未使用,1已使用,2已过期
     */
    private int couponRecordStatus;

    /**
     * 获取 商品优惠券记录自增长ID
     * @return couponRecordId
     */
    public int getCouponRecordId() {
        return couponRecordId;
    }

    /**
     * 设置 商品优惠券记录自增长ID
     * @param couponRecordId 商品优惠券记录自增长ID
     */
    public void setCouponRecordId(int couponRecordId) {
        this.couponRecordId = couponRecordId;
    }
    /**
     * 获取 商品优惠券ID
     * @return couponRecordCouponId
     */
    public int getCouponRecordCouponId() {
        return couponRecordCouponId;
    }

    /**
     * 设置 商品优惠券ID
     * @param couponRecordCouponId 商品优惠券ID
     */
    public void setCouponRecordCouponId(int couponRecordCouponId) {
        this.couponRecordCouponId = couponRecordCouponId;
    }
    /**
     * 获取 用户ID
     * @return couponRecordUserId
     */
    public int getCouponRecordUserId() {
        return couponRecordUserId;
    }

    /**
     * 设置 用户ID
     * @param couponRecordUserId 用户ID
     */
    public void setCouponRecordUserId(int couponRecordUserId) {
        this.couponRecordUserId = couponRecordUserId;
    }
    /**
     * 获取 优惠券创建时间
     * @return couponRecordCreateDate
     */
    public Date getCouponRecordCreateDate() {
        return couponRecordCreateDate;
    }

    /**
     * 设置 优惠券创建时间
     * @param couponRecordCreateDate 优惠券创建时间
     */
    public void setCouponRecordCreateDate(Date couponRecordCreateDate) {
        this.couponRecordCreateDate = couponRecordCreateDate;
    }
    /**
     * 获取 优惠券有效开始时间
     * @return couponRecordStartDate
     */
    public Date getCouponRecordStartDate() {
        return couponRecordStartDate;
    }

    /**
     * 设置 优惠券有效开始时间
     * @param couponRecordStartDate 优惠券有效开始时间
     */
    public void setCouponRecordStartDate(Date couponRecordStartDate) {
        this.couponRecordStartDate = couponRecordStartDate;
    }
    /**
     * 获取 优惠券有效结束时间
     * @return couponRecordEndDate
     */
    public Date getCouponRecordEndDate() {
        return couponRecordEndDate;
    }

    /**
     * 设置 优惠券有效结束时间
     * @param couponRecordEndDate 优惠券有效结束时间
     */
    public void setCouponRecordEndDate(Date couponRecordEndDate) {
        this.couponRecordEndDate = couponRecordEndDate;
    }
    /**
     * 获取 优惠券状态:0.未使用,1已使用,2已过期
     * @return couponRecordStatus
     */
    public int getCouponRecordStatus() {
        return couponRecordStatus;
    }

    /**
     * 设置 优惠券状态:0.未使用,1已使用,2已过期
     * @param couponRecordStatus 优惠券状态:0.未使用,1已使用,2已过期
     */
    public void setCouponRecordStatus(int couponRecordStatus) {
        this.couponRecordStatus = couponRecordStatus;
    }

}
