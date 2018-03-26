package com.refill.people.entity;

import com.refill.people.entity.UserEntity;

/**
 * @author Peter Guo
 * @version 0.1
 * @ClassName MerchantEntity
 * @Description 商户实体类, 继承用户类
 * @date 17/05/02
 */
public class MerchantEntity extends UserEntity {

    /**
     * 父级商户ID
     */
    private int merchantParentId;

    /**
     * 父级商户路径
     */
    private String merchantParentPath;

    /**
     * 获取 父级商户ID
     *
     * @return merchantParentId
     */
    public int getMerchantParentId() {
        return merchantParentId;
    }

    /**
     * 设置 父级商户ID
     *
     * @param merchantParentId 父级商户ID
     */
    public void setMerchantParentId(int merchantParentId) {
        this.merchantParentId = merchantParentId;
    }
    /**
     * 获取 父级商户路径
     *
     * @return merchantParentPath 父级商户路径
     */
    public String getMerchantParentPath() {
        return merchantParentPath;
    }
    /**
     * 设置 父级商户路径
     *
     * @param merchantParentPath 父级商户路径
     */
    public void setMerchantParentPath(String merchantParentPath) {
        this.merchantParentPath = merchantParentPath;
    }
}