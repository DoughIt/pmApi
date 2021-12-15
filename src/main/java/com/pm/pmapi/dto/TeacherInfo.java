package com.pm.pmapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Xinran Duan
 * @Description 自定义返回结构-教师信息
 * @date 2021-12-07
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TeacherInfo {
    private Long teacherId;
    private String teacherName;
}
