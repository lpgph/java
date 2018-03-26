package com.refill.people.entity;

import com.refill.base.entity.BaseTreeEntity;

import java.util.Date;

/**
 * @author Peter Guo
 * @version 0.1
 * @ClassName ManagerEntity
 * @Description 管理员实体类
 * @date 17/05/02
 */
public class ManagerEntity extends BaseTreeEntity {

    /**
     * 管理员角色ID
     */
    private int managerRoleId;

    /**
     *管理员名称
     */
    private String managerName;


    /**
     * 管理员账号
     */
    private String managerAccount;


    /**
     * 管理员密码
     */
    private String managerPassword;

    /**
     * 管理员类别
     */
    private int managerCategoryId;


    /**
     * 管理员创建日期
     */
    private Date managerCreateDate;


    /**
     * 管理员最后登录日期
     */
    private Date managerUpdateDate;


    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getManagerAccount() {
        return managerAccount;
    }

    public void setManagerAccount(String managerAccount) {
        this.managerAccount = managerAccount;
    }

    public String getManagerPassword() {
        return managerPassword;
    }

    public void setManagerPassword(String managerPassword) {
        this.managerPassword = managerPassword;
    }

    public int getManagerCategoryId() {
        return managerCategoryId;
    }

    public void setManagerCategoryId(int managerCategoryId) {
        this.managerCategoryId = managerCategoryId;
    }

    public Date getManagerCreateDate() {
        return managerCreateDate;
    }

    public void setManagerCreateDate(Date managerCreateDate) {
        this.managerCreateDate = managerCreateDate;
    }

    public Date getManagerUpdateDate() {
        return managerUpdateDate;
    }

    public void setManagerUpdateDate(Date managerUpdateDate) {
        this.managerUpdateDate = managerUpdateDate;
    }

    /**
     * 获取 管理员角色ID
     *
     * @return managerRoleId
     */
    public int getManagerRoleId() {
        return managerRoleId;
    }

    /**
     * 设置 管理员角色ID
     *
     * @param managerRoleId 管理员角色ID
     */
    public void setManagerRoleId(int managerRoleId) {
        this.managerRoleId = managerRoleId;
    }

}