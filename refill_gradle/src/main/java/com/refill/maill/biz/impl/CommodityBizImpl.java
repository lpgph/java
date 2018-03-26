package com.refill.maill.biz.impl;

import com.refill.base.dao.IBaseDao;
import com.refill.basic.biz.impl.InformationBizImpl;
import com.refill.maill.biz.ICommodityBiz;
import com.refill.maill.dao.ICommodityDao;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by peter on 5/2/17.
 */
public class CommodityBizImpl extends InformationBizImpl implements ICommodityBiz {


    @Autowired
    private ICommodityDao commodityDao;

    @Override
    protected IBaseDao getDao() {
        return commodityDao;
    }
}
