package com.pm.pmapi.service;

import com.pm.pmapi.dto.UpdateUserParam;
import com.pm.pmapi.dto.UserParam;
import com.pm.pmapi.mbg.model.TabUser;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author Jerry Zhang <https://github.com/doughit>
 * @Description 用户管理服务
 * @Copyright DoughIt Studio - Powered By DoughIt
 * @date 2021-11-29 10:39
 */
public interface UserService {

    /**
     * 注册用户
     *
     * @param userParam 用户信息
     * @return 1操作成功，0操作失败
     */
    int register(UserParam userParam);

    /**
     * 用户登录
     *
     * @param userParam 用户信息
     * @return 生成的Jwt token
     */
    String login(UserParam userParam);

    /**
     * 刷新Token
     *
     * @param oldToken 旧的Token
     * @return 新的Token
     */
    String refreshToken(String oldToken);

    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    int delete(int id);

    /**
     * 更新用户信息
     *
     * @param user
     * @return
     */
    int update(TabUser user);

    /**
     * 更新用户密码
     *
     * @param userParam
     * @return
     */
    int updatePassword(UpdateUserParam userParam);

    /**
     * 获取用户信息
     *
     * @param id
     * @return
     */
    UserDetails loadUserById(int id);

    /**
     * 从数据库获取用户信息
     *
     * @param id
     * @return
     */
    TabUser getUserById(int id);
}
