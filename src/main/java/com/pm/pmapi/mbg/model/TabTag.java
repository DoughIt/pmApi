package com.pm.pmapi.mbg.model;

import java.io.Serializable;

/**
 * @Description mbg自动生成tab_tag表实体类
 *
 * @date 2021-12-15 08:43
 */
public class TabTag implements Serializable {
    private String tagId;

    private String tag;

    private Integer positive;

    private Integer negative;

    private static final long serialVersionUID = 1L;

    public String getTagId() {
        return tagId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Integer getPositive() {
        return positive;
    }

    public void setPositive(Integer positive) {
        this.positive = positive;
    }

    public Integer getNegative() {
        return negative;
    }

    public void setNegative(Integer negative) {
        this.negative = negative;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", tagId=").append(tagId);
        sb.append(", tag=").append(tag);
        sb.append(", positive=").append(positive);
        sb.append(", negative=").append(negative);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}