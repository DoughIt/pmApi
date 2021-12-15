package com.pm.pmapi.mbg.model;

import java.io.Serializable;

/**
 * @Description mbg自动生成tab_lesson表实体类
 *
 * @date 2021-12-15 06:18
 */
public class TabLesson implements Serializable {
    private Long id;

    private String lessonName;

    private Integer credit;

    private Long teacherId;

    private String schoolId;

    private String semesterId;

    private Long score;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    public Integer getCredit() {
        return credit;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }

    public String getSemesterId() {
        return semesterId;
    }

    public void setSemesterId(String semesterId) {
        this.semesterId = semesterId;
    }

    public Long getScore() {
        return score;
    }

    public void setScore(Long score) {
        this.score = score;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", lessonName=").append(lessonName);
        sb.append(", credit=").append(credit);
        sb.append(", teacherId=").append(teacherId);
        sb.append(", schoolId=").append(schoolId);
        sb.append(", semesterId=").append(semesterId);
        sb.append(", score=").append(score);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}