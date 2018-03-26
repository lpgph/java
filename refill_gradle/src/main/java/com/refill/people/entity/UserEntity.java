package com.refill.people.entity;

import com.refill.base.entity.BaseEntity;

import java.util.Date;

/**
 * @author Peter Guo
 * @version 0.1
 * @ClassName UserEntity
 * @Description 用户实体类
 * @date 17/05/02
 */
public class UserEntity extends BaseEntity {

    /**
     * 用户ID
     */
    private int userId;


    /**
     * 用户手机
     */
    private String userPhone;


    /**
     * 用户邮箱
     */
    private String userMail;


    /**
     * 用户账号
     */
    private String userAccount;


    /**
     * 用户密码
     */
    private String userPassword;


    /**
     * 用户名称
     */
    private String userName;

    /**
     * 用户状态
     */
    private int userStatus;

    /**
     * 用户分类
     */
    private int userCategoryId;

    /**
     * 用户创建时间
     */
    private Date userCreateDate;

    /**
     * 用户更新时间
     */
    private Date userUpdateDate;

    /**
     * 获取 用户ID
     *
     * @return userId
     */
    public int getUserId() {
        return userId;
    }

    /**
     * 设置 用户ID
     *
     * @param userId 用户ID
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * 获取 用户手机
     *
     * @return userPhone
     */
    public String getUserPhone() {
        return userPhone;
    }

    /**
     * 设置 用户手机
     *
     * @param userPhone 用户手机
     */
    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    /**
     * 获取 用户邮箱
     *
     * @return userMail
     */
    public String getUserMail() {
        return userMail;
    }

    /**
     * 设置 用户邮箱
     *
     * @param userMail 用户邮箱
     */
    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    /**
     * 获取 用户账号
     *
     * @return userAccount
     */
    public String getUserAccount() {
        return userAccount;
    }

    /**
     * 设置 用户账号
     *
     * @param userAccount 用户账号
     */
    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    /**
     * 获取 用户密码
     *
     * @return userPassword
     */
    public String getUserPassword() {
        return userPassword;
    }

    /**
     * 设置 用户密码
     *
     * @param userPassword 用户密码
     */
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    /**
     * 获取 用户名称
     *
     * @return userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置 用户名称
     *
     * @param userName 用户名称
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 获取 用户状态
     *
     * @return userStatus
     */
    public int getUserStatus() {
        return userStatus;
    }

    /**
     * 设置 用户状态
     *
     * @param userStatus 用户状态
     */
    public void setUserStatus(int userStatus) {
        this.userStatus = userStatus;
    }

    /**
     * 获取 用户分类
     *
     * @return userCategoryId
     */
    public int getUserCategoryId() {
        return userCategoryId;
    }

    /**
     * 设置 用户分类
     *
     * @param userCategoryId 用户分类
     */
    public void setUserCategoryId(int userCategoryId) {
        this.userCategoryId = userCategoryId;
    }

    /**
     * 获取 用户创建时间
     *
     * @return userCreateDate
     */
    public Date getUserCreateDate() {
        return userCreateDate;
    }
    /**
     * 设置 用户创建时间
     *
     * @param userCreateDate 用户创建时间
     */
    public void setUserCreateDate(Date userCreateDate) {
        this.userCreateDate = userCreateDate;
    }

    /**
     * 获取 用户更新时间
     *
     * @return userUpdateDate
     */
    public Date getUserUpdateDate() {
        return userUpdateDate;
    }
    /**
     * 设置 用户更新时间
     *
     * @param userUpdateDate 用户更新时间
     */
    public void setUserUpdateDate(Date userUpdateDate) {
        this.userUpdateDate = userUpdateDate;
    }
}