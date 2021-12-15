package com.pm.pmapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Xinran Duan
 * @Description 自定义课程参数
 * @date 2021-12-07
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LessonParam {
    private Long id;
    private String lessonName;
    private String lessonId;
    private String schoolName;
    private Integer credit;
    private String teacherName;
    private String semester;
    private Double score;
}
