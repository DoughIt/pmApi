package com.pm.pmapi.dao;

import com.pm.pmapi.dto.SchoolInfo;
import org.apache.ibatis.annotations.Param;

public interface SchoolDao {

    SchoolInfo getSchoolBySchoolId(@Param("schoolId") Long schoolId);
}
