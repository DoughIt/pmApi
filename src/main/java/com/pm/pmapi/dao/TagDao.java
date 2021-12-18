package com.pm.pmapi.dao;

import com.pm.pmapi.dto.TagInfo;
import org.apache.ibatis.annotations.Param;

public interface TagDao {
    TagInfo getTagInfoByTagId(@Param("tagId") Long tagId);
}
