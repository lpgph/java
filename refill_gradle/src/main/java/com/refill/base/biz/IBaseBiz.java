package com.refill.base.biz;

import com.refill.base.bean.OrderByBean;
import com.refill.base.bean.ResultJsonBean;
import com.refill.base.bean.PageBean;
import com.refill.base.entity.BaseEntity;

import java.util.List;
import java.util.Map;

/**
 * @author Peter Guo
 * @version 0.1
 * @ClassName IBaseBiz
 * @Description 基类业务层接口
 * @date 17/05/02
 */
public interface IBaseBiz<T extends BaseEntity> {
    //------------------------- 保存 -------------------------

    /**
     * 保存
     *
     * @param entity 实体
     * @return 返回保存后的id
     */
    public ResultJsonBean saveEntity(T entity);

    /**
     * 批量新增
     *
     * @param list 新增数据
     */
    public void saveBatch(List<T> list);

    //------------------------- 更新 -------------------------

    /**
     * 更新实体
     *
     * @param entity 实体
     */
    public ResultJsonBean updateEntity(T entity);

    /**
     * 批量更新
     *
     * @param list 需要更新的对象集合
     */
    public void updateBatchById(int id, List<T> list);

    /**
     * 批量更新实体
     *
     * @param list 实体集合
     */
    public void updatesBatch(List<T> list);

    /**
     * 批量更新
     *
     * @param list 需要更新的对象集合
     */
    public void saveBatchById(int id, List<T> list);

    //------------------------- 删除 -------------------------

    /**
     * 根据id删除实体
     *
     * @param id 要删除的主键id
     */
    public ResultJsonBean deleteEntity(int id);

    /**
     * 根据id删除实体
     *
     * @param id 要删除的主键id
     * @param isForce 是否强制删除(存在子集情况时)
     */
    public ResultJsonBean deleteEntity(int id, Boolean isForce);

    /**
     * 根据id集合实现批量的删除
     *
     * @param ids id集合
     */
    public void deleteBatch(String[] ids);

    //------------------------- 查询 -------------------------

    /**
     * 根据ID查询实体信息
     *
     * @param id 实体ID
     * @return 返回base实体
     */
    public T getEntity(int id);

    /**
     * 查询数据表中记录集合总数
     *
     * @return 返回查询总条数
     */
    public int count();

    /**
     * 根据map条件查询记录总数
     *
     * @param whereMap 查询条件集合 key:条件名 value:值
     * @return
     */
    public int countByMap(Map<String, Object> whereMap);

    /**
     * 查询所有
     *
     * @return 返回list数组
     */
    public List<T> queryAll();

    /**
     * 分页查询
     *
     * @param page     分页实体
     * @param orderList 排序方式
     * @return 返回list数组
     */
    public List<T> queryAllByPage(PageBean page, List<OrderByBean> orderList);

    /**
     * 根据map条件条件查询列表
     *
     * @param whereMap 查询条件集合 key:条件名 value:值
     * @return 列表
     */
    public List<T> queryListByMap(Map<String, Object> whereMap);

    /**
     * 分页查询</br>
     * 根据map条件查询列表
     *
     * @param whereMap 查询条件集合 key:条件名 value：值
     * @param page     分页条件
     * @param orderList 排序方式
     * @return 列表集合
     */
    public List<T> queryListByMapPage(Map<String, Object> whereMap, PageBean page, List<OrderByBean> orderList);

    //-------------------------根据模块ID查询 -------------------------

    /**
     * 根据mouduleId和whereMap条件查询记录总数
     *
     * @param moduleId 模块ID
     * @param whereMap 查询条件集合 key:条件名 value:值
     * @return 总条数
     */
    public int countByModuleIdAndMap(int moduleId, Map<String, Object> whereMap);

    /**
     * 分页查询</br>
     * 根据mouduleId和whereMap条件查询列表
     *
     * @param moduleId 模块ID
     * @param whereMap 查询条件集合 key:条件名 value：值
     * @param page     分页条件
     * @param orderList 排序方式集合
     * @return 列表集合
     */
    public List<T> queryListByModuleIdAndMapPage(int moduleId, Map<String, Object> whereMap, PageBean page, List<OrderByBean> orderList);
}
