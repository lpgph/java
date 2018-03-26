package com.refill.base.biz;

import com.refill.base.bean.ResultJsonBean;
import com.refill.base.entity.BaseTreeEntity;

/**
 * Created by peter on 5/10/17.
 */
public interface IBaseTreeBiz<T extends BaseTreeEntity> extends IBaseBiz<T>{

    /**
     * 保存
     *
     * @param entity 实体
     * @return 返回保存后的id
     */
    public ResultJsonBean saveEntity(T entity);

    /**
     * 更新实体
     *
     * @param entity 实体
     */
    public ResultJsonBean updateEntity(T entity);
}
