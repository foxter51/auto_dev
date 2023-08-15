package com.ua.kpi.developmentautomation.security;

import com.ua.kpi.developmentautomation.entities.AppUser;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@RequiredArgsConstructor
@EqualsAndHashCode
public class UserDetailsImpl implements UserDetails {
    
    private static long serialVersionUID = 1L;
    private final Long id;
    private final String username;
    private final String email;
    private final String password;

    public static UserDetailsImpl build(AppUser user) {

        return new UserDetailsImpl(user.getId(),
                user.getUsername(), user.getEmail(),
                user.getPassword());
    }
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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
        return true;
    }
}
