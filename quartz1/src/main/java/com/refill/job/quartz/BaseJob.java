package com.refill.job.quartz;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 基础job类 <br/>
 * Create Date: 3/22/18 10:23 AM <br/>
 *
 * @author Peter Guo
 * @version 0.1
 **/
public abstract class BaseJob implements Job {

    /**
     * slf4j日志记录
     */
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 获取Job传入事务工作类中的值<br/>
     * 推荐传入json数据<br/>
     *
     * @param context
     *            上下文对象
     * @return 获取传入的值
     */
    protected JobDataMap getJobDataMap(JobExecutionContext context) {
        // 获取传入的JSON数据
        return context.getJobDetail().getJobDataMap();
    }
}
