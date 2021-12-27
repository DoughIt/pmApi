package com.pm.pmapi.mbg.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description mbg自动生成tab_message表实体类
 *
 * @date 2021-12-27 04:44
 */
public class TabMessage implements Serializable {
    private Long id;

    /**
     * 发信人id
     *
     * @mbg.generated
     */
    private Long senderId;

    /**
     * 收信人id
     *
     * @mbg.generated
     */
    private Long receiverId;

    /**
     * 内容
     *
     * @mbg.generated
     */
    private String content;

    /**
     * 发信时间
     *
     * @mbg.generated
     */
    private Date issueTime;

    /**
     * 收信时间
     *
     * @mbg.generated
     */
    private Date readTime;

    /**
     * 是否已读，0->未读，1->已读
     *
     * @mbg.generated
     */
    private Boolean readStatus;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    public Long getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Long receiverId) {
        this.receiverId = receiverId;
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

    public Date getReadTime() {
        return readTime;
    }

    public void setReadTime(Date readTime) {
        this.readTime = readTime;
    }

    public Boolean getReadStatus() {
        return readStatus;
    }

    public void setReadStatus(Boolean readStatus) {
        this.readStatus = readStatus;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", senderId=").append(senderId);
        sb.append(", receiverId=").append(receiverId);
        sb.append(", content=").append(content);
        sb.append(", issueTime=").append(issueTime);
        sb.append(", readTime=").append(readTime);
        sb.append(", readStatus=").append(readStatus);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}