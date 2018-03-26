package com.refill.basic.entity;

import com.refill.base.constant.Const;
import com.refill.base.entity.BaseEntity;
import com.refill.base.entity.BaseTreeEntity;
import com.refill.util.StringUtil;

/**
 * @author Peter Guo
 * @version 0.1
 * @ClassName ModuleEntity
 * @Description 模块实体类
 * @date 17/05/02
 */
public class ModuleEntity extends BaseTreeEntity {



    /**
     * 模块标题
     */
    private String moduleTitle;

    /**
     * 模块编码
     */
    private String moduleCode;


    /**
     * 模块缩略图
     */
    private String moduleIconPath;


    /**
     * 模块排序
     */
    private int moduleSort;

    /**
     * 模块请求路径
     */
    private String moduleUrl;

    /**
     * 获取 模块标题
     *
     * @return moduleTitle
     */
    public String getModuleTitle() {
        return moduleTitle;
    }

    /**
     * 设置 模块标题
     *
     * @param moduleTitle 模块标题
     */
    public void setModuleTitle(String moduleTitle) {
        this.moduleTitle = moduleTitle;
    }

    /**
     * 获取 模块编码
     *
     * @return moduleCode
     */
    public String getModuleCode() {
        return moduleCode;
    }

    /**
     * 设置 模块编码
     *
     * @param moduleCode 模块编码
     */
    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    /**
     * 获取 模块缩略图
     *
     * @return moduleIconPath
     */
    public String getModuleIconPath() {
        return moduleIconPath;
    }

    /**
     * 设置 模块缩略图
     *
     * @param moduleIconPath 模块缩略图
     */
    public void setModuleIconPath(String moduleIconPath) {
        this.moduleIconPath = moduleIconPath;
    }

    /**
     * 获取 模块排序
     *
     * @return moduleSort
     */
    public int getModuleSort() {
        return moduleSort;
    }

    /**
     * 设置 模块排序
     *
     * @param moduleSort 模块排序
     */
    public void setModuleSort(int moduleSort) {
        this.moduleSort = moduleSort;
    }

    /**
     * 获取 模块请求路径
     *
     * @return moduleUrl
     */
    public String getModuleUrl() {
        return moduleUrl;
    }

    /**
     * 设置 模块请求路径
     *
     * @param moduleUrl
     */
    public void setModuleUrl(String moduleUrl) {
        this.moduleUrl = moduleUrl;
    }

}