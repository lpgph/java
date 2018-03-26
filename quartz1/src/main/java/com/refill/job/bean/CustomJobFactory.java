package com.refill.job.bean;

import org.quartz.spi.TriggerFiredBundle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;

/**
 * 解决Spring quartz Job不能依赖注入 <br/>
 * Create Date: 3/20/18 10:09 PM <br/>
 * 
 * @author Peter Guo
 * @version 0.1
 **/
public class CustomJobFactory extends SpringBeanJobFactory {

    /**
     * slf4j日志记录
     */
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AutowireCapableBeanFactory capableBeanFactory;

    /**
     * 覆盖super的createJobInstance方法，对其创建出来的类再进行autowire。
     * @param bundle
     * @return
     * @throws Exception
     */
    @Override
    protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {
        //调用父类的方法
        Object jobInstance = super.createJobInstance(bundle);
        logger.debug("SpringBeanJobFactory 开始注入==============================================");
        //进行注入
        capableBeanFactory.autowireBean(jobInstance);
        return jobInstance;
    }
}