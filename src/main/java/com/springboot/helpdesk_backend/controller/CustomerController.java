package com.springboot.helpdesk_backend.controller;

import com.springboot.helpdesk_backend.dto.request.CustomerDto;
import com.springboot.helpdesk_backend.dto.request.CustomerUpdateDto;
import com.springboot.helpdesk_backend.model.Customer;
import com.springboot.helpdesk_backend.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping("/api/customer/add")
    public Customer insertCustomer(@Valid @RequestBody CustomerDto customerDto){
        return customerService.insertCustomer(customerDto);
    }
    @GetMapping("/api/customer/{id}")
    public Customer getById(@PathVariable Long id){
        return customerService.getById(id);
    }

    @GetMapping("/api/customer/all")
    public List<Customer> getAll(){
        return customerService.getAll();
    }

    @DeleteMapping("/api/customer/{id}")
    public void deleteById(@PathVariable long id){

        customerService.deleteById(id);
    }

    @PutMapping("/api/customer/{id}")
    public void update(@PathVariable Long id,
                       @RequestBody CustomerUpdateDto dto){
        customerService.update(id, dto);
    }


}
