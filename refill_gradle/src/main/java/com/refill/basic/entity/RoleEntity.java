package com.refill.basic.entity;

import com.refill.base.constant.Const;
import com.refill.base.entity.BaseEntity;
import com.refill.base.entity.BaseTreeEntity;

/**
 * @author Peter Guo
 * @version 0.1
 * @ClassName RoleEntity
 * @Description 角色实体类
 * @date 17/05/02
 */
public class RoleEntity extends BaseTreeEntity {

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 获取 角色名称
     *
     * @return roleName
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * 设置 角色名称
     *
     * @param roleName 角色名称
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}