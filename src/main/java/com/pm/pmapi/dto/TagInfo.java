package com.pm.pmapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Transient;

/**
 * @author Xinran Duan
 * @Description 自定义返回结构-标签信息
 * @date 2021-12-07
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TagInfo {
    private Long lessonId;
    private Long tagId;

    private Long positive;
    private Long negative;

    @Transient
    private String tagName;
    @Transient
    private Boolean positiveSelected;
    @Transient
    private Boolean negativeSelected;
}
