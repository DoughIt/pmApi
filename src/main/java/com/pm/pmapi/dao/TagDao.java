package com.pm.pmapi.dao;

import com.pm.pmapi.dto.TagInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TagDao {
    TagInfo getTagInfoByTagIdAndLessonId(@Param("tagId") Long tagId, @Param("lessonId") Long lessonId);
    List<TagInfo> listTagInfoByTLessonId(@Param("lessonId") Long lessonId);
}
