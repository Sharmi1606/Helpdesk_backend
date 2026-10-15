package com.springboot.helpdesk_backend.dto.request;

import com.springboot.helpdesk_backend.enums.PlanType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CustomerPlanDto(
        @NotBlank(message = "Username is required")
        String username,

        @NotNull(message = "Plan type is required")
        PlanType planType

) {
}
