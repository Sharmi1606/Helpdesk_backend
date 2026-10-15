package com.springboot.helpdesk_backend.mapper;

import com.springboot.helpdesk_backend.dto.request.TicketDto;
import com.springboot.helpdesk_backend.dto.response.TicketRespDto;
import com.springboot.helpdesk_backend.model.Ticket;
import org.springframework.stereotype.Component;

@Component
public class TicketMapper {
    public static Ticket convertDtoToEntity(TicketDto dto){
        Ticket ticket=new Ticket();
        ticket.setSubject(dto.subject());
        ticket.setIssue(dto.issue());
        ticket.setPriority(dto.priority());
        return ticket;
    }
    public static TicketRespDto convertEntityToDto(Ticket ticket){
        return
                new TicketRespDto (
                        ticket.getId(),
                        ticket.getSubject(),
                        ticket.getCreatedAt(),
                        ticket.getPriority(),
                        ticket.getStatus()
                );
    }
}
