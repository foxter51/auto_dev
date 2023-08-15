package com.ua.kpi.developmentautomation.services;

import com.ua.kpi.developmentautomation.entities.AppUser;
import com.ua.kpi.developmentautomation.repositories.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    protected final AppUserRepository repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = repo.findAppUserByUsername(username);
        if(user == null) throw new UsernameNotFoundException("User not found!");
        return user;
    }
}
