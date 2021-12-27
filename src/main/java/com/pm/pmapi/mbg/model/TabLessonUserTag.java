package com.pm.pmapi.mbg.model;

import java.io.Serializable;

/**
 * @Description mbg自动生成tab_lesson_user_tag表实体类
 *
 * @date 2021-12-27 04:44
 */
public class TabLessonUserTag implements Serializable {
    private Long id;

    private Long tagId;

    private Long userId;

    private Long lessonId;

    private Boolean positiveselected;

    private Boolean negetiveselected;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTagId() {
        return tagId;
    }

    public void setTagId(Long tagId) {
        this.tagId = tagId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getLessonId() {
        return lessonId;
    }

    public void setLessonId(Long lessonId) {
        this.lessonId = lessonId;
    }

    public Boolean getPositiveselected() {
        return positiveselected;
    }

    public void setPositiveselected(Boolean positiveselected) {
        this.positiveselected = positiveselected;
    }

    public Boolean getNegetiveselected() {
        return negetiveselected;
    }

    public void setNegetiveselected(Boolean negetiveselected) {
        this.negetiveselected = negetiveselected;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", tagId=").append(tagId);
        sb.append(", userId=").append(userId);
        sb.append(", lessonId=").append(lessonId);
        sb.append(", positiveselected=").append(positiveselected);
        sb.append(", negetiveselected=").append(negetiveselected);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}