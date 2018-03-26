package com.refill.people.biz.impl;

import com.refill.base.biz.impl.BaseBizImpl;
import com.refill.base.dao.IBaseDao;
import com.refill.people.biz.IUserBiz;
import com.refill.people.dao.IUserDao;
import com.refill.people.entity.MerchantEntity;
import com.refill.people.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Peter Guo
 * @version 0.1
 * @ClassName UserBizImpl
 * @Description 用户业务层实现类
 * @date 17/05/02
 */
@Service("userBiz")
public class UserBizImpl extends BaseBizImpl<UserEntity>  implements IUserBiz {

    /**
     * 注入用户持久化层接口
     */
    @Autowired
    private IUserDao userDao;

    @Override
    protected IBaseDao<UserEntity>  getDao() {
        return userDao;
    }

    @Override
    public UserEntity getByUserAccount(String userAccount) {
        return null;
    }
}
