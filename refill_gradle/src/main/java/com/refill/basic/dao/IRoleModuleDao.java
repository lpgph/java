package com.refill.basic.dao;

import com.refill.base.bean.OrderByBean;
import com.refill.base.dao.IBaseDao;
import com.refill.basic.entity.RoleModuleEntity;
import com.refill.base.bean.PageBean;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Peter Guo
 * @version 0.1
 * @ClassName IRoleModuleDao
 * @Description 角色权限持久化接口
 * @date 17/05/02
 */
@Repository
public interface IRoleModuleDao extends IBaseDao<RoleModuleEntity> {

    /**
     * 根据角色ID查询角色权限总条数
     *
     * @param roleId 角色ID
     * @return 总条数
     */
    public int countByRoleId(int roleId);

    /**
     * 根据角色ID查询角色权限集合
     *
     * @param roleId    角色ID
     * @param page      分页条件
     * @param orderList 排序方式
     * @return 列表集合
     */
    public List<RoleModuleEntity> queryListByRoleId(@Param("roleId") int roleId, @Param("page") PageBean page, @Param("orderList") List<OrderByBean> orderList);
}
