package com.refill.basic.action;

import com.alibaba.fastjson.JSONObject;
import com.refill.base.action.BaseAction;
import com.refill.base.bean.ResultJsonBean;
import com.refill.base.bean.ResultPageBean;
import com.refill.basic.biz.IModuleBiz;
import com.refill.basic.entity.ModuleEntity;
import com.refill.util.DateUtil;
import com.refill.util.HttpServletUtil;
import com.refill.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Peter Guo
 * @version 0.1
 * @ClassName ModuleAction
 * @Description 模块控制层
 * @date 17/05/02
 */
@Controller
@RequestMapping("/${requestPath}/module")
public class ModuleAction extends BaseAction {

    /**
     * 注入模块业务层
     */
    @Autowired
    private IModuleBiz moduleBiz;


    /**
     * 模块实体数据验证
     * @return
     */
    private ResultJsonBean checkEntity(ModuleEntity module){
        if(module==null){
            return new ResultJsonBean(null, false, "没有任何模块信息！");
        }
        if(StringUtil.isBlank(module.getModuleCode())){
            return new ResultJsonBean(null, false, "模块编码不能为空！");
        }
        if(StringUtil.isBlank(module.getModuleTitle())){
            return new ResultJsonBean(null, false, "模块标题不能为空！");
        }
        return new ResultJsonBean(null, true);
    }


    /**
     * 查询模块集合
     * @param request
     * @param response
     */
    @RequestMapping("/queryList")
    @ResponseBody
    public ResultJsonBean queryList(HttpServletRequest request, HttpServletResponse response) {
        //获取模块ID
        Integer moduleModuleId = HttpServletUtil.getInt(request,"moduleModuleId");
        //获取查询条件
        Map<String,Object> whereMap = new HashMap<>();
        if(!StringUtil.isBlank(moduleModuleId)){
            whereMap.put("moduleModuleId",moduleModuleId);
        }
        //查询总条数
        int count = moduleBiz.countByMap(whereMap);
        List<?> moduleList = moduleBiz.queryListByMapPage(whereMap, HttpServletUtil.getPageBean(request,count), HttpServletUtil.getOrderBeanList(request,"orderList"));
        return new ResultJsonBean(null, new ResultPageBean(count, moduleList), true);

    }

    /**
     * 保存模块实体
     * @param moduleEntity 模块实体
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public ResultJsonBean save(@ModelAttribute ModuleEntity moduleEntity, HttpServletRequest request, HttpServletResponse response) {
        //验证数据是否合法
        ResultJsonBean check = checkEntity(moduleEntity);
        if (!check.isResult()) {
            return check;
        }
        ModuleEntity getModule = moduleBiz.getModuleEntity(moduleEntity.getModuleCode());
        if(getModule!=null){
            return new ResultJsonBean(null, false,"模块已经存在！！");
        }
        //进行保存
        return moduleBiz.saveEntity(moduleEntity);
    }


    /**
     * 更新模块实体
     * @param moduleEntity 模块实体
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public ResultJsonBean update(@ModelAttribute ModuleEntity moduleEntity, HttpServletRequest request, HttpServletResponse response) {
        //验证数据是否合法
        ResultJsonBean check = checkEntity(moduleEntity);
        if (!check.isResult()) {
            return check;
        }
        //查询模块是否存在
        ModuleEntity oldEntity = (ModuleEntity) moduleBiz.getEntity(moduleEntity.getBaseTreeId());
        if(oldEntity == null){
            return new ResultJsonBean(null, false,"不存在该模块！");
        }
        return moduleBiz.updateEntity(moduleEntity);
    }

    /**
     * 根据模块ID删除模块实体
     * @param moduleId 模块ID
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     */
    @RequestMapping("/{moduleId}/delete")
    @ResponseBody
    public ResultJsonBean delete(@PathVariable int moduleId, HttpServletRequest request, HttpServletResponse response) {
        Boolean isForce =HttpServletUtil.getBoolean(request,"isForce");
        if(StringUtil.isMaxZeroInteger(moduleId)){
            return moduleBiz.deleteEntity(moduleId,isForce);
        }else {
            return new ResultJsonBean(null, false,"模块ID不能为0");
        }
    }

}
