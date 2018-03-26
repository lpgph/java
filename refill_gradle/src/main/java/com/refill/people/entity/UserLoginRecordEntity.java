package com.refill.people.entity;
import com.refill.base.entity.BaseEntity;

import java.util.Date;

/**
 * @author Peter Guo
 * @version 0.1
 * @ClassName UserLoginRecordEntity
 * @Description 用户登录记录实体
 * @date 18/02/28
 */
public class UserLoginRecordEntity extends BaseEntity{


    /**
     * 自增长ID
     */
    private int userLoginRecordId;


    /**
     * 模块ID
     */
    private int userLoginRecordModuleId;


    /**
     * 用户ID
     */
    private int userLoginRecordPeopleId;


    /**
     * 最后登录IP
     */
    private String userLoginRecordLastLoginIp;


    /**
     * 用户sessionID
     */
    private int userLoginRecordSessionid;


    /**
     * 用户最后登录日期
     */
    private Date userLoginRecordLastLoginDate;


    /**
     * 最后退出时间
     */
    private Date userLoginRecordLastQuitDate;



    /**
     * 获取
     * @return userLoginRecordId
     */
    public int getUserLoginRecordId() {
        return userLoginRecordId;
    }

    /**
     * 设置
     * @param userLoginRecordId
     */
    public void setUserLoginRecordId(int userLoginRecordId) {
        this.userLoginRecordId = userLoginRecordId;
    }

    public int getUserLoginRecordModuleId() {
        return userLoginRecordModuleId;
    }

    public void setUserLoginRecordModuleId(int userLoginRecordModuleId) {
        this.userLoginRecordModuleId = userLoginRecordModuleId;
    }

    /**
     * 获取
     * @return userLoginRecordPeopleId
     */
    public int getUserLoginRecordPeopleId() {
        return userLoginRecordPeopleId;
    }

    /**
     * 设置
     * @param userLoginRecordPeopleId
     */
    public void setUserLoginRecordPeopleId(int userLoginRecordPeopleId) {
        this.userLoginRecordPeopleId = userLoginRecordPeopleId;
    }
    /**
     * 获取
     * @return userLoginRecordLastLoginIp
     */
    public String getUserLoginRecordLastLoginIp() {
        return userLoginRecordLastLoginIp;
    }

    /**
     * 设置
     * @param userLoginRecordLastLoginIp
     */
    public void setUserLoginRecordLastLoginIp(String userLoginRecordLastLoginIp) {
        this.userLoginRecordLastLoginIp = userLoginRecordLastLoginIp;
    }
    /**
     * 获取
     * @return userLoginRecordSessionid
     */
    public int getUserLoginRecordSessionid() {
        return userLoginRecordSessionid;
    }

    /**
     * 设置
     * @param userLoginRecordSessionid
     */
    public void setUserLoginRecordSessionid(int userLoginRecordSessionid) {
        this.userLoginRecordSessionid = userLoginRecordSessionid;
    }
    /**
     * 获取
     * @return userLoginRecordLastLoginDate
     */
    public Date getUserLoginRecordLastLoginDate() {
        return userLoginRecordLastLoginDate;
    }

    /**
     * 设置
     * @param userLoginRecordLastLoginDate
     */
    public void setUserLoginRecordLastLoginDate(Date userLoginRecordLastLoginDate) {
        this.userLoginRecordLastLoginDate = userLoginRecordLastLoginDate;
    }
    /**
     * 获取
     * @return userLoginRecordLastQuitDate
     */
    public Date getUserLoginRecordLastQuitDate() {
        return userLoginRecordLastQuitDate;
    }

    /**
     * 设置
     * @param userLoginRecordLastQuitDate
     */
    public void setUserLoginRecordLastQuitDate(Date userLoginRecordLastQuitDate) {
        this.userLoginRecordLastQuitDate = userLoginRecordLastQuitDate;
    }

}
