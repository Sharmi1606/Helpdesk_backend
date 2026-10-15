package com.springboot.helpdesk_backend.model;

import com.springboot.helpdesk_backend.enums.PlanType;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Plan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    PlanType planType;
    double price;
}
