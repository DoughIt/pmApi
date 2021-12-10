package com.pm.pmapi.dao;

import com.pm.pmapi.dto.SimpleUserInfo;
import org.apache.ibatis.annotations.Param;

public interface UserDao {
    /**
     * 获取简单用户信息
     *
     * @param id
     * @return
     */
    SimpleUserInfo selectSimpleUserByPrimaryKey(@Param("id") Long id);

    /**
     * 更新用户id
     *
     * @param newId
     */
    void updateUserIdAndDeleteByPrimaryKey(@Param("newId") Long newId, @Param("id") Long id);

    /**
     * 更新用户nav
     * @param newNav
     * @param id
     * @param nav
     */
    void updateUserNavByPrimaryKey(@Param("newNav") Long newNav, @Param("id") Long id, @Param("nav") Long nav);
}
