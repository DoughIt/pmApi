package com.pm.pmapi.dto;

import com.pm.pmapi.mbg.model.TabUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Jerry Zhang <https://github.com/doughit>
 * @Description Spring Security需要的用户详情
 * @Copyright DoughIt Studio - Powered By DoughIt
 * @date 2021-11-30 13:08
 */
public class AdminUserDetails implements UserDetails {
    private TabUser user;

    public AdminUserDetails(TabUser user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<SimpleGrantedAuthority>();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getId().toString();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return user.getStatus();
    }
}
