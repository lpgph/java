package com.refill.job.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * @author Peter Guo
 * @version 0.1
 * @ClassName StartUpListener
 * @Description 启动监听
 * @date 17/04/25
 */
@WebListener("startUpListener")
public class StartUpListener implements ServletContextListener {

    /**
     * slf4j日志记录
     */
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());


    /**
     * 监听项目启动，进行初始化
     *
     * @param sce ServletContextEvent对象
     */
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // TODO Auto-generated method stub
        logger.debug("---项目启动初始化开始---");
        logger.debug("---项目启动初始化结束---");

    }

    /**
     * 监听项目终止，进行销毁
     *
     * @param sce ServletContextEvent对象
     */
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // TODO Auto-generated method stub
        logger.debug("---项目监听终止进行销毁---");
    }

}
