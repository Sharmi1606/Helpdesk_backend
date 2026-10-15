package com.springboot.helpdesk_backend.service;

import com.springboot.helpdesk_backend.dto.request.ExecutiveDto;
import com.springboot.helpdesk_backend.enums.Role;
import com.springboot.helpdesk_backend.exception.ResourceNotFoundException;
import com.springboot.helpdesk_backend.mapper.ExecutiveMapper;
import com.springboot.helpdesk_backend.model.Executive;
import com.springboot.helpdesk_backend.model.Manager;
import com.springboot.helpdesk_backend.model.User;
import com.springboot.helpdesk_backend.repository.ExecutiveRepository;
import com.springboot.helpdesk_backend.repository.ManagerRepository;
import com.springboot.helpdesk_backend.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExecutiveService {
    private final ExecutiveRepository executiveRepository;
    private final UserRepository userRepository;
    private final UserService userService;
    private final ManagerRepository managerRepository;
    private final PasswordEncoder passwordEncoder;


    public void add(@Valid ExecutiveDto executiveDto, String managerUsername) {

        Manager manager =  managerRepository.getByUsername(managerUsername);

        User user = userService.getUserObj(  // <-- this is a full created user obj with id
                executiveDto.username(),
                executiveDto.password(),
                Role.EXECUTIVE
        );

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user=userRepository.save(user);

        Executive executive= ExecutiveMapper.mapDtoToEntity(executiveDto);
        executive.setUser(user);
        executive.setManager(manager);
        executiveRepository.save(executive);
    }

}
