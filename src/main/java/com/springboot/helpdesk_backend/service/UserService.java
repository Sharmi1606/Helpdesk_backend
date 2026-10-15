package com.springboot.helpdesk_backend.service;

import com.springboot.helpdesk_backend.dto.request.AdminDto;
import com.springboot.helpdesk_backend.enums.Role;
import com.springboot.helpdesk_backend.model.User;
import com.springboot.helpdesk_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User getUserObj(String username, String password, Role role){
        User user =  new User(username,password,role);
        return userRepository.save(user);
    }

    public void addAdmin(AdminDto adminDto){
        User user=new User(
                adminDto.username(),
                passwordEncoder.encode(adminDto.password()),
                Role.ADMIN
        );
        userRepository.save(user);
    }
}
