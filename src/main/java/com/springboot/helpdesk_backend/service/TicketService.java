package com.springboot.helpdesk_backend.service;

import com.springboot.helpdesk_backend.dto.request.TicketDto;
import com.springboot.helpdesk_backend.dto.response.TicketInfoDto;
import com.springboot.helpdesk_backend.dto.response.TicketRespDto;
import com.springboot.helpdesk_backend.dto.response.TicketStatusCountDto;
import com.springboot.helpdesk_backend.dto.response.Ticketdto;
import com.springboot.helpdesk_backend.enums.Priority;
import com.springboot.helpdesk_backend.enums.Status;
import com.springboot.helpdesk_backend.exception.OwnershipException;
import com.springboot.helpdesk_backend.exception.ResourceNotFoundException;
import com.springboot.helpdesk_backend.mapper.TicketMapper;
import com.springboot.helpdesk_backend.model.Customer;
import com.springboot.helpdesk_backend.model.Executive;
import com.springboot.helpdesk_backend.model.Ticket;
import com.springboot.helpdesk_backend.repository.CustomerRepository;
import com.springboot.helpdesk_backend.repository.ExecutiveRepository;
import com.springboot.helpdesk_backend.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketService {
    private final TicketRepository ticketRepository;
    private final CustomerRepository customerRepository;
    private final ExecutiveRepository executiveRepository;

    public void add(String customerUsername, TicketDto ticketDto){
        // Step 1: Fetch Customer Object from DB using given customerId
        Customer customer = customerRepository.getByUsername(customerUsername);
        System.out.println("i am at line 38");
        // Step 2: Map TicketDto to Ticket entity
        Ticket ticket = TicketMapper.convertDtoToEntity(ticketDto);
        System.out.println("i am at line 41");
        // Step 3: Attach customer to Ticket
        ticket.setCustomer(customer);
        System.out.println("i am at line 44");
        // Step 3.5: Assign Status as Open to Ticket
        ticket.setStatus(Status.OPEN);
        System.out.println("i am at line 47");
        // Step 4: Insert Ticket in DB
        ticketRepository.save(ticket);
    }

    public void assignExecutive(Long executiveId,Long ticketId,String managerUsername){
        // Step 1: Fetch executive from DB using executiveId
        Executive executive = executiveRepository
                .findById(executiveId)
                .orElseThrow(()-> new ResourceNotFoundException("executive ID not found"));

        if(! (executive.getManager().getUser().getUsername().equals(managerUsername))){
            throw new OwnershipException("Operation not allowed! contact admin");
        }
        // Step 2: Fetch ticket from DB using ticketId
        Ticket ticket = ticketRepository
                .findById(ticketId)
                .orElseThrow(()-> new ResourceNotFoundException("Ticket not found"));

        // Step 3: Attach executive to ticket
        ticket.setExecutive(executive);

        // Step 4: Save ticket
        ticketRepository.save(ticket);
    }

    public List<TicketInfoDto> getTicketsByCustomerId(Long customerId, int page, int size) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer Id Invalid"));

        Pageable pageable =  PageRequest.of(page,size);
        return ticketRepository.getTicketByCustomerId(customerId,pageable);
    }

    public List<TicketInfoDto> getTicketsByCustomerUsername(String customerUsername, Integer page, Integer size) {
        Pageable pageable =  PageRequest.of(page,size);
        return ticketRepository.getTicketByCustomerUsername(customerUsername,pageable);
    }

    public List<Ticketdto> getTicketsByExecutiveUsername(String executiveUsername, Integer page, Integer size) {
        Pageable pageable =  PageRequest.of(page,size);
        return ticketRepository.getTicketByExecutiveUsername(executiveUsername,pageable);
    }

    public List<TicketStatusCountDto> getTicketStatWithTicketStatusAndCount() {
        return ticketRepository.getTicketStatWithTicketStatusAndCount();
    }

    public List<TicketRespDto> getTicketsByPriority(Priority priority) {
        List<Ticket> list =ticketRepository.findByPriority(priority);

        return list.stream()
                .map(TicketMapper::convertEntityToDto)
                .toList();
    }

    public List<TicketRespDto> getTicketsByStatus(Status status) {
        List<Ticket> list=ticketRepository.findByStatus(status);
        return list.stream()
                .map(TicketMapper::convertEntityToDto)
                .toList();
    }

    public List<TicketRespDto> getTicketsByStatusAndPriority(Status status,Priority priority) {
        List<Ticket> list=ticketRepository.findByPriorityAndStatus(status,priority);
        return
                list.stream()
                        .map(TicketMapper::convertEntityToDto)
                        .toList();
    }

    public List<TicketRespDto> dynamicFilterTicket(Priority priority,
                                                   Status status,
                                                   LocalDate fromDate,
                                                   LocalDate toDate) {

        return ticketRepository.dynamicFilterTicket(priority,
                status,
                fromDate == null ? null: fromDate.atStartOfDay(ZoneOffset.UTC).toInstant(),
                toDate == null? null:toDate.atStartOfDay(ZoneOffset.UTC).toInstant());
    }
}
