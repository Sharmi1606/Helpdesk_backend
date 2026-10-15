package com.springboot.helpdesk_backend.dto.response;

import com.springboot.helpdesk_backend.enums.JobTitle;

public record JobTitleExecutiveCountDto(
        JobTitle jobTitle,
        Long numberOfExecutives
) {
}
