package com.pm.pmapi.dto;

import com.pm.pmapi.mbg.model.TabUser;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

/**
 * @author Jerry Zhang <https://github.com/doughit>
 * @Description 自定义帖子信息
 * @Copyright DoughIt Studio - Powered By DoughIt
 * @date 2021-12-06 08:53
 */
@Setter
@Getter
public class TopicInfo {
    /**
     * 帖子id
     */
    private Long id;
    /**
     * 本帖回复的帖子id
     */
    private Long parentId;
    /**
     * 帖子标题
     */
    private String title;
    /**
     * 帖子内容
     */
    private String content;
    /**
     * 发帖人信息
     */
    private TabUser user;
    /**
     * 帖子发布时间
     */
    private Date issueTime;
    /**
     * 帖子状态，1->有效的，-1->已删除，-2->已屏蔽……
     */
    private Integer state;
    /**
     * 回帖列表
     */
    private List<TopicInfo> children;
}
