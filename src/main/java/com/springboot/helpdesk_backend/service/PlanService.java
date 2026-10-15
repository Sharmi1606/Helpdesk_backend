package com.springboot.helpdesk_backend.service;

import com.springboot.helpdesk_backend.dto.request.CustomerPlanDto;
import com.springboot.helpdesk_backend.dto.request.PlanDto;
import com.springboot.helpdesk_backend.dto.response.CustomerPlanInfoDto;
import com.springboot.helpdesk_backend.exception.ResourceNotFoundException;
import com.springboot.helpdesk_backend.model.Customer;
import com.springboot.helpdesk_backend.model.CustomerPlan;
import com.springboot.helpdesk_backend.model.Plan;
import com.springboot.helpdesk_backend.repository.CustomerPlanRepository;
import com.springboot.helpdesk_backend.repository.CustomerRepository;
import com.springboot.helpdesk_backend.repository.PlanRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PlanService {
   private final PlanRepository planRepository;
   private final CustomerRepository customerRepository;
   private final CustomerPlanRepository customerPlanRepository;
    public Plan addPlan(@Valid PlanDto planDto) {
        Plan plan=new Plan();
        plan.setPlanType(planDto.planType());
        plan.setPrice(planDto.price());
        return planRepository.save(plan);
    }

    public void insertCustomerPlan(@Valid CustomerPlanDto customerPlanDto) {
        //Find Customer
        Customer customer= (Customer) customerRepository.findByUserUsername(customerPlanDto.username())
                .orElseThrow(() ->
                        new RuntimeException("Customer not found"));

        //Find Plan

        Plan plan=planRepository.findByPlanType(customerPlanDto.planType()).orElseThrow(()-> new ResourceNotFoundException("Plan not found"));
//Create Customer plan
        CustomerPlan customerPlan = new CustomerPlan();

        customerPlan.setCustomer(customer);
        customerPlan.setPlan(plan);
        LocalDateTime dt3 = LocalDateTime.now(ZoneId.of("UTC"));
        customerPlan.setActivationDate(LocalDate.from(dt3));
        customerPlan.setEndDate(LocalDate.from(dt3).plusMonths(1));
        customerPlan.setAmountPaid(plan.getPrice());
        customerPlanRepository.save(customerPlan);


    }

    public List<CustomerPlanInfoDto> fetchAllPlans(String customerUsername, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);

        return customerPlanRepository
                .findPlansByCustomerUsername(customerUsername, pageable);
    }
}
