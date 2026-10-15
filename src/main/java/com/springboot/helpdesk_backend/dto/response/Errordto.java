package com.springboot.helpdesk_backend.dto.response;

import java.time.Instant;

public record Errordto(
        String message,
        String comments,
        Instant timestamp
) {
}
