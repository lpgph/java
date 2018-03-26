package com.refill.basic.entity;

import com.refill.base.entity.BaseEntity;

import java.util.Date;

/**
 * @author Peter Guo
 * @version 0.1
 * @ClassName Information
 * @Description 信息实体类
 * @date 17/05/02
 */
public class InformationEntity extends BaseEntity {

    /**
     * 信息自增长ID
     */
    private int informationId;

    /**
     * 信息标题
     */
    private String informationTitle;

    /**
     * 信息类别 关联(聚合)关系
     */
    private int informationCategoryId;

    /**
     * 信息描述
     */
    private String informationDescription;

    /**
     * 信息内容
     */
    private String informationContent;

    /**
     * 信息创建时间
     */
    private Date informationCreateDate;

    /**
     * 信息创建人
     */
    private int informationCreatePeopleId;

    /**
     * 信息更新时间
     */
    private Date informationUpdateDate;

    /**
     * 信息更新人
     */
    private int informationUpdatePeopleId;

    /**
     * 信息类型:0 商户信息 1 积分信息
     */
    private int informationType;

    /**
     * 信息评论次数
     */
    private int informationCommentNumber;

    /**
     * 信息点击量
     */
    private int informationHitNumber;

    /**
     * 信息缩略图路径
     */
    private String informationIconPath;

    /**
     * 获取
     *
     * @return informationId
     */
    public int getInformationId() {
        return informationId;
    }

    /**
     * 设置
     *
     * @param informationId
     */
    public void setInformationId(int informationId) {
        this.informationId = informationId;
    }

    /**
     * 获取 信息标题
     *
     * @return informationTitle
     */
    public String getInformationTitle() {
        return informationTitle;
    }

    /**
     * 设置 信息标题
     *
     * @param informationTitle 信息标题
     */
    public void setInformationTitle(String informationTitle) {
        this.informationTitle = informationTitle;
    }

    /**
     * 获取 信息类别
     *
     * @return informationCategoryId
     */
    public int getInformationCategoryId() {
        return informationCategoryId;
    }

    /**
     * 设置 信息类别
     *
     * @param informationCategoryId 信息类别
     */
    public void setInformationCategoryId(int informationCategoryId) {
        this.informationCategoryId = informationCategoryId;
    }

    /**
     * 获取 信息描述
     *
     * @return informationDescription
     */
    public String getInformationDescription() {
        return informationDescription;
    }

    /**
     * 设置 信息描述
     *
     * @param informationDescription 信息描述
     */
    public void setInformationDescription(String informationDescription) {
        this.informationDescription = informationDescription;
    }

    /**
     * 获取 信息内容
     *
     * @return informationContent
     */
    public String getInformationContent() {
        return informationContent;
    }

    /**
     * 设置 信息内容
     *
     * @param informationContent 信息内容
     */
    public void setInformationContent(String informationContent) {
        this.informationContent = informationContent;
    }

    /**
     * 获取 信息评论次数
     *
     * @return informationCommentNumber
     */
    public int getInformationCommentNumber() {
        return informationCommentNumber;
    }

    /**
     * 设置 信息评论次数
     *
     * @param informationCommentNumber 信息评论次数
     */
    public void setInformationCommentNumber(int informationCommentNumber) {
        this.informationCommentNumber = informationCommentNumber;
    }

    /**
     * 获取 信息点击量
     *
     * @return informationHitNumber
     */
    public int getInformationHitNumber() {
        return informationHitNumber;
    }

    /**
     * 设置 信息点击量
     *
     * @param informationHitNumber 信息点击量
     */
    public void setInformationHitNumber(int informationHitNumber) {
        this.informationHitNumber = informationHitNumber;
    }

    /**
     * 获取 信息创建时间
     *
     * @return informationCreateDate
     */
    public Date getInformationCreateDate() {
        return informationCreateDate;
    }

    /**
     * 设置 信息创建时间
     *
     * @param informationCreateDate 信息创建时间
     */
    public void setInformationCreateDate(Date informationCreateDate) {
        this.informationCreateDate = informationCreateDate;
    }

    /**
     * 获取 信息创建人
     *
     * @return informationCreatePeopleId
     */
    public int getInformationCreatePeopleId() {
        return informationCreatePeopleId;
    }

    /**
     * 设置 信息创建人
     *
     * @param informationCreatePeopleId 信息创建人
     */
    public void setInformationCreatePeopleId(int informationCreatePeopleId) {
        this.informationCreatePeopleId = informationCreatePeopleId;
    }

    /**
     * 获取 信息更新时间
     *
     * @return informationUpdateDate
     */
    public Date getInformationUpdateDate() {
        return informationUpdateDate;
    }

    /**
     * 设置 信息更新时间
     *
     * @param informationUpdateDate 信息更新时间
     */
    public void setInformationUpdateDate(Date informationUpdateDate) {
        this.informationUpdateDate = informationUpdateDate;
    }

    /**
     * 获取 信息更新人
     *
     * @return informationUpdatePeopleId
     */
    public int getInformationUpdatePeopleId() {
        return informationUpdatePeopleId;
    }

    /**
     * 设置 信息更新人
     *
     * @param informationUpdatePeopleId 信息更新人
     */
    public void setInformationUpdatePeopleId(int informationUpdatePeopleId) {
        this.informationUpdatePeopleId = informationUpdatePeopleId;
    }

    /**
     * 获取 缩略图路径
     *
     * @return informationIconPath
     */
    public String getInformationIconPath() {
        return informationIconPath;
    }

    /**
     * 设置 缩略图路径
     *
     * @param informationIconPath 缩略图路径
     */
    public void setInformationIconPath(String informationIconPath) {
        this.informationIconPath = informationIconPath;
    }


    /**
     * 获取 信息类型
     *
     * @return informationType
     */
    public int getInformationType() {
        return informationType;
    }

    /**
     * 设置 信息类型
     *
     * @param informationType 信息类型
     */
    public void setInformationType(int informationType) {
        this.informationType = informationType;
    }
}
