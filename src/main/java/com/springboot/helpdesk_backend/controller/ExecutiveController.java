package com.springboot.helpdesk_backend.controller;

import com.springboot.helpdesk_backend.dto.request.ExecutiveDto;
import com.springboot.helpdesk_backend.service.ExecutiveService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class ExecutiveController {
    private final ExecutiveService executiveService;


    @PostMapping("/api/executive/add")
    public void insertExecutive(@Valid @RequestBody ExecutiveDto executiveDto,
                                Principal principal){
        executiveService.add(executiveDto, principal.getName());
    }

}
