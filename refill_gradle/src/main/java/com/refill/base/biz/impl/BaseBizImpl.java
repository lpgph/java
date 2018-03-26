package com.refill.base.biz.impl;

import com.refill.base.bean.OrderByBean;
import com.refill.base.bean.ResultJsonBean;
import com.refill.base.biz.IBaseBiz;
import com.refill.base.dao.IBaseDao;
import com.refill.base.bean.PageBean;
import com.refill.base.entity.BaseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/**
 * @author Peter Guo
 * @version 0.1
 * @ClassName BaseBizImpl
 * @Description 基类业务层实现类
 * @date 17/05/02
 */
public abstract class BaseBizImpl <T extends BaseEntity> implements IBaseBiz<T>{

    /**
     * slf4j日志记录
     */
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());


    /**
     * 注入持久化层
     *
     * @return 注入成功的持久化层
     */
    protected abstract IBaseDao<T> getDao();


    @Override
    public ResultJsonBean saveEntity(T entity) {
        getDao().saveEntity(entity);
        return new ResultJsonBean(null,true);
    }

    @Override
    public void saveBatch(List<T> list) {
        getDao().saveBatch(list);
    }

    @Override
    public ResultJsonBean updateEntity(T entity) {
        getDao().updateEntity(entity);
        return new ResultJsonBean(null,true);
    }

    @Override
    public void updateBatchById(int id, List<T> list) {
        getDao().updateBatchById(id, list);
    }

    @Override
    public void updatesBatch(List<T> list) {
        getDao().updatesBatch(list);
    }

    @Override
    public void saveBatchById(int id, List<T> list) {
        getDao().saveBatchById(id, list);
    }

    @Override
    public ResultJsonBean deleteEntity(int id) {
        getDao().deleteEntity(id);
        return new ResultJsonBean(null,true);
    }

    @Override
    public ResultJsonBean deleteEntity(int id, Boolean isForce) {
        return new ResultJsonBean(null,true);
    }

    @Override
    public void deleteBatch(String[] ids) {
        getDao().deleteBatch(ids);
    }

    @Override
    public T getEntity(int id) {
        return getDao().getEntity(id);
    }

    @Override
    public int count() {
        return getDao().count();
    }

    @Override
    public int countByMap(Map<String, Object> whereMap) {
        return getDao().countByMap(whereMap);
    }

    @Override
    public List<T> queryAll() {
        return getDao().queryAll();
    }

    @Override
    public List<T> queryAllByPage(PageBean page, List<OrderByBean> orderList) {
        return getDao().queryAllByPage(page, orderList);
    }

    @Override
    public List<T> queryListByMap(Map<String, Object> whereMap) {
        return getDao().queryListByMap(whereMap);
    }

    @Override
    public List<T> queryListByMapPage(Map<String, Object> whereMap, PageBean page, List<OrderByBean> orderList) {
        return getDao().queryListByMapPage(whereMap, page, orderList);
    }
    @Override
    public int countByModuleIdAndMap(int moduleId, Map<String, Object> whereMap) {
        return getDao().countByModuleIdAndMap(moduleId, whereMap);
    }

    @Override
    public List<T> queryListByModuleIdAndMapPage(int moduleId, Map<String, Object> whereMap, PageBean page, List<OrderByBean> orderList) {
        return getDao().queryListByModuleIdAndMapPage(moduleId, whereMap, page, orderList);
    }

}