package com.refill.basic.action;

import com.alibaba.fastjson.JSONObject;
import com.refill.base.action.BaseAction;
import com.refill.base.bean.ResultJsonBean;
import com.refill.base.bean.ResultPageBean;
import com.refill.base.constant.e.SessionEnum;
import com.refill.basic.biz.ICategoryBiz;
import com.refill.basic.entity.CategoryEntity;
import com.refill.people.entity.UserEntity;
import com.refill.util.HttpServletUtil;
import com.refill.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
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
@RequestMapping("/${requestPath}/category")
public class CategoryAction extends BaseAction {

    /**
     * 注入类别业务层实现类
     */
    @Autowired
    private ICategoryBiz categoryBiz;

    /**
     * 根据模块ID查询分类
     * @param moduleId
     * @param request
     * @param response
     */
    @RequestMapping("/{moduleId}/queryList")
    @ResponseBody
    public ResultJsonBean queryList(@PathVariable int moduleId, HttpServletRequest request, HttpServletResponse response) {
        //获取父级分类ID
        Integer categoryCategoryId = HttpServletUtil.getInt(request,"categoryCategoryId");
        //获取查询条件
        Map<String,Object> whereMap = new HashMap<>();
        whereMap.put("categoryModuleId",moduleId);
        if(!StringUtil.isBlank(categoryCategoryId)){
            //查询模块是否存在
            CategoryEntity category = (CategoryEntity) categoryBiz.getEntity(categoryCategoryId);
            if(category == null){
                return new ResultJsonBean(null,false);
            }
            whereMap.put("categoryCategoryId",categoryCategoryId);
            whereMap.put("categoryPath",category.getChildsPathPrefix());
        }
        //查询总条数
        int count = categoryBiz.countByMap(whereMap);
        List<?> categoryList = categoryBiz.queryListByMapPage(whereMap, HttpServletUtil.getPageBean(request,count), HttpServletUtil.getOrderBeanList(request,"orderList"));

        return new ResultJsonBean(null,new ResultPageBean(count,categoryList),true);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public ResultJsonBean save(@ModelAttribute CategoryEntity category, HttpServletRequest request, HttpServletResponse response) {
        //验证数据是否合法
        ResultJsonBean check = checkEntity(category);
        if(!check.isResult()){
            return check;
        }
        //获取用户session(用户登录后保存的)
        UserEntity user = (UserEntity) HttpServletUtil.getSession(request, SessionEnum.USER_SESSION);
        int userId = user.getUserId();
        Date date = new Date();
        category.setCategoryCreatePeopleId(userId);
        category.setCategoryCreateDate(date);
        category.setCategoryUpdatePeopleId(userId);
        category.setCategoryUpdateDate(date);

        //进行保存
        return categoryBiz.saveEntity(category);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public ResultJsonBean update(@ModelAttribute CategoryEntity category, HttpServletRequest request, HttpServletResponse response) {
        //验证数据是否合法
        ResultJsonBean check = checkEntity(category);
        if(!check.isResult()){
            return check;
        }
        //获取用户session
        UserEntity user = (UserEntity) HttpServletUtil.getSession(request, SessionEnum.USER_SESSION);
        int userId = user.getUserId();
        Date date = new Date();
        category.setCategoryUpdatePeopleId(userId);
        category.setCategoryUpdateDate(date);
        return categoryBiz.updateEntity(category);
    }

    @RequestMapping("/{categoryId}/delete")
    @ResponseBody
    public ResultJsonBean delete(@PathVariable int categoryId, HttpServletRequest request, HttpServletResponse response) {
        Boolean isForce =HttpServletUtil.getBoolean(request,"isForce");
        if(StringUtil.isMaxZeroInteger(categoryId)){
            return categoryBiz.deleteEntity(categoryId,isForce);

        }else {
            return new ResultJsonBean(null,false,"id不大于0！");
        }
    }

    /**
     * 模块实体数据验证
     * @return
     */
    private ResultJsonBean checkEntity(CategoryEntity category){
        //实体不能为空
        if(category==null){
            return new ResultJsonBean(null,false,"实体不能为null");
        }
        //分类标题不能为空
        if(StringUtil.isBlank(category.getCategoryTitle())){
            return new ResultJsonBean(null,false,"标题不能为空");
        }
        return new ResultJsonBean(null,true);
    }

}
