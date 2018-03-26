package com.refill.basic.biz.impl;

import com.refill.base.biz.impl.BaseTreeBizImpl;
import com.refill.base.dao.IBaseTreeDao;
import com.refill.basic.dao.ICategoryDao;
import com.refill.basic.biz.ICategoryBiz;
import com.refill.basic.entity.CategoryEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Peter Guo
 * @version 0.1
 * @ClassName CategoryBizImpl
 * @Description 类别业务层实现类
 * @date 17/05/02
 */
@Service("categoryBiz")
public class CategoryBizImpl extends BaseTreeBizImpl<CategoryEntity> implements ICategoryBiz {

    /**
     * 注入类别持久化层
     */
    @Autowired
    private ICategoryDao categoryDao;

    @Override
    protected IBaseTreeDao<CategoryEntity> getBaseTreeDao() {
        return categoryDao;
    }


}
