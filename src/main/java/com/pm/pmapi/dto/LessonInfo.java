package com.pm.pmapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Transient;

import java.util.List;

/**
 * @author Xinran Duan
 * @Description 自定义返回结构-课程信息
 * @date 2021-12-07
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LessonInfo {

    private Long lessonId;
    private String lessonName;
    private String lessonNumber;
    private Double credit;
    private String semester;
    private Double score;

    private Long schoolId;
    private Long teacherId;

    @Transient
    private String schoolName;

    @Transient
    private String teacherName;

    @Transient
    private Boolean collected;

    @Transient
    private List<String> pictures;
}
