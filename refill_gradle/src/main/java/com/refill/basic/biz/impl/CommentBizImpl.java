package com.refill.basic.biz.impl;

import com.refill.base.biz.impl.BaseTreeBizImpl;
import com.refill.base.dao.IBaseTreeDao;
import com.refill.basic.biz.ICommentBiz;
import com.refill.basic.dao.ICommentDao;
import com.refill.basic.entity.CommentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Peter Guo
 * @version 0.1
 * @ClassName CommentBizImpl
 * @Description 评论业务层实现类
 * @date 17/05/02
 */
@Service("commentBiz")
public class CommentBizImpl extends BaseTreeBizImpl<CommentEntity> implements ICommentBiz {

    /**
     * 注入评论持久化层接口
     */
    @Autowired
    private ICommentDao commentDao;

    @Override
    protected IBaseTreeDao<CommentEntity> getBaseTreeDao() {
        return commentDao;
    }

}
