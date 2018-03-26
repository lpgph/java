package com.refill.basic.biz.impl;

import com.refill.base.bean.ResultJsonBean;
import com.refill.base.dao.IBaseDao;
import com.refill.basic.biz.IInformationBiz;
import com.refill.basic.dao.ICategoryDao;
import com.refill.basic.dao.IInformationDao;
import com.refill.basic.entity.CategoryEntity;
import com.refill.basic.entity.InformationEntity;
import com.refill.base.biz.impl.BaseBizImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Peter Guo
 * @version 0.1
 * @ClassName IInformationBizImpl
 * @Description 信息业务层实现类
 * @date 17/05/02
 */
@Service("informationBiz")
public class InformationBizImpl extends BaseBizImpl<InformationEntity> implements IInformationBiz {

    /**
     * 注入信息持久化层接口
     */
    @Autowired
    private IInformationDao informationDao;

    /**
     * 注入类别持久化层
     */
    @Autowired
    private ICategoryDao categoryDao;


    @Override
    protected IBaseDao<InformationEntity> getDao() {
        return informationDao;
    }

    /**
     * 重写saveEntity
     * @param information
     * @return
     */
    public ResultJsonBean saveEntity(InformationEntity information) {
        //查询模块是否存在
        CategoryEntity category = (CategoryEntity) categoryDao.getEntity(information.getInformationCategoryId());
        if(category == null){
            return new ResultJsonBean(null,false,null);
        }
        informationDao.saveEntity(information);
        return new ResultJsonBean(null,true);
    }

    public ResultJsonBean updateEntity(InformationEntity information) {
        //查询实体是否存在
        InformationEntity oldEntity = informationDao.getEntity(information.getInformationId());
        if(oldEntity == null){
            return new ResultJsonBean(null,false,null);
        }
        informationDao.saveEntity(information);
        return new ResultJsonBean(null,true);
    }

    public ResultJsonBean deleteEntity(int informationId){
        //查询实体是否存在
        InformationEntity information = informationDao.getEntity(informationId);
        if(information == null){
            return new ResultJsonBean(null,false,null);
        }
        informationDao.deleteEntity(informationId);
        return new ResultJsonBean(null,true);
    }


}
