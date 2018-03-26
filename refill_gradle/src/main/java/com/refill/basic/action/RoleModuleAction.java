package com.refill.basic.action;

import com.alibaba.fastjson.JSONObject;
import com.refill.base.action.BaseAction;
import com.refill.base.bean.ResultJsonBean;
import com.refill.base.bean.ResultPageBean;
import com.refill.basic.biz.IRoleBiz;
import com.refill.basic.biz.IRoleModuleBiz;
import com.refill.basic.biz.impl.RoleModuleBizImpl;
import com.refill.basic.entity.CategoryEntity;
import com.refill.basic.entity.RoleEntity;
import com.refill.basic.entity.RoleModuleEntity;
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
 * @ClassName CategoryAction
 * @Description 类别控制层
 * @date 17/05/02
 */
@Controller
@RequestMapping("/${requestPath}/roleModule")
public class RoleModuleAction  extends BaseAction {

    @Autowired
    private IRoleModuleBiz roleModuleBiz;

    @Autowired
    private IRoleBiz roleBiz;


    @RequestMapping("/{roleId}/queryList")
    @ResponseBody
    public ResultJsonBean queryList(@PathVariable int roleId,HttpServletRequest request, HttpServletResponse response) {
        RoleEntity role = (RoleEntity) roleBiz.getEntity(roleId);
        if(role == null){
            return new ResultJsonBean(null, false,"模块已经存在！！");
        }
        //查询总条数
        int count = roleModuleBiz.countByRoleId(roleId);
        List<RoleModuleEntity> roleModuleList = roleModuleBiz.queryListByRolePage(roleId, HttpServletUtil.getPageBean(request,count), HttpServletUtil.getOrderBeanList(request,"orderList"));

        return new ResultJsonBean(null,new ResultPageBean(count, roleModuleList), true);

    }

    @RequestMapping(value = "/{roleId}/save", method = RequestMethod.POST)
    @ResponseBody
    public ResultJsonBean saveBatch(@PathVariable int roleId, HttpServletRequest request, HttpServletResponse response) {
        if(!StringUtil.isMaxZeroInteger(roleId)){
            return new ResultJsonBean(null, false,"角色ID必须大于0！！");
        }
        RoleEntity role = (RoleEntity) roleBiz.getEntity(roleId);
        if(role != null ){
            return new ResultJsonBean(null, false,"没有该角色！！");
        }
        String moduleIds = request.getParameter("moduleIds");
        if(StringUtil.isBlank(moduleIds)){
            return new ResultJsonBean(null, false,"没有获取到模块ID！！");
        }
        List<Integer> list =  JSONObject.parseArray(moduleIds,Integer.class);
        if(StringUtil.isMaxZeroInteger(list.size())){
            return new ResultJsonBean(null, false,"没有找到模块ID集合！！");
        }
//        roleModuleBiz.saveBatchById(roleId,list);
        return new ResultJsonBean(null, true);
    }

    @RequestMapping(value = "/{roleId}/update", method = RequestMethod.POST)
    @ResponseBody
    public ResultJsonBean updateBatch(@PathVariable int roleId, HttpServletRequest request, HttpServletResponse response) {

        if(!StringUtil.isMaxZeroInteger(roleId)){
            return new ResultJsonBean(null, false,"角色ID必须大于0！！");
        }
        RoleEntity role = (RoleEntity) roleBiz.getEntity(roleId);
        if(role != null ){
            return new ResultJsonBean(null, false,"没有该角色！！");
        }
        String moduleIds = request.getParameter("moduleIds");
        if(StringUtil.isBlank(moduleIds)){
            return new ResultJsonBean(null, false,"没有获取到模块ID！！");
        }
        List<Integer> list =  JSONObject.parseArray(moduleIds,Integer.class);
        if(StringUtil.isMaxZeroInteger(list.size())){
            return new ResultJsonBean(null, false,"没有找到模块ID集合！！");
        }
//        roleModuleBiz.updateBatchById(roleId,list);
        return new ResultJsonBean(null, true);
    }
}
