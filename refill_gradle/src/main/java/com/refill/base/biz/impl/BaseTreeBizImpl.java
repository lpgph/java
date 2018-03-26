package com.refill.base.biz.impl;

import com.refill.base.bean.ResultJsonBean;
import com.refill.base.biz.IBaseTreeBiz;
import com.refill.base.constant.Const;
import com.refill.base.dao.IBaseDao;
import com.refill.base.dao.IBaseTreeDao;
import com.refill.base.entity.BaseTreeEntity;
import com.refill.util.StringUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by peter on 5/10/17.
 */
public abstract class BaseTreeBizImpl<T extends BaseTreeEntity> extends BaseBizImpl<T> implements IBaseTreeBiz<T> {

    /**
     * 注入basicDao
     * @return
     */
    protected abstract IBaseTreeDao<T> getBaseTreeDao();

    @Override
    protected IBaseDao<T> getDao() {
        return getBaseTreeDao();
    }

    public ResultJsonBean saveEntity(T entity) {
        ResultJsonBean check = whetherCanSave(entity);
        if(!check.isResult()){
            return check;
        }
        // 设置路径
        setTreePath(entity);
        getBaseTreeDao().saveEntity(entity);
        return new ResultJsonBean(null,true);
    }

    /**
     * 实体保存条件
     * @param entity T实体
     * @return ResultJsonBean
     */
    protected ResultJsonBean whetherCanSave(T entity){
        return new ResultJsonBean(null,true);
    }

    public ResultJsonBean updateEntity(T entity) {
        ResultJsonBean check = whetherCanUpdate(entity);
        if(!check.isResult()){
            return check;
        }
        // 设置模块路径
        setTreePath(entity);
        getBaseTreeDao().updateEntity(entity);
        return new ResultJsonBean(null,true);
    }

    /**
     * 实体更新条件
     * @param entity T实体
     * @return ResultJsonBean
     */
    protected ResultJsonBean whetherCanUpdate(T entity){
        //查询模块是否存在
        T oldEntity = getBaseTreeDao().getEntity(entity.getBaseTreeId());
        if(oldEntity == null){
            return new ResultJsonBean(null,false,null);
        }
        return new ResultJsonBean(null,true);
    }

    public ResultJsonBean deleteEntity(int baseTreeId,boolean isForce){
        //查询模块是否存在
        T oldEntity = getBaseTreeDao().getEntity(baseTreeId);
        if(oldEntity == null){
            return new ResultJsonBean(null,false,null);
        }
        //根据父级模块路径查询是否存在子模块
        Map<String,Object> whereMap = new HashMap<>();
        if(isForce){
            whereMap.put("baseTreePath",oldEntity.getChildsPathPrefix());
        }
        //根据父级模块路径查询子模块,存在子集不能删除 ps. 可以选择是否强制删除
        List<T> childList = getBaseTreeDao().queryListByMap(whereMap);
        if(StringUtil.isMaxZeroInteger(childList.size())){
            return new ResultJsonBean(null,false,null);
        }
        getBaseTreeDao().deleteEntity(baseTreeId);
        return new ResultJsonBean(null,true);
    }


    /**
     * 设置的父级路径
     * @param entity 树结构实体
     */
    private void setTreePath(T entity){
        if(StringUtil.isMaxZeroInteger(entity.getBaseTreeParentId())){
            T parentEntity = getDao().getEntity(entity.getBaseTreeParentId());
            if(parentEntity != null && !StringUtil.isBlank(parentEntity.getBaseTreeParentPath())){
                entity.setBaseTreeParentPath(parentEntity.getBaseTreeParentPath()+ Const.SEPARATOR + entity.getBaseTreeParentId());
            }
        }else{
            entity.setBaseTreeParentPath(Const.SEPARATOR + entity.getBaseTreeParentId());
        }
    }

}
