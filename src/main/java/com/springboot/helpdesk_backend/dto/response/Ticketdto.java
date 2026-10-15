package com.springboot.helpdesk_backend.dto.response;

import com.springboot.helpdesk_backend.enums.Priority;
import com.springboot.helpdesk_backend.enums.Status;

import java.time.Instant;

public record Ticketdto(
        Long ticketId,
        Long executiveId,
        String executiveName,
        String subject,
        Priority priority,
        Status status,
        Instant createdAt,
        Long customerId,
        String customerName) {
}
