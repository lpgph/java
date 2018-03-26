package com.refill.basic.dao;

import com.refill.base.dao.IBaseTreeDao;
import com.refill.basic.entity.RoleEntity;
import org.springframework.stereotype.Repository;

/**
 * @author Peter Guo
 * @version 0.1
 * @ClassName IRoleDao
 * @Description 角色持久化接口
 * @date 17/05/02
 */
@Repository
public interface IRoleDao extends IBaseTreeDao<RoleEntity> {
}
