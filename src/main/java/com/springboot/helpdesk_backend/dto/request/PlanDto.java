package com.springboot.helpdesk_backend.dto.request;

import com.springboot.helpdesk_backend.enums.PlanType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PlanDto(
        @NotNull
        PlanType planType,
        double price
) {
}
