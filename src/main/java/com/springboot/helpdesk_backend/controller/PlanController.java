package com.springboot.helpdesk_backend.controller;

import com.springboot.helpdesk_backend.dto.request.PlanDto;
import com.springboot.helpdesk_backend.model.Plan;
import com.springboot.helpdesk_backend.service.PlanService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PlanController {
private final PlanService planService;
    @PostMapping("/api/plan/add")
    public Plan addPlan(@Valid @RequestBody PlanDto planDto){
        return planService.addPlan(planDto);

    }
}
