package com.refill.basic.biz;

import com.refill.base.bean.OrderByBean;
import com.refill.base.bean.PageBean;
import com.refill.base.biz.IBaseBiz;
import com.refill.basic.entity.RoleModuleEntity;

import java.util.List;

/**
 * @author Peter Guo
 * @version 0.1
 * @ClassName IRoleModuleBiz
 * @Description 角色权限业务层接口
 * @date 17/05/02
 */
public interface IRoleModuleBiz extends IBaseBiz<RoleModuleEntity> {

    /**
     * 通过角色ID获取总条数
     * @param roleId 角色ID
     * @return 总条数
     */
    public int countByRoleId(int roleId);

    /**
     * 通过角色ID查询集合
     * @param roleId 角色ID
     * @param page 分页
     * @param orderList 排序
     * @return RoleModuleEntity集合
     */
    public List<RoleModuleEntity> queryListByRolePage(int roleId, PageBean page, List<OrderByBean> orderList);
}
