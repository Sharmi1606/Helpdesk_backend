package com.springboot.helpdesk_backend.service;

import com.springboot.helpdesk_backend.dto.request.ManagerDto;
import com.springboot.helpdesk_backend.dto.response.JobTitleExecutiveCountDto;
import com.springboot.helpdesk_backend.enums.JobTitle;
import com.springboot.helpdesk_backend.enums.Role;
import com.springboot.helpdesk_backend.enums.Status;
import com.springboot.helpdesk_backend.exception.ResourceNotFoundException;
import com.springboot.helpdesk_backend.mapper.ManagerMapper;
import com.springboot.helpdesk_backend.model.*;
import com.springboot.helpdesk_backend.repository.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ManagerService {
    private final UserRepository userRepository;
    private final ManagerRepository managerRepository;
    private final ExecutiveRepository executiveRepository;
    private final TicketRepository ticketRepository;
    private final PasswordEncoder passwordEncoder;


    public void add(@Valid ManagerDto managerDto) {
        User user =new User();
        user.setUsername(managerDto.username());
        user.setPassword(passwordEncoder.encode(managerDto.password()));
        user.setRole(Role.MANAGER);
        user=userRepository.save(user);

        Manager manager= ManagerMapper.convertDtoToEntity(managerDto);
        manager.setUser(user);
        managerRepository.save(manager);
    }

    public List<JobTitleExecutiveCountDto> getExecutiveStatWithJobTitleAndCount() {
        return managerRepository.getExecutiveStatWithJobTitleAndCount();
    }

    public void updateJobtitle(long executiveId, JobTitle jobTitle) {
        Executive executive=executiveRepository.findById(executiveId).orElseThrow(()-> new ResourceNotFoundException("Executive Not Found"));

        executive.setJobTitle(jobTitle);
        executiveRepository.save(executive);
    }

    public void updateTicketStatus(long ticketId, Status status) {
     Ticket ticket= ticketRepository.findById(ticketId).orElseThrow(()->new ResourceNotFoundException("Ticket Not Found"));

     ticket.setStatus(status);
     ticketRepository.save(ticket);
    }

    public void updateJobTitle(long executiveId, JobTitle jobTitle) {
        // Step 1: Fetch executive from DB using executiveId
        Executive executive = executiveRepository.findById(executiveId)
                .orElseThrow(()-> new ResourceNotFoundException("Executive Not Found"));

        // Step 2: Update job title
        executive.setJobTitle(jobTitle);

        // Step 3: Save in DB
        executiveRepository.save(executive);
    }
}
