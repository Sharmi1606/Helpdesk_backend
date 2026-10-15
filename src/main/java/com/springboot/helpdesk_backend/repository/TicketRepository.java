package com.springboot.helpdesk_backend.repository;

import com.springboot.helpdesk_backend.dto.response.*;
import com.springboot.helpdesk_backend.enums.Priority;
import com.springboot.helpdesk_backend.enums.Status;
import com.springboot.helpdesk_backend.model.Ticket;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket,Long> {
    @Query("""
            select new com.springboot.helpdesk_backend.dto.response.TicketInfoDto(t.id,t.subject,
            t.createdAt,t.priority,t.status,c.name,e.name,e.email)
            from Ticket t
            JOIN t.customer c
            JOIN t.executive e
            where c.id = ?1
            """)
    List<TicketInfoDto> getTicketByCustomerId(Long customerId, Pageable pageable);
    @Query("""
            select new com.springboot.helpdesk_backend.dto.response.TicketInfoDto(t.id,t.subject,
            t.createdAt,t.priority,t.status,c.name,e.name,e.email)
            from Ticket t
            JOIN t.customer c
            JOIN t.executive e
            JOIN c.user u
            where u.username = ?1
            """)
    List<TicketInfoDto> getTicketByCustomerUsername(String customerUsername, Pageable pageable);
@Query("""
        SELECT new com.springboot.helpdesk_backend.dto.response.Ticketdto(
            t.id,e.id,e.name,t.subject,t.priority,t.status,t.createdAt,c.id,c.name
        )
        FROM Ticket t
        JOIN t.executive e
        JOIN e.user u
        JOIN t.customer c
        JOIN e.manager m
        WHERE u.username = ?1
        """)
    List<Ticketdto> getTicketByExecutiveUsername(String executiveUsername, Pageable pageable);
@Query("""
        select t.status as TicketStatus ,count(c.id) as numOfCustomer
        from Ticket t join t.customer c
        group by t.status
        """)
    List<TicketStatusCountDto> getTicketStatWithTicketStatusAndCount();

    List<Ticket> findByPriority(Priority priority);

    List<Ticket> findByStatus(Status status);

    List<Ticket> findByPriorityAndStatus(Status status, Priority priority);


    @Query("""
            select t.id as ticketId, t.subject as subject, t.createdAt as createdAt, t.priority as priority, t.status as status
            from Ticket t
            where (?1 IS NULL OR t.priority = ?1)
            AND  (?2 IS NULL OR t.status = ?2)
            AND (?3 IS NULL OR t.createdAt >= ?3)
            AND (?4 IS NULL OR t.createdAt <= ?4)
            """)
    List<TicketRespDto> dynamicFilterTicket(Priority priority,
                                            Status status,
                                            Instant fromDate,
                                            Instant toDate);
}
