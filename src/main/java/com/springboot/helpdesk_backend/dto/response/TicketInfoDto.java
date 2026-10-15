package com.springboot.helpdesk_backend.dto.response;

import com.springboot.helpdesk_backend.enums.Priority;
import com.springboot.helpdesk_backend.enums.Status;

import java.time.Instant;

public record TicketInfoDto (
        Long ticketId,
        String subject,
        Instant createdAt,
        Priority priority,
        Status status,
        String customerName,
        String executiveName,
        String executiveEmail
){
}
