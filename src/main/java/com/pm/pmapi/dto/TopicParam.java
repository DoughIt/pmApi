package com.pm.pmapi.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

/**
 * @Description 自定义发帖参数
 *
 * @Copyright DoughIt Studio - Powered By DoughIt
 * @author Jerry Zhang <https://github.com/doughit>
 * @date 2021-12-06 10:01
 */
@Getter
@Setter
public class TopicParam {
    /**
     * 课程id，若为课程帖子则填写
     */
    private Long lessonId;
    /**
     * 商品id，若为商品帖子则填写
     */
    private Long goodsId;
    /**
     * 帖子id，若为回复别人的帖子则填写被回复帖子的id
     */
    private Long topicId;
    /**
     * 帖子标题
     */
    private String title;
    /**
     * 帖子内容，非空
     */
    @NotEmpty
    private String content;
}
