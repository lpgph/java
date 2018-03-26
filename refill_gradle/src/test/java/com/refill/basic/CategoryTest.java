package com.refill.basic;

import com.refill.base.BaseTest;
import com.refill.base.bean.OrderByBean;
import com.refill.base.bean.PageBean;
import com.refill.base.constant.e.OrderEnum;
import com.refill.basic.biz.ICategoryBiz;
import com.refill.basic.entity.CategoryEntity;
import com.refill.util.StringUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

/**
 * Created by peter on 5/3/17.
 */
public class CategoryTest extends BaseTest {

    @Autowired
    private ICategoryBiz categoryBiz;

    @Test
    public void save() {
        CategoryEntity category = new CategoryEntity();
        category.setCategoryTitle("分类1.2");
        category.setCategoryModuleId(1);
        category.setBaseTreeParentId(1);
        category.setCategoryCreatePeopleId(1);
        category.setCategoryCreateDate(new Date());

        categoryBiz.saveEntity(category);
        logger.debug("分类ID: {}", category.getBaseTreeId());
    }

    @Test
    public void query() {
        int moduleId = 1;
        int categoryCategoryId = 1;
        int count = categoryBiz.countByModuleIdAndMap(moduleId, null);
        logger.debug("总条数 {}", count);
        //设置排序条件
        List<OrderByBean> orderList = new ArrayList<>();
        orderList.add(new OrderByBean("categoryTitle", OrderEnum.ASC));
        orderList.add(new OrderByBean("categoryCreateDate", OrderEnum.ASC));
        //获取查询条件
        Map<String,Object> whereMap = new HashMap<>();
        //查询模块是否存在
        CategoryEntity category = (CategoryEntity) categoryBiz.getEntity(categoryCategoryId);
        if(category == null){
            return;
        }
        whereMap.put("categoryCategoryId",categoryCategoryId);
        whereMap.put("categoryPath",category.getChildsPathPrefix());

//        @SuppressWarnings("unchecked")
//        List<CategoryEntity> list = categoryBiz.queryListByModuleIdAndMapPage(moduleId, whereMap, new PageBean(1,2,count), orderList);
//        if (StringUtil.isBlank(list)) {
//            logger.debug("没有找到数据");
//            return;
//        }
//        for (CategoryEntity i : list) {
//            logger.debug("{} {}", i.getCategoryCreateDate(), i.getCategoryTitle());
//        }
    }
}
