package com.refill.shiro.tag;

import com.refill.util.StringUtil;
import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import java.io.IOException;
import java.util.Map;

/**
 * 自定义freemarker权限标签
 */
public class PermissionDirective implements TemplateDirectiveModel {
    /*
	 * log4j日志记录
	 */
    protected final Logger LOG = Logger.getLogger(this.getClass());
    
    private final static String SHIRO_PARAM= "url";

    @Override
    public void execute(Environment environment, Map map, TemplateModel[] templateModels, TemplateDirectiveBody templateDirectiveBody) throws TemplateException, IOException {
        String url = map.get(SHIRO_PARAM).toString();
        if(!StringUtil.isBlank(url)){
            Subject subject = SecurityUtils.getSubject();
            boolean pass = subject.isPermitted(url);
//            LOG.debug("是否显示:"+pass+",模块地址:"+url);
            if (pass) {
                templateDirectiveBody.render(environment.getOut());
            }
        }
    }
}
