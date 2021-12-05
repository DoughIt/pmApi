package com.pm.pmapi.mbg.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description mbg自动生成tab_user表实体类
 *
 * @date 2021-12-04 11:14
 */
public class TabUser implements Serializable {
    /**
     * 自增主键
     *
     * @mbg.generated
     */
    private Long id;

    /**
     * 学号
     *
     * @mbg.generated
     */
    private String studentId;

    /**
     * wechat小程序用户id
     *
     * @mbg.generated
     */
    private String openId;

    /**
     * 昵称
     *
     * @mbg.generated
     */
    private String username;

    /**
     * 密码
     *
     * @mbg.generated
     */
    private String password;

    /**
     * 头像URL
     *
     * @mbg.generated
     */
    private String avatar;

    /**
     * 手机号
     *
     * @mbg.generated
     */
    private String phone;

    /**
     * 简介
     *
     * @mbg.generated
     */
    private String description;

    /**
     * 注册时间
     *
     * @mbg.generated
     */
    private Date registerTime;

    /**
     * 上次登录时间
     *
     * @mbg.generated
     */
    private Date loginTime;

    /**
     * 学邮认证情况，0未认证，1认证成功，2等待确认
     *
     * @mbg.generated
     */
    private String unthorized;

    /**
     * 账号状态，0禁用，1启用
     *
     * @mbg.generated
     */
    private Boolean status;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public String getUnthorized() {
        return unthorized;
    }

    public void setUnthorized(String unthorized) {
        this.unthorized = unthorized;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", studentId=").append(studentId);
        sb.append(", openId=").append(openId);
        sb.append(", username=").append(username);
        sb.append(", password=").append(password);
        sb.append(", avatar=").append(avatar);
        sb.append(", phone=").append(phone);
        sb.append(", description=").append(description);
        sb.append(", registerTime=").append(registerTime);
        sb.append(", loginTime=").append(loginTime);
        sb.append(", unthorized=").append(unthorized);
        sb.append(", status=").append(status);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}