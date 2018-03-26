package com.refill.base.bean;

import java.util.List;

/**
 * Created by peter on 5/10/17.
 */
public class ResultPageBean extends BaseBean {

    private int count;
    private List<?> list;

    public ResultPageBean(int count, List<?> list) {
        this.count = count;
        this.list = list;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<?> getList() {
        return list;
    }

    public void setList(List<?> list) {
        this.list = list;
    }
}
