package com.springboot.helpdesk_backend.dto.response;

import com.springboot.helpdesk_backend.enums.Status;

public record TicketStatusCountDto(
        Status status,
        Long numofCustomers
) {

}
