package com.refill.basic;

import com.refill.base.BaseTest;
import com.refill.base.bean.OrderByBean;
import com.refill.base.constant.e.OrderEnum;
import com.refill.basic.biz.IInformationBiz;
import com.refill.basic.entity.InformationEntity;
import com.refill.util.StringUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;


/**
 * @author Peter Guo
 * @version 0.1
 * @ClassName InformationTest
 * @Description 信息测试类
 * @date 17/05/03
 */
public class InformationTest extends BaseTest {

    @Autowired
    private IInformationBiz informationBiz;

    @Test
    public void save() {
        InformationEntity information = new InformationEntity();
        information.setInformationTitle("文章标题1");
        information.setInformationContent("内容1");
        information.setInformationCreateDate(new Date());
        information.setInformationCreatePeopleId(1);
        information.setInformationCategoryId(4);
        informationBiz.saveEntity(information);
        logger.debug("文章ID: {}",information.getInformationId());
    }

    @Test
    public void query(){
        List<OrderByBean> orderList = new ArrayList<>();
        orderList.add(new OrderByBean("informationCreatDate", OrderEnum.ASC));
        orderList.add(new OrderByBean("informationTitle", OrderEnum.DESC));

        //获取查询条件
        Map<String,Object> whereMap = new HashMap<>();
        String[] categoryIds = {"1","4"};
        whereMap.put("categoryIds",categoryIds);

        int count = informationBiz.countByMap(whereMap);
        @SuppressWarnings("unchecked")
        List<InformationEntity> list = informationBiz.queryListByMapPage(whereMap,null,orderList);

        if(StringUtil.isBlank(list)){
            logger.debug("没有找到数据");
            return;
        }
        for (InformationEntity i:list) {
            logger.debug("{} {}", i.getInformationCreateDate(), i.getInformationTitle());
        }
    }
}
