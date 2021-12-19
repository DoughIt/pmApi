package com.pm.pmapi.service;

import com.pm.pmapi.dto.TagInfo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface TagService {

    Integer createTag(Optional<Long> userId, String lessonNumber, String tagName, Boolean isPositive);

    TagInfo getTagByLessonIdAndTagName(Optional<Long> userId, Long lessonId, String tagName);

    List<TagInfo> listTagsOfLessonByLessonId(Optional<Long> userId, Long lessonId, Integer pageNum, Integer pageSize);

    Boolean evaluateTag(Optional<Long> userId, Long lessonId, String tagName, Integer evaluate);
}
