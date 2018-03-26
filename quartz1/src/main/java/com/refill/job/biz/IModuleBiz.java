package com.refill.job.biz;
import com.refill.job.entity.ModuleEntity;

/**
 * @author Peter Guo
 * @version 0.1
 * @ClassName IModuleBiz
 * @Description 模块业务层接口
 * @date 17/05/02
 */
public interface IModuleBiz{

    /**
     * 根据ID查询实体信息
     *
     * @param id 实体ID
     * @return 返回base实体
     */
    public ModuleEntity getEntity(int id);

}
