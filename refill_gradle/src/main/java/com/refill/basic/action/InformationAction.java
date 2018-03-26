package com.refill.basic.action;

import com.alibaba.fastjson.JSONObject;
import com.refill.base.action.BaseAction;
import com.refill.base.bean.ResultJsonBean;
import com.refill.base.bean.ResultPageBean;
import com.refill.base.constant.e.BaseEnum;
import com.refill.base.constant.e.SessionEnum;
import com.refill.basic.biz.IInformationBiz;
import com.refill.basic.entity.InformationEntity;
import com.refill.people.entity.UserEntity;
import com.refill.util.DateUtil;
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
@RequestMapping("/${requestPath}/information")
public class InformationAction extends BaseAction {


    @Autowired
    private IInformationBiz informationBiz;

    @RequestMapping("/queryList")
    @ResponseBody
    public ResultJsonBean queryList(HttpServletRequest request, HttpServletResponse response) {
        //获取模块ID
        Integer informationInformationId = HttpServletUtil.getInt(request, "informationInformationId");
        //获取查询条件
        Map<String, Object> whereMap = new HashMap<>();
        if (!StringUtil.isBlank(informationInformationId)) {
            whereMap.put("informationInformationId", informationInformationId);
        }
        //查询总条数
        int count = informationBiz.countByMap(whereMap);
        List<?> informationList = informationBiz.queryListByMapPage(whereMap, HttpServletUtil.getPageBean(request, count), HttpServletUtil.getOrderBeanList(request, "orderList"));
        return new ResultJsonBean(null,new ResultPageBean(count, informationList), true);

    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public ResultJsonBean save(@ModelAttribute InformationEntity information, HttpServletRequest request, HttpServletResponse response) {
        //验证数据是否合法
        ResultJsonBean check = checkEntity(information);
        if (!check.isResult()) {
            return check;
        }
        //获取用户session
        UserEntity user = (UserEntity) HttpServletUtil.getSession(request, SessionEnum.USER_SESSION);
        int userId = user.getUserId();
        Date date = new Date();
        information.setInformationCreatePeopleId(userId);
        information.setInformationCreateDate(date);
        information.setInformationUpdatePeopleId(userId);
        information.setInformationUpdateDate(date);
        //进行保存
        return informationBiz.saveEntity(information);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public ResultJsonBean update(@ModelAttribute InformationEntity information, HttpServletRequest request, HttpServletResponse response) {
        //验证数据是否合法
        ResultJsonBean check = checkEntity(information);
        if (!check.isResult()) {
            return check;
        }
        //获取用户session
        UserEntity user = (UserEntity) HttpServletUtil.getSession(request, SessionEnum.USER_SESSION);
        int userId = user.getUserId();
        Date date = new Date();
        information.setInformationUpdatePeopleId(userId);
        information.setInformationUpdateDate(date);
        return informationBiz.updateEntity(information);
    }

    @RequestMapping("/{informationId}/delete")
    @ResponseBody
    public ResultJsonBean delete(@PathVariable int informationId, HttpServletRequest request, HttpServletResponse response) {
        if (StringUtil.isMaxZeroInteger(informationId)) {
            return informationBiz.deleteEntity(informationId);
        } else {
            return new ResultJsonBean(null, false, "没有获取到文章ID！");
        }
    }

    /**
     * 模块实体数据验证
     *
     * @return
     */
    private ResultJsonBean checkEntity(InformationEntity information) {
        if (information == null) {
            return new ResultJsonBean(null, false, "没有任何文章信息！");
        }
        if (StringUtil.isBlank(information.getInformationContent())) {
            return new ResultJsonBean(null, false, "文章必须有内容！");
        }
        if (StringUtil.isBlank(information.getInformationCategoryId())) {
            return new ResultJsonBean(null, false, "文章必须有分类！");
        }
        if (StringUtil.isBlank(information.getInformationTitle())) {
            return new ResultJsonBean(null, false, "文章标题不能为空！");
        }
        return new ResultJsonBean(null, true);
    }
}
