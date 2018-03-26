package com.refill.basic.biz.impl;

import com.refill.base.bean.OrderByBean;
import com.refill.base.bean.PageBean;
import com.refill.base.biz.impl.BaseBizImpl;
import com.refill.base.dao.IBaseDao;
import com.refill.basic.biz.IRoleModuleBiz;
import com.refill.basic.dao.IRoleModuleDao;
import com.refill.basic.entity.RoleModuleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Peter Guo
 * @version 0.1
 * @ClassName RoleModuleBizImpl
 * @Description 角色权限业务层实现类
 * @date 17/05/02
 */
@Service("roleModuleBiz")
public class RoleModuleBizImpl extends BaseBizImpl<RoleModuleEntity> implements IRoleModuleBiz {

    /**
     * 注入角色权限持久化层接口
     */
    @Autowired
    private IRoleModuleDao roleModuleDao;


    @Override
    protected IBaseDao<RoleModuleEntity> getDao() {
        return roleModuleDao;
    }

    @Override
    public int countByRoleId(int roleId) {
        return roleModuleDao.countByRoleId(roleId);
    }

    @Override
    public List<RoleModuleEntity> queryListByRolePage(int roleId, PageBean page, List<OrderByBean> orderList) {
        return roleModuleDao.queryListByRoleId(roleId,page,orderList);
    }
}
