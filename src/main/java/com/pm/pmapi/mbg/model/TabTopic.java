package com.pm.pmapi.mbg.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description mbg自动生成tab_topic表实体类
 *
 * @date 2021-12-27 04:44
 */
public class TabTopic implements Serializable {
    private Long id;

    /**
     * 父贴id
     *
     * @mbg.generated
     */
    private Long parentId;

    private Long lessonId;

    private Long goodsId;

    /**
     * 帖子所属模块，0->公共模块，1->课程模块，2->商品模块
     *
     * @mbg.generated
     */
    private Integer relatedType;

    /**
     * 发帖人
     *
     * @mbg.generated
     */
    private Long userId;

    /**
     * 标题
     *
     * @mbg.generated
     */
    private String title;

    /**
     * 内容
     *
     * @mbg.generated
     */
    private String content;

    /**
     * 发帖时间
     *
     * @mbg.generated
     */
    private Date issueTime;

    /**
     * 帖子状态，0->正常，1->已删除，2->已屏蔽
     *
     * @mbg.generated
     */
    private Integer state;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Long getLessonId() {
        return lessonId;
    }

    public void setLessonId(Long lessonId) {
        this.lessonId = lessonId;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getRelatedType() {
        return relatedType;
    }

    public void setRelatedType(Integer relatedType) {
        this.relatedType = relatedType;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getIssueTime() {
        return issueTime;
    }

    public void setIssueTime(Date issueTime) {
        this.issueTime = issueTime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", parentId=").append(parentId);
        sb.append(", lessonId=").append(lessonId);
        sb.append(", goodsId=").append(goodsId);
        sb.append(", relatedType=").append(relatedType);
        sb.append(", userId=").append(userId);
        sb.append(", title=").append(title);
        sb.append(", content=").append(content);
        sb.append(", issueTime=").append(issueTime);
        sb.append(", state=").append(state);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}