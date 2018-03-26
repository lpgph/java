package com.refill.basic.biz;

import com.refill.base.biz.IBaseTreeBiz;
import com.refill.basic.entity.ModuleEntity;

import java.util.List;

/**
 * @author Peter Guo
 * @version 0.1
 * @ClassName IModuleBiz
 * @Description 模块业务层接口
 * @date 17/05/02
 */
public interface IModuleBiz extends IBaseTreeBiz<ModuleEntity> {
    /**
     * 根据模块编码获取模块实体
     * @param moduleCode 模块编码
     * @return 模块实体
     */
    public ModuleEntity getModuleEntity(String moduleCode);
}
