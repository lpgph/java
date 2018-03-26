package com.refill.basic.biz.impl;

import com.refill.base.bean.ResultJsonBean;
import com.refill.base.biz.impl.BaseTreeBizImpl;
import com.refill.base.dao.IBaseTreeDao;
import com.refill.base.entity.BaseTreeEntity;
import com.refill.basic.biz.IModuleBiz;
import com.refill.basic.dao.IModuleDao;
import com.refill.basic.entity.ModuleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Peter Guo
 * @version 0.1
 * @ClassName ModuleBizImpl
 * @Description 模块业务层实现类
 * @date 17/05/02
 */
@Service("moduleBiz")
public class ModuleBizImpl extends BaseTreeBizImpl<ModuleEntity> implements IModuleBiz {
    /**
     * 注入模块持久化层接口
     */
    @Autowired
    private IModuleDao moduleDao;

    @Override
    protected IBaseTreeDao<ModuleEntity> getBaseTreeDao() {
        return moduleDao;
    }


    @Override
    public ModuleEntity getModuleEntity(String moduleCode) {
        return moduleDao.getModuleEntity(moduleCode);
    }

    protected ResultJsonBean whetherCanSave(ModuleEntity module){
        ModuleEntity getModule = moduleDao.getModuleEntity(module.getModuleCode());
        if(getModule!=null){
            return new ResultJsonBean(null,false,null);
        }
        return new ResultJsonBean(null,true);
    }

    protected ResultJsonBean whetherCanUpdate(ModuleEntity module){
        //通过ID查询模块是否存在
        ModuleEntity oldEntity = (ModuleEntity) getBaseTreeDao().getEntity(module.getBaseTreeId());
        if(oldEntity == null){
            return new ResultJsonBean(null,false,null);
        }
        //查询模块模块编码是否重复
        ModuleEntity oldCodeEntity = moduleDao.getModuleEntity(module.getModuleCode());
        if(oldCodeEntity != null && oldCodeEntity.getBaseTreeId()!= module.getBaseTreeId()){
            return new ResultJsonBean(null,false,null);
        }
        return new ResultJsonBean(null,true);
    }

}
