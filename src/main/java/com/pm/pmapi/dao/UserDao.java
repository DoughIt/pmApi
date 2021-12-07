package com.pm.pmapi.dao;

import com.pm.pmapi.dto.SimpleUserInfo;
import org.apache.ibatis.annotations.Param;

public interface UserDao {
    /**
     * 获取简单用户信息
     * @param id
     * @return
     */
    SimpleUserInfo selectSimpleUserByPrimaryKey(@Param("id") Long id);
}
