package com.springboot.helpdesk_backend.model;

import com.springboot.helpdesk_backend.enums.Priority;
import com.springboot.helpdesk_backend.enums.Status;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String subject;
    @Column(length = 1000)
    private String issue;
    @CreationTimestamp
    @Column(updatable = false)
    private Instant createdAt;
    @UpdateTimestamp
    private Instant updatedAt;
    @Enumerated(EnumType.STRING)
    private Priority priority;
    @Enumerated(EnumType.STRING)
    private Status status;


    @ManyToOne
    @JoinColumn(name="customer_id",nullable = false)
    private Customer customer;
    @ManyToOne
    @JoinColumn(name="executive_id")
    private Executive executive;


}
