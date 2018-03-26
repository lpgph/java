package com.refill.job.biz.impl;

import com.refill.job.biz.IModuleBiz;
import com.refill.job.entity.ModuleEntity;
import org.springframework.stereotype.Service;

/**
 * @author Peter Guo
 * @version 0.1
 * @ClassName ModuleBizImpl
 * @Description 模块业务层实现类
 * @date 17/05/02
 */
@Service("moduleBiz")
public class ModuleBizImpl implements IModuleBiz {

    @Override
    public ModuleEntity getEntity(int id) {
        return new ModuleEntity(id,"123333"+id,"test"+id);
    }
}
