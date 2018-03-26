package com.refill.basic.biz.impl;

import com.refill.base.bean.ResultJsonBean;
import com.refill.base.biz.impl.BaseBizImpl;
import com.refill.base.biz.impl.BaseTreeBizImpl;
import com.refill.base.constant.Const;
import com.refill.base.dao.IBaseDao;
import com.refill.base.dao.IBaseTreeDao;
import com.refill.base.entity.BaseEntity;
import com.refill.basic.biz.IRoleBiz;
import com.refill.basic.dao.IRoleDao;
import com.refill.basic.entity.ModuleEntity;
import com.refill.basic.entity.RoleEntity;
import com.refill.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Peter Guo
 * @version 0.1
 * @ClassName RoleBizImpl
 * @Description 角色业务层实现类
 * @date 17/05/02
 */
@Service("roleBiz")
public class RoleBizImpl extends BaseTreeBizImpl<RoleEntity> implements IRoleBiz {

    /**
     * 注入角色持久化层接口
     */
    @Autowired
    private IRoleDao roleDao;

    @Override
    protected IBaseTreeDao<RoleEntity> getBaseTreeDao() {
        return roleDao;
    }

}
