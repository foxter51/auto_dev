package com.ua.kpi.developmentautomation.services;

import com.ua.kpi.developmentautomation.dto.CredentialsDto;
import com.ua.kpi.developmentautomation.dto.RegisterCredentialsDto;
import com.ua.kpi.developmentautomation.dto.UserDto;
import com.ua.kpi.developmentautomation.entities.User;
import com.ua.kpi.developmentautomation.exceptions.AppException;
import com.ua.kpi.developmentautomation.mappers.UserMapper;
import com.ua.kpi.developmentautomation.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.CharBuffer;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService {

    protected final UserRepository repo;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserDto findUserByUsername(String username){
        User user = repo.findByUsername(username)
                .orElseThrow(() -> new AppException("User not found", HttpStatus.NOT_FOUND));
        return userMapper.toUserDto(user);
    }

    public UserDto login(CredentialsDto credentialsDto){
        User user = repo.findByUsername(credentialsDto.getUsername())
                .orElseThrow(() -> new AppException("User not found", HttpStatus.NOT_FOUND));

        if (passwordEncoder.matches(CharBuffer.wrap(credentialsDto.getPassword()), user.getPassword())){
            return userMapper.toUserDto(user);
        }
        throw new AppException("Invalid password", HttpStatus.BAD_REQUEST);
    }

    public UserDto register(RegisterCredentialsDto registerCredentialsDto){
        if (repo.existsByUsername(registerCredentialsDto.getUsername())){
            throw new AppException("User with this login already exists", HttpStatus.BAD_REQUEST);
        }
        if (repo.existsByEmail(registerCredentialsDto.getEmail())){
            throw new AppException("User with this email already exists", HttpStatus.BAD_REQUEST);
        }

        User user = userMapper.registerToUser(registerCredentialsDto);
        user.setPassword(passwordEncoder.encode(CharBuffer.wrap(registerCredentialsDto.getPassword())));
        User savedUser = repo.save(user);

        return userMapper.toUserDto(savedUser);
    }

    public List<User> getAllUsers(){
        return repo.findAll();
    }

    public Optional<User> getUserById(Long id){
        return repo.findById(id);
    }

    public User saveUser(User user){
        return repo.save(user);
    }

    public void deleteUser(Long id){
        repo.deleteById(id);
    }
}
