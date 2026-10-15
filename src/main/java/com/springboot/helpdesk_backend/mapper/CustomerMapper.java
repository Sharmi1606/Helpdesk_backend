package com.springboot.helpdesk_backend.mapper;

import com.springboot.helpdesk_backend.dto.request.CustomerDto;
import com.springboot.helpdesk_backend.model.Customer;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class CustomerMapper {

    public static Customer convertDtoToEntity(CustomerDto dto) {
        Customer customer = new Customer();
        customer.setName(dto.name());
        customer.setCity(dto.city());
        customer.setEmail(dto.email());
        return customer;
    }
}
