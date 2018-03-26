package com.refill.base.bean;

/**
 * @author Peter Guo
 * @version 0.1
 * @ClassName PageBean
 * @Description 分页Bean
 * @date 17/05/07
 */
public class PageBean extends BaseBean{

    /**
     * 总记录数
     */
    protected int recordCount;

    /**
     * 当前页码
     */
    protected int pageNo;

    /**
     * 总页数
     */
    protected int pageCount;

    /**
     * 一页显示数量
     */
    protected int pageSize = 10;

    /**
     * 保存当前页码数
     *
     * @param pageNo 当前页数
     */
    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    /**
     * 获取总页数
     *
     * @return pageCount 总页数
     */
    public int getPageCount() {
        return pageCount;
    }

    /**
     * 保存总页数
     *
     * @param pageCount 总页数
     */
    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    /**
     * 只提供给数据查询用。例如导出数据的记录现在，pageSize=9999,表示查询9999条记录，没有分页效果
     *
     * @param pageSize
     */
    public PageBean(int pageSize) {
        this.pageSize = pageSize;
        this.pageNo = 0;
    }

    /**
     * 初始化分页实体类,默认每页10条
     *
     * @param pageNo      当前页码默认调用BaseAction的getPageNo()方法
     * @param recordCount 总记录数
     */
    public PageBean(int pageNo, int recordCount) {
        this.pageNo = pageNo - 1;
        this.recordCount = recordCount;

        calculatePageCount();
    }

    /**
     * 初始化分页实体类
     *
     * @param pageNo      当前页码
     * @param pageSize    一页显示的数量
     * @param recordCount 总记录数
     */
    public PageBean(int pageNo, int pageSize, int recordCount) {
        this.pageNo = pageNo - 1;
        this.recordCount = recordCount;
        this.pageSize = pageSize;
        calculatePageCount();
        if (this.pageNo + 1 > this.pageCount && this.pageCount > 1) {
            this.pageNo = this.pageCount - 1;
        }
    }

    /**
     * 计算总页数
     */
    private void calculatePageCount() {
        // 计算总页数
        if (this.recordCount == 0) {
            pageCount = 0;
        } else {
            // 判断是否有余数
            if (recordCount % pageSize == 0) {
                pageCount = recordCount / pageSize;
            } else {
                pageCount = recordCount / pageSize + 1;
            }
        }
    }

    /**
     * 获取总记录数
     *
     * @return 总记录数
     */
    public int getRecordCound() {
        return recordCount;
    }

    /**
     * 获取当前页码
     *
     * @return 返回当前页码
     */
    public int getPageNo() {
        return pageNo;
    }

    /**
     * 获取分页数量
     *
     * @return 分页数量
     */
    public int getPageSize() {
        return pageSize;
    }


}
