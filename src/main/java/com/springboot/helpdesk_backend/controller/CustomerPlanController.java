package com.springboot.helpdesk_backend.controller;

import com.springboot.helpdesk_backend.dto.request.CustomerPlanDto;
import com.springboot.helpdesk_backend.dto.response.CustomerPlanInfoDto;
import com.springboot.helpdesk_backend.service.CustomerService;
import com.springboot.helpdesk_backend.service.PlanService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CustomerPlanController {
    /*
    API to add customer buys plan record in customer_plan table with all relevant fields
    * */

    /*
     * GET API : to fetch all plans based on customer username.
     * */

    private final PlanService planService;
    @PostMapping("/api/customerplan/add")
    public void insertCustomerPlan(@Valid @RequestBody CustomerPlanDto customerPlanDto){
        planService.insertCustomerPlan(customerPlanDto);

    }
    @GetMapping("/api/customerplan")
    public List<CustomerPlanInfoDto> fetchAllPlans(@RequestParam("username") String customerUsername,
                                                   @RequestParam(name="page",required = false,defaultValue = "0") Integer page,
                                                   @RequestParam(name="size",required = false,defaultValue = "20")Integer size){
                   return planService.fetchAllPlans(customerUsername,page,size);
    }
}
