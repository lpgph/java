package com.refill.basic.dao;

import com.refill.base.dao.IBaseTreeDao;
import com.refill.basic.entity.ModuleEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Peter Guo
 * @version 0.1
 * @ClassName IModuleDao
 * @Description 模块持久化接口
 * @date 17/05/02
 */
@Repository
public interface IModuleDao extends IBaseTreeDao<ModuleEntity> {

    /**
     * 根据模块编码获取模块实体
     * @param moduleCode 模块编码
     * @return 模块实体
     */
    public ModuleEntity getModuleEntity(String moduleCode);

}
