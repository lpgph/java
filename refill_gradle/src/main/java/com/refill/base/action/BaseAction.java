package com.refill.base.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author Peter Guo
 * @version 0.1
 * @ClassName BaseAction
 * @Description 基类控制层
 * @date 17/05/02
 */
public abstract class BaseAction {
    /**
     * slf4j日志记录
     */
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

//    /**
//     * 获取请求路径前缀
//     */
//    @Value("${requestPath}")
//    protected String requestPath;

}
