package com.refill.maill.entity;
import com.refill.base.entity.BaseEntity;

import java.util.Date;

/**
 * @ClassName SalesRecordEntity
 *
 * @Description 商品销售记录实体类
 * @author Peter Guo
 * @date 17/05/02
 * @version 0.1
 *
 */
public class SalesRecordEntity extends BaseEntity{

    /**
     * 商品销售记录自增长ID
     */
    private int salesRecordId;

    /**
     * 商品ID,依赖关系
     */
    private int salesRecordCommodityId;

    /**
     * 购买用户ID,依赖关系
     */
    private int salesRecordUserId;

    /**
     * 优惠券记录ID,依赖关系
     */
    private int salesRecordCouponRecordId;

    /**
     * 商品支付价格
     */
    private Double salesRecordPrice;

    /**
     * 支付方式(1.微信2.支付宝3.银联)
     */
    private int salesRecordPayment;

    /**
     * 创建时间
     */
    private Date salesRecordCreatedate;

    /**
     * 订单状态 0 已下单,1.已取消 2.已付款 3.已发货 4已签收,5.退货中,6.已退货 7退款中 8 已退款 9. 已完成
     */
    private int salesRecordStatus;

    /**
     * 获取 商品销售记录自增长ID
     * @return salesRecordId
     */
    public int getSalesRecordId() {
        return salesRecordId;
    }

    /**
     * 设置 商品销售记录自增长ID
     * @param salesRecordId 商品销售记录自增长ID
     */
    public void setSalesRecordId(int salesRecordId) {
        this.salesRecordId = salesRecordId;
    }
    /**
     * 获取 商品商品ID
     * @return salesRecordCommodityId
     */
    public int getSalesRecordCommodityId() {
        return salesRecordCommodityId;
    }

    /**
     * 设置 商品商品ID
     * @param salesRecordCommodityId 商品商品ID
     */
    public void setSalesRecordCommodityId(int salesRecordCommodityId) {
        this.salesRecordCommodityId = salesRecordCommodityId;
    }

    /**
     * 获取 购买用户ID
     * @return salesRecordUserId
     */
    public int getSalesRecordUserId() {
        return salesRecordUserId;
    }

    /**
     * 设置 购买用户ID
     * @param salesRecordUserId 购买用户ID
     */
    public void setSalesRecordUserId(int salesRecordUserId) {
        this.salesRecordUserId = salesRecordUserId;
    }
    /**
     * 获取 优惠券记录ID
     * @return salesRecordCouponRecordId
     */
    public int getSalesRecordCouponRecordId() {
        return salesRecordCouponRecordId;
    }

    /**
     * 设置 优惠券记录ID
     * @param salesRecordCouponRecordId 优惠券记录ID
     */
    public void setSalesRecordCouponRecordId(int salesRecordCouponRecordId) {
        this.salesRecordCouponRecordId = salesRecordCouponRecordId;
    }
    /**
     * 获取 商品支付价格
     * @return salesRecordPrice
     */
    public Double getSalesRecordPrice() {
        return salesRecordPrice;
    }

    /**
     * 设置 商品支付价格
     * @param salesRecordPrice 商品支付价格
     */
    public void setSalesRecordPrice(Double salesRecordPrice) {
        this.salesRecordPrice = salesRecordPrice;
    }
    /**
     * 获取 支付方式(1.微信2.支付宝3.银联)
     * @return salesRecordPayment
     */
    public int getSalesRecordPayment() {
        return salesRecordPayment;
    }

    /**
     * 设置 支付方式(1.微信2.支付宝3.银联)
     * @param salesRecordPayment 支付方式(1.微信2.支付宝3.银联)
     */
    public void setSalesRecordPayment(int salesRecordPayment) {
        this.salesRecordPayment = salesRecordPayment;
    }
    /**
     * 获取 创建时间
     * @return salesRecordCreatedate
     */
    public Date getSalesRecordCreatedate() {
        return salesRecordCreatedate;
    }

    /**
     * 设置 创建时间
     * @param salesRecordCreatedate 创建时间
     */
    public void setSalesRecordCreatedate(Date salesRecordCreatedate) {
        this.salesRecordCreatedate = salesRecordCreatedate;
    }
    /**
     * 获取 订单状态 0 已下单,1.已取消 2.已付款 3.已发货 4已签收,5.退货中,6.已退货 7退款中 8 已退款 9. 已完成
     * @return salesRecordStatus
     */
    public int getSalesRecordStatus() {
        return salesRecordStatus;
    }

    /**
     * 设置 订单状态 0 已下单,1.已取消 2.已付款 3.已发货 4已签收,5.退货中,6.已退货 7退款中 8 已退款 9. 已完成
     * @param salesRecordStatus 订单状态 0 已下单,1.已取消 2.已付款 3.已发货 4已签收,5.退货中,6.已退货 7退款中 8 已退款 9. 已完成
     */
    public void setSalesRecordStatus(int salesRecordStatus) {
        this.salesRecordStatus = salesRecordStatus;
    }

}