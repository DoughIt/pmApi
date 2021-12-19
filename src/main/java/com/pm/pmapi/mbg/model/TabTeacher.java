package com.pm.pmapi.mbg.model;

import java.io.Serializable;

/**
 * @Description mbg自动生成tab_teacher表实体类
 *
 * @date 2021-12-18 07:29
 */
public class TabTeacher implements Serializable {
    private Long teacherId;

    private String teacherName;

    private Long schoolId;

    private static final long serialVersionUID = 1L;

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public Long getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Long schoolId) {
        this.schoolId = schoolId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", teacherId=").append(teacherId);
        sb.append(", teacherName=").append(teacherName);
        sb.append(", schoolId=").append(schoolId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}