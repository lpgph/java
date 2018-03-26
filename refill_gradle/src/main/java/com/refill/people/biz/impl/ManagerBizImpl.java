package com.refill.people.biz.impl;

import com.refill.base.biz.impl.BaseTreeBizImpl;
import com.refill.base.dao.IBaseTreeDao;
import com.refill.people.biz.IManagerBiz;
import com.refill.people.dao.IManagerDao;
import com.refill.people.entity.ManagerEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Peter Guo
 * @version 0.1
 * @ClassName ManagerBizImpl
 * @Description 管理员业务层实现类
 * @date 17/05/02
 */
@Service("managerBiz")
public class ManagerBizImpl extends BaseTreeBizImpl<ManagerEntity> implements IManagerBiz{

    /**
     * 注入管理员持久化层接口
     */
    @Autowired
    private IManagerDao managerDao;

    @Override
    protected IBaseTreeDao<ManagerEntity> getBaseTreeDao() {
        return managerDao;
    }
}
