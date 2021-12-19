package com.pm.pmapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Xinran Duan
 * @Description 自定义传入数据结构-标签信息
 * @date 2021-12-07
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TagEvaluateParam {
    private Long lessonId;
    private String tagName;
    private Integer evaluate;
}
