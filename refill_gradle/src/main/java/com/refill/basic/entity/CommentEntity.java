package com.refill.basic.entity;

import com.refill.base.constant.Const;
import com.refill.base.entity.BaseEntity;
import com.refill.base.entity.BaseTreeEntity;

import java.util.Date;

/**
 * @author Peter Guo
 * @version 0.1
 * @ClassName CommentEntity
 * @Description 评论实体类
 * @date 17/05/02
 */
public class CommentEntity extends BaseTreeEntity {

    /**
     * 评论内容
     */
    private String commentContent;

    /**
     * 评论的类型：1 匿名，2 公开，默认为公开
     */
    private int commentType;

    /**
     * 评论模块ID
     */
    private int commentModuleId;

    /**
     * 评论的主体ID,如文章,商品
     */
    private int commentMainId;


    /**
     * 评分
     */
    private int commentScore;


    /**
     * 评论用户ID
     */
    private int commentCreateUserId;


    /**
     * 创建时间
     */
    private Date commentCreateDate;


    /**
     * 获取 评论内容
     *
     * @return commentContent
     */
    public String getCommentContent() {
        return commentContent;
    }

    /**
     * 设置 评论内容
     *
     * @param commentContent 评论内容
     */
    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    /**
     * 获取 评论的类型：1 匿名，2 公开，默认为公开
     *
     * @return commentType
     */
    public int getCommentType() {
        return commentType;
    }

    /**
     * 设置 评论的类型：1 匿名，2 公开，默认为公开
     *
     * @param commentType 评论的类型：1 匿名，2 公开，默认为公开
     */
    public void setCommentType(int commentType) {
        this.commentType = commentType;
    }

    /**
     * 获取 评论的模块ID
     *
     * @return commentModuleId
     */
    public int getCommentModuleId() {
        return commentModuleId;
    }

    /**
     * 设置 评论的模块ID
     *
     * @param commentModuleId 评论的模块ID
     */
    public void setCommentModuleId(int commentModuleId) {
        this.commentModuleId = commentModuleId;
    }

    /**
     * 获取 评论的主体ID,如文章,商品
     *
     * @return commentMainId
     */
    public int getCommentMainId() {
        return commentMainId;
    }

    /**
     * 设置 评论的主体ID,如文章,商品
     *
     * @param commentMainId 评论的主体ID,如文章,商品
     */
    public void setCommentMainId(int commentMainId) {
        this.commentMainId = commentMainId;
    }

    /**
     * 获取 评分
     *
     * @return commentScore
     */
    public int getCommentScore() {
        return commentScore;
    }

    /**
     * 设置 评分
     *
     * @param commentScore 评分
     */
    public void setCommentScore(int commentScore) {
        this.commentScore = commentScore;
    }

    /**
     * 获取 评论用户ID,ID为0则为后台管理员回复
     *
     * @return commentCreateUserId
     */
    public int getCommentCreateUserId() {
        return commentCreateUserId;
    }

    /**
     * 设置 评论用户ID,ID为0则为后台管理员回复
     *
     * @param commentCreateUserId 评论用户ID,ID为0则为后台管理员回复
     */
    public void setCommentCreateUserId(int commentCreateUserId) {
        this.commentCreateUserId = commentCreateUserId;
    }

    /**
     * 获取 创建时间
     *
     * @return commentCreateDate
     */
    public Date getCommentCreateDate() {
        return commentCreateDate;
    }

    /**
     * 设置 创建时间
     *
     * @param commentCreateDate 创建时间
     */
    public void setCommentCreateDate(Date commentCreateDate) {
        this.commentCreateDate = commentCreateDate;
    }
}