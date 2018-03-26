package com.refill.basic.action;

import com.alibaba.fastjson.JSONObject;
import com.refill.base.action.BaseAction;
import com.refill.base.bean.ResultJsonBean;
import com.refill.base.bean.ResultPageBean;
import com.refill.base.constant.e.SessionEnum;
import com.refill.basic.biz.ICommentBiz;
import com.refill.basic.entity.CommentEntity;
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
 * @ClassName CommentAction
 * @Description 类别控制层
 * @date 17/05/02
 */
@Controller
@RequestMapping("/${requestPath}/comment")
public class CommentAction extends BaseAction {

    @Autowired
    private ICommentBiz commentBiz;

    @RequestMapping("/queryList")
    @ResponseBody
    public ResultJsonBean queryList(HttpServletRequest request, HttpServletResponse response) {
        //获取模块ID
        Integer commentCommentId = HttpServletUtil.getInt(request,"commentCommentId");
        //获取查询条件
        Map<String,Object> whereMap = new HashMap<>();
        if(!StringUtil.isBlank(commentCommentId)){
            whereMap.put("commentCommentId",commentCommentId);
        }
        //查询总条数
        int count = commentBiz.countByMap(whereMap);
        List<?> commentList = commentBiz.queryListByMapPage(whereMap, HttpServletUtil.getPageBean(request,count), HttpServletUtil.getOrderBeanList(request,"orderList"));
        return new ResultJsonBean(null,new ResultPageBean(count,commentList),true);

    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public ResultJsonBean save(@ModelAttribute CommentEntity comment, HttpServletRequest request, HttpServletResponse response) {
        //验证数据是否合法
        ResultJsonBean check = checkEntity(comment);
        if(!check.isResult()){
            return check;
        }
        //获取用户session(用户登录后保存的)
        UserEntity user = (UserEntity) HttpServletUtil.getSession(request, SessionEnum.USER_SESSION);
        int userId = user.getUserId();
        Date date = new Date();
        comment.setCommentCreateUserId(userId);
        comment.setCommentCreateDate(date);
        //进行保存
        return commentBiz.saveEntity(comment);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public ResultJsonBean update(@ModelAttribute CommentEntity comment, HttpServletRequest request, HttpServletResponse response) {
        //验证数据是否合法
        ResultJsonBean check = checkEntity(comment);
        if(!check.isResult()){
            return check;
        }
        return commentBiz.updateEntity(comment);
    }

    @RequestMapping("/{commentId}/delete")
    @ResponseBody
    public ResultJsonBean delete(@PathVariable int commentId, HttpServletRequest request, HttpServletResponse response) {
        boolean isForce =HttpServletUtil.getBoolean(request,"isForce");
        if(StringUtil.isMaxZeroInteger(commentId)){
            return commentBiz.deleteEntity(commentId,isForce);
        }else {
            return new ResultJsonBean(null,false,"id不大于0！");
        }
    }

    /**
     * 模块实体数据验证
     * @return
     */
    private ResultJsonBean checkEntity(CommentEntity comment){
        if(comment==null){
            return new ResultJsonBean(null,false,"实体不能为null");
        }
        if(StringUtil.isBlank(comment.getCommentContent())){
            return new ResultJsonBean(null,false,"评论内容不能为null");
        }
        return new ResultJsonBean(null,true);
    }
}
