package com.refill.base.bean;

import com.refill.base.constant.e.OrderEnum;


/**
 * @author Peter Guo
 * @version 0.1
 * @ClassName OrderBean
 * @Description 字段排序Bean
 * @date 18/02/28
 */
public class OrderByBean extends BaseBean {

    /**
     * 构造函数
     */
    public OrderByBean(String orderBy, OrderEnum orderEnum){
        this.orderBy = orderBy;
        this.order = orderEnum.toString();
    }

    public OrderByBean(String orderBy){
        this.orderBy = orderBy;
    }

    /**
     * 排序字段
     */
    private String orderBy;

    /**
     * 排序方式
     */
    private String order = OrderEnum.DESC.toString();

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        if(order.equals(OrderEnum.ASC.toString())){
            this.order = order;
        }
    }
}
