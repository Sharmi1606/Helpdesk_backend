package com.springboot.helpdesk_backend.controller;

import com.springboot.helpdesk_backend.dto.request.TicketDto;
import com.springboot.helpdesk_backend.dto.response.*;
import com.springboot.helpdesk_backend.enums.Priority;
import com.springboot.helpdesk_backend.enums.Status;
import com.springboot.helpdesk_backend.service.TicketService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class TicketController {
    private final TicketService ticketService;

    @PostMapping("/api/ticket/add")
    public void postTicket(Principal principal,
                           @Valid @RequestBody TicketDto ticketDto){
        ticketService.add(principal.getName(), ticketDto);
    }
    // Done by Manager - activate the check after security config
    @PutMapping("/api/ticket/assign/{executiveId}/{ticketId}")
    public void assignExecutive(@PathVariable Long  executiveId ,
                                @PathVariable Long ticketId,
                                Principal principal){
        ticketService.assignExecutive(executiveId, ticketId,principal.getName());
    }
    @GetMapping("/api/ticket/v1/{customerId}")
    public List<TicketInfoDto> getTicketsByCustomerId(@PathVariable Long customerId,
                                                      @RequestParam("page") int page,
                                                      @RequestParam("size") int size){
        return ticketService.getTicketsByCustomerId(customerId,page,size);
    }
    @GetMapping("/api/ticket/v2")
    public List<TicketInfoDto> getTicketsByCustomerUsername(@RequestParam("username") String customerUsername,
                                                            @RequestParam(name = "page" , required = false, defaultValue = "0") Integer page,
                                                            @RequestParam(name = "size", required = false, defaultValue = "20") Integer size){
        return ticketService.getTicketsByCustomerUsername(customerUsername,page,size);
    }
@GetMapping("/api/ticket/v3")
    public List<Ticketdto> getTicketsByExecutiveUsername(@RequestParam("username") String executiveUsername,
                                                            @RequestParam(name = "page" , required = false, defaultValue = "0") Integer page,
                                                            @RequestParam(name = "size", required = false, defaultValue = "20") Integer size){
        return ticketService.getTicketsByExecutiveUsername(executiveUsername,page,size);
}

    @GetMapping("/api/ticket/count/ticketStatus")
    public List<TicketStatusCountDto> getTicketStatWithTicketStatusAndCount(){
        return ticketService.getTicketStatWithTicketStatusAndCount();
    }
@GetMapping("api/ticket/by-priority")
    public List<TicketRespDto> getTicketsByPriority(@RequestParam("priority")Priority priority){
return ticketService.getTicketsByPriority(priority);
    }
@GetMapping("api/ticket/by-status")
    public List<TicketRespDto> getTicketsByStatus(@RequestParam("status")Status status){
        return ticketService.getTicketsByStatus(status);
    }
    @GetMapping("api/ticket/by-priorityandstatus")
    public List<TicketRespDto> getTicketsByStatusAndPriority(@RequestParam("status")Status status,
                                                             @RequestParam("priority")Priority priority){
        return ticketService.getTicketsByStatusAndPriority(status,priority);
    }
    @GetMapping("/api/ticket/filter")
    public List<TicketRespDto> dynamicFilterTicket(@RequestParam(value = "priority", required = false) Priority priority,
                                                   @RequestParam(value = "status", required = false)Status status,
                                                   @RequestParam(value = "fromDate", required = false) LocalDate fromDate,
                                                   @RequestParam(value = "toDate", required = false) LocalDate toDate
    ){
        return ticketService.dynamicFilterTicket(priority,status, fromDate, toDate);
    }


}
