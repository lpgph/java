package com.refill.basic.action;

import com.alibaba.fastjson.JSONObject;
import com.refill.base.action.BaseAction;
import com.refill.base.bean.ResultJsonBean;
import com.refill.base.bean.ResultPageBean;
import com.refill.basic.biz.IRoleBiz;
import com.refill.basic.entity.RoleEntity;
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
@RequestMapping("/${requestPath}/role")
public class RoleAction extends BaseAction {

    @Autowired
    private IRoleBiz roleBiz;

    @RequestMapping("/queryList")
    @ResponseBody
    public ResultJsonBean queryList(HttpServletRequest request, HttpServletResponse response) {
        //获取模块ID
        Integer roleRoleId = HttpServletUtil.getInt(request, "roleRoleId");
        //获取查询条件
        Map<String, Object> whereMap = new HashMap<>();
        if (!StringUtil.isBlank(roleRoleId)) {
            whereMap.put("roleRoleId", roleRoleId);
        }
        //查询总条数
        int count = roleBiz.countByMap(whereMap);
        List<?> roleList = roleBiz.queryListByMapPage(whereMap, HttpServletUtil.getPageBean(request, count), HttpServletUtil.getOrderBeanList(request, "orderList"));
        return new ResultJsonBean(null, new ResultPageBean(count, roleList), true);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public ResultJsonBean save(@ModelAttribute RoleEntity role, HttpServletRequest request, HttpServletResponse response) {
        //验证数据是否合法
        ResultJsonBean check = checkEntity(role);
        if (!check.isResult()) {
            return check;
        }
        //进行保存
        return roleBiz.saveEntity(role);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public ResultJsonBean update(@ModelAttribute RoleEntity role, HttpServletRequest request, HttpServletResponse response) {
        //验证数据是否合法
        //验证数据是否合法
        ResultJsonBean check = checkEntity(role);
        if (!check.isResult()) {
            return check;
        }
        return roleBiz.updateEntity(role);
    }

    @RequestMapping("/{roleId}/delete")
    @ResponseBody
    public ResultJsonBean delete(@PathVariable int roleId, HttpServletRequest request, HttpServletResponse response) {
        Boolean isForce = HttpServletUtil.getBoolean(request, "isForce");
        if (StringUtil.isMaxZeroInteger(roleId)) {
           return roleBiz.deleteEntity(roleId, isForce);
        } else {
            return new ResultJsonBean(null, false, "角色ID必须大于0！");
        }
    }

    /**
     * 角色实体数据验证
     *
     * @return true或false
     */
    private ResultJsonBean checkEntity(RoleEntity role) {
        if (role == null) {
            return new ResultJsonBean(null, false, "找不到角色信息！");
        }
        if (StringUtil.isBlank(role.getRoleName())) {
            return new ResultJsonBean(null, false, "角色名称不能为空！");
        }
        return new ResultJsonBean(null, true);
    }
}
