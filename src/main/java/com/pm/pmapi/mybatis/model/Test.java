package com.pm.pmapi.mybatis.model;

import java.io.Serializable;

/**
 * @Description mbg自动生成test表实体类
 *
 * @date 2021-10-12 09:22
 */
public class Test implements Serializable {
    /**
     * 主键
     *
     * @mbg.generated
     */
    private Integer id;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}