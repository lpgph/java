package com.refill.people.biz;

import com.refill.base.biz.IBaseBiz;
import com.refill.people.entity.UserEntity;

/**
 * @author Peter Guo
 * @version 0.1
 * @ClassName IUserBiz
 * @Description 用户业务层接口
 * @date 17/05/02
 */
public interface IUserBiz extends IBaseBiz<UserEntity>  {

    /**
     * 通过账号获取用户实体
     * @param userAccount
     * @return
     */
    public UserEntity getByUserAccount(String userAccount);
}
