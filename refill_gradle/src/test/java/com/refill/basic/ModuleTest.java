package com.refill.basic;

import com.alibaba.fastjson.JSONObject;
import com.beust.jcommander.internal.Lists;
import com.refill.base.BaseTest;
import com.refill.base.bean.PageBean;
import com.refill.basic.biz.IModuleBiz;
import com.refill.basic.entity.ModuleEntity;
import com.refill.util.HttpServletUtil;
import com.refill.util.StringUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

/**
 * @author Peter Guo
 * @version 0.1
 * @ClassName ModuleTest
 * @Description 模块测试类
 * @date 17/05/02
 */
public class ModuleTest extends BaseTest {

    /**
     *  引入分类业务层接口
     */
    @Autowired
    private IModuleBiz moduleBiz;

    @Test
    public void save(){
        String moduleCode = "82132221";
        ModuleEntity module = new ModuleEntity();
        module.setModuleTitle("模块1");
        module.setModuleCode(moduleCode);
//        module.setModuleModuleId(5);
        logger.debug("{}", moduleBiz.saveEntity(module));
        logger.debug("保存的ID: {}",module.getBaseTreeId());
    }

    @Test
    public void query(){
        //获取模块ID
        Integer moduleModuleId = null ;
        //获取查询条件
        Map<String,Object> whereMap = new HashMap<>();

        if(!StringUtil.isBlank(moduleModuleId)){
            whereMap.put("moduleModuleId",moduleModuleId);
        }
        //查询总条数
        int count = moduleBiz.countByMap(whereMap);
        List<ModuleEntity> moduleList = moduleBiz.queryListByMapPage(whereMap, new PageBean(1,count), null);
        for (ModuleEntity module: moduleList) {
            logger.debug("{} {} {}", module.getBaseTreeId() ,module.getModuleTitle(),module.getBaseTreeParentPath());
        }
    }

    @Test
    public void update(){
        ModuleEntity moduleEntity = (ModuleEntity) moduleBiz.getEntity(5);
        moduleEntity.setModuleTitle("新的标题");
        logger.debug("{}", moduleBiz.updateEntity(moduleEntity));
        logger.debug("{} {} {}", moduleEntity.getBaseTreeId() ,moduleEntity.getModuleTitle(),moduleEntity.getBaseTreeParentPath());
    }

    @Test
    public void delete(){
        int moduleId = 5;
        logger.debug("{}", moduleBiz.deleteEntity(moduleId));
    }
}
