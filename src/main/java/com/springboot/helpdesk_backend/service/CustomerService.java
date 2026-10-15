package com.springboot.helpdesk_backend.service;

import com.springboot.helpdesk_backend.dto.request.CustomerDto;
import com.springboot.helpdesk_backend.dto.request.CustomerUpdateDto;
import com.springboot.helpdesk_backend.enums.Role;
import com.springboot.helpdesk_backend.exception.InvalidCallException;
import com.springboot.helpdesk_backend.exception.ResourceNotFoundException;
import com.springboot.helpdesk_backend.mapper.CustomerMapper;
import com.springboot.helpdesk_backend.model.Customer;
import com.springboot.helpdesk_backend.model.User;
import com.springboot.helpdesk_backend.repository.CustomerRepository;
import com.springboot.helpdesk_backend.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final UserRepository userRepository;
private final PasswordEncoder passwordEncoder;
    public Customer insertCustomer(@Valid CustomerDto customerDto) {
        // Step 1: Prepare user obj and save it in DB
        User user = new User();
        user.setUsername(customerDto.username());
        user.setPassword(customerDto.password());
        user.setRole(Role.CUSTOMER);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user=userRepository.save(user);
        // Map CustomerDto to Customer entity
        Customer customer= CustomerMapper.convertDtoToEntity(customerDto);
        // Save Customer entity
        customer.setUser(user);
        return customerRepository.save(customer);
    }

    public List<Customer> getAll() {
        return customerRepository.findAll();

    }

    public Customer getById(Long id) {
        Optional<Customer> optional=customerRepository.findById(id);
        if(optional.isEmpty()){
            throw new ResourceNotFoundException("Invalid customer ID");
        }
            return optional.get();
    }

    public void deleteById(long id) {
        getById(id);
        customerRepository.deleteById(id);
    }

    public void update(Long id, CustomerUpdateDto dto){
        Customer customer=getById(id);
        boolean flag=false;

        if(!(dto.name()==null)&&!(dto.name().trim().isEmpty())){
            customer.setName((dto.name()));
        }
        if(!(dto.city()==null)&&!(dto.city().trim().isEmpty())){
            customer.setCity(dto.city());
            flag=true;
        }

        if(!flag){
            throw  new InvalidCallException("Update output terminated");


        }
        customerRepository.save(customer);
    }

}
