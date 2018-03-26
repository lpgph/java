package com.refill.job.quartz;

import com.refill.job.biz.IModuleBiz;
import com.refill.job.entity.ModuleEntity;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.PersistJobDataAfterExecution;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;


/**
 * 定时任务测试<br/>
 * Create Date: 3/20/18 10:09 PM <br/>
 *
 * @author Peter Guo
 * @version 0.1
 **/
@PersistJobDataAfterExecution //,表示当正常执行完Job后, JobDataMap中的数据应该被改动, 以被下一次调用时用。
@DisallowConcurrentExecution  //不允许并发执行
public class ModuleQuartz extends BaseJob {

    @Autowired
    private IModuleBiz moduleBiz;


    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
       Map<String,Object> map =  getJobDataMap(jobExecutionContext);
        ModuleEntity moduleEntity = moduleBiz.getEntity(Integer.parseInt(map.get("moduleId").toString()));
        String title= moduleEntity!=null ? moduleEntity.getModuleTitle():"空值";
        logger.debug("测试==== {} ====== {} =============", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()),title);

    }
}
