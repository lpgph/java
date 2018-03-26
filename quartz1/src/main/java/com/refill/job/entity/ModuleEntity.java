package com.refill.job.entity;

import java.io.Serializable;

/**
 * @author Peter Guo
 * @version 0.1
 * @ClassName ModuleEntity
 * @Description 模块实体类
 * @date 17/05/02
 */
public class ModuleEntity implements Serializable {

    /**
     * 数结构自增长ID
     */
    private int moduleId;


    /**
     * 模块标题
     */
    private String moduleTitle;

    /**
     * 模块编码
     */
    private String moduleCode;


    public ModuleEntity(){

    }

    public ModuleEntity(int moduleId,String moduleCode, String moduleTitle){
        this.moduleId = moduleId;
        this.moduleCode = moduleCode;
        this.moduleTitle = moduleTitle;
    }


    public int getModuleId() {
        return moduleId;
    }

    public void setModuleId(int moduleId) {
        this.moduleId = moduleId;
    }

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



}