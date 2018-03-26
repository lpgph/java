package com.refill.base.listener;

import com.refill.base.constant.Const;
import org.apache.log4j.Logger;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.File;

/**
 * @author Peter Guo
 * @version 0.1
 * @ClassName StartUpListener
 * @Description 启动监听
 * @date 17/04/25
 */
@WebListener("startUpListener")
public class StartUpListener implements ServletContextListener {

    /*
     * log4j日志记录
     */
    protected final Logger LOG = Logger.getLogger(this.getClass());

    /**
     * 监听项目启动，进行初始化
     *
     * @param sce ServletContextEvent对象
     */
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // TODO Auto-generated method stub
        LOG.debug("---项目启动初始化开始---");
        Const.PROJECT_PATH = sce.getServletContext().getRealPath(File.separator);
        Const.CONTEXT = WebApplicationContextUtils.getWebApplicationContext(sce.getServletContext());
        LOG.debug("---项目启动初始化结束---");

    }

    /**
     * 监听项目终止，进行销毁
     *
     * @param sce ServletContextEvent对象
     */
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // TODO Auto-generated method stub
        LOG.debug("---项目监听终止进行销毁---");
    }

}
