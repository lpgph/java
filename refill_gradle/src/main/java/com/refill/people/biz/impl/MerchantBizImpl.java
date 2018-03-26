package com.refill.people.biz.impl;

import com.refill.base.biz.impl.BaseBizImpl;
import com.refill.base.dao.IBaseDao;
import com.refill.people.biz.IMerchantBiz;
import com.refill.people.dao.IMerchantDao;
import com.refill.people.entity.MerchantEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Peter Guo
 * @version 0.1
 * @ClassName MerchantBizImpl
 * @Description 商户业务层实现类
 * @date 17/05/02
 */
@Service("merchantBiz")
public class MerchantBizImpl extends BaseBizImpl<MerchantEntity> implements IMerchantBiz {

    /**
     * 注入商户持久化层接口
     */
    @Autowired
    private IMerchantDao merchantDao;

    @Override
    protected IBaseDao<MerchantEntity>  getDao() {
        return merchantDao;
    }
}
