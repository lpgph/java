package com.refill.basic.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.refill.base.entity.BaseTreeEntity;
import com.refill.util.DateUtil;

import java.util.Date;

/**
 * @author Peter Guo
 * @version 0.1
 * @ClassName CategoryEntity
 * @Description 类别实体类
 * @date 18/02/27
 */
public class CategoryEntity extends BaseTreeEntity {

    /**
     * 类别标题
     */
    private String categoryTitle;

    /**
     * 类别排序
     */
    private int categorySort;

    /**
     * 类别模块ID
     */
    private int categoryModuleId;


    /**
     * 创建人ID
     */
    private int categoryCreatePeopleId;

    /**
     * 创建时间
     */@JSONField(format = DateUtil.simple)
    private Date categoryCreateDate;

    @JSONField(format = DateUtil.simple)
    private Date categoryUpdateDate;


    /**
     * 最后更新人ID
     */
    private int categoryUpdatePeopleId;


    /**
     * 获取 类别标题
     *
     * @return categoryTitle
     */
    public String getCategoryTitle() {
        return categoryTitle;
    }

    /**
     * 设置 类别标题
     *
     * @param categoryTitle 类别标题
     */
    public void setCategoryTitle(String categoryTitle) {
        this.categoryTitle = categoryTitle;
    }

    /**
     * 获取 类别排序
     *
     * @return categorySort
     */
    public int getCategorySort() {
        return categorySort;
    }

    /**
     * 设置 类别排序
     *
     * @param categorySort 类别排序
     */
    public void setCategorySort(int categorySort) {
        this.categorySort = categorySort;
    }

    /**
     * 获取 类别模块ID
     *
     * @return categoryModuleId
     */
    public int getCategoryModuleId() {
        return categoryModuleId;
    }

    /**
     * 设置 类别模块ID
     *
     * @param categoryModuleId 类别模块ID
     */
    public void setCategoryModuleId(int categoryModuleId) {
        this.categoryModuleId = categoryModuleId;
    }

    /**
     * 获取 创建时间
     *
     * @return categoryCreateDate
     */
    public Date getCategoryCreateDate() {
        return categoryCreateDate;
    }

    /**
     * 设置 创建时间
     *
     * @param categoryCreateDate 创建时间
     */
    public void setCategoryCreateDate(Date categoryCreateDate) {
        this.categoryCreateDate = categoryCreateDate;
    }

    /**
     * 获取 创建人ID
     *
     * @return categoryCreatePeopleId
     */
    public int getCategoryCreatePeopleId() {
        return categoryCreatePeopleId;
    }

    /**
     * 设置 创建人ID
     *
     * @param categoryCreatePeopleId 创建人ID
     */
    public void setCategoryCreatePeopleId(int categoryCreatePeopleId) {
        this.categoryCreatePeopleId = categoryCreatePeopleId;
    }

    /**
     * 获取 更新时间
     *
     * @return categoryUpdateDate
     */
    public Date getCategoryUpdateDate() {
        return categoryUpdateDate;
    }

    /**
     * 设置 更新时间
     *
     * @param categoryUpdateDate 更新时间
     */
    public void setCategoryUpdateDate(Date categoryUpdateDate) {
        this.categoryUpdateDate = categoryUpdateDate;
    }

    /**
     * 获取 最后更新人ID
     *
     * @return categoryUpdatePeopleId
     */
    public int getCategoryUpdatePeopleId() {
        return categoryUpdatePeopleId;
    }

    /**
     * 设置 最后更新人ID
     *
     * @param categoryUpdatePeopleId 最后更新人ID
     */
    public void setCategoryUpdatePeopleId(int categoryUpdatePeopleId) {
        this.categoryUpdatePeopleId = categoryUpdatePeopleId;
    }

}