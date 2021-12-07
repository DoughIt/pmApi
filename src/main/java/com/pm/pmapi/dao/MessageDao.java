package com.pm.pmapi.dao;

import com.pm.pmapi.dto.MessageSummaryInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Jerry Zhang <https://github.com/doughit>
 * @Description 自定义消息dao
 * @Copyright DoughIt Studio - Powered By DoughIt
 * @date 2021-12-07 11:32
 */
public interface MessageDao {
    List<MessageSummaryInfo> listMessageSummariesByUserId(@Param("userId") Long userId);
}
