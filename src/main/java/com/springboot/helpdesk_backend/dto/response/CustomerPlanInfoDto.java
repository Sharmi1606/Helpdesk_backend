package com.springboot.helpdesk_backend.dto.response;

import com.springboot.helpdesk_backend.enums.PlanType;

import java.time.Instant;
import java.time.LocalDate;

public record CustomerPlanInfoDto(
        String customerName,
        PlanType planType,
        LocalDate activationDate,
        LocalDate endDate,
        String coupon,
        double amountPaid,
        String email

) {
}
