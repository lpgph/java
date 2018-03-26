package com.refill.job;

import com.refill.base.BaseTest;
import com.refill.job.bean.JobBean;
import com.refill.job.bean.JobBeanUtil;
import com.refill.job.quartz.ModuleQuartz;
import org.junit.Test;
import org.quartz.JobDataMap;
import org.quartz.SchedulerException;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 测试调度计划 <br/>
 * Create Date: 3/24/18 6:28 AM <br/>
 *
 * @author Peter Guo
 * @version 0.1
 **/
public class Job2Test extends BaseTest{

    @Test
    public void runTest(){
        try {
            JobBeanUtil.deleteAllJobs();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试定时器能否运行
     * @param args
     */
    public static void main(String[] args) {
        new ClassPathXmlApplicationContext("spring.xml");
        JobDataMap map = new JobDataMap();
        map.put(JobBeanUtil.JOB_TASK_DATA_JSON_NAME,1);
        JobBeanUtil.addJob("任务2",ModuleQuartz.class.getName(),"*/30 * * * * ?",map);
    }



}
