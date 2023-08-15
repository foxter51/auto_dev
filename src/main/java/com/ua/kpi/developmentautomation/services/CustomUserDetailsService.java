package com.ua.kpi.developmentautomation.services;

import com.ua.kpi.developmentautomation.entities.AppUser;
import com.ua.kpi.developmentautomation.repositories.AppUserRepository;
import com.ua.kpi.developmentautomation.security.UserDetailsImpl;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    protected final AppUserRepository repo;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = repo.findAppUserByUsername(username);
        if(user == null) throw new UsernameNotFoundException("User not found!");
        return UserDetailsImpl.build(user);
    }

    public AppUser registerUser(AppUser newUser){
        if(repo.findAppUserByUsername(newUser.getUsername()) == null){
            newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));
            return repo.save(newUser);
        }
        return null;
    }

    public boolean login(String username, String password) {
        AppUser user = repo.findAppUserByUsername(username);
        if (user == null) {
            return false;
        } else return bCryptPasswordEncoder.matches(password, user.getPassword());
    }

    public List<AppUser> getAllUsers(){
        return repo.findAll();
    }

    public Optional<AppUser> getUserById(Long id){
        return repo.findById(id);
    }

    public AppUser saveUser(AppUser user){
        return repo.save(user);
    }

    public void deleteUser(Long id){
        repo.deleteById(id);
    }
}
