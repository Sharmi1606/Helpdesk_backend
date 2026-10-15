package com.springboot.helpdesk_backend.controller;

import com.springboot.helpdesk_backend.dto.request.ManagerDto;
import com.springboot.helpdesk_backend.dto.response.JobTitleExecutiveCountDto;
import com.springboot.helpdesk_backend.enums.JobTitle;
import com.springboot.helpdesk_backend.service.ManagerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ManagerController {
    private final ManagerService managerService;

    @PostMapping("/api/manager/add")
    public void add(@Valid @RequestBody ManagerDto managerDto){
        managerService.add(managerDto);
    }

    @GetMapping("/api/executive/count/jobtitle")
    public List<JobTitleExecutiveCountDto> getExecutiveStatWithJobTitleAndCount(){
      return managerService.getExecutiveStatWithJobTitleAndCount();
    }

    @PutMapping("/api/jobtitle/update/{executiveId}")
    public void updateJobtitle(@PathVariable long executiveId,
                              @RequestParam("jobTitle")JobTitle jobTitle){
        managerService.updateJobtitle(executiveId,jobTitle);

    }

}
