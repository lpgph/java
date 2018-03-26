package com.refill.basic.entity;

import com.refill.base.entity.BaseEntity;

/**
 * @author Peter Guo
 * @version 0.1
 * @ClassName RoleModuleEntity
 * @Description 角色模块实体类
 * @date 17/05/02
 */
public class RoleModuleEntity extends BaseEntity {


    /**
     * 角色ID
     */
    private int roleModuleRoleId;


    /**
     * 模块ID
     */
    private int roleModuleModuleId;

    /**
     * 模块实体
     */
    private ModuleEntity module;


    /**
     * 获取 角色ID
     *
     * @return roleModuleRoleId
     */
    public int getRoleModuleRoleId() {
        return roleModuleRoleId;
    }

    /**
     * 设置 角色ID
     *
     * @param roleModuleRoleId 角色ID
     */
    public void setRoleModuleRoleId(int roleModuleRoleId) {
        this.roleModuleRoleId = roleModuleRoleId;
    }

    /**
     * 获取 模块ID
     *
     * @return roleModuleModuleId
     */
    public int getRoleModuleModuleId() {
        return roleModuleModuleId;
    }

    /**
     * 设置 模块ID
     *
     * @param roleModuleModuleId 模块ID
     */
    public void setRoleModuleModuleId(int roleModuleModuleId) {
        this.roleModuleModuleId = roleModuleModuleId;
    }

    /**
     * 获取 模块实体
     *
     * @return module
     */
    public ModuleEntity getModule() {
        return module;
    }

    /**
     * 设置 模块实体
     *
     * @param module 模块实体
     */
    public void setModule(ModuleEntity module) {
        this.module = module;
    }
}