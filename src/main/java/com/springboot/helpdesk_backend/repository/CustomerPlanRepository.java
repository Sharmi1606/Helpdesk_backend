package com.springboot.helpdesk_backend.repository;

import com.springboot.helpdesk_backend.dto.response.CustomerPlanInfoDto;
import com.springboot.helpdesk_backend.model.CustomerPlan;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerPlanRepository extends JpaRepository<CustomerPlan,Long> {

    @Query("""
        SELECT new com.springboot.helpdesk_backend.dto.response.CustomerPlanInfoDto(
            c.name,
            p.planType,
            cp.activationDate,
            cp.endDate,
            cp.coupon,
            cp.amountPaid,
            c.email
        )
        FROM CustomerPlan cp
        JOIN cp.customer c
        JOIN cp.plan p
        JOIN c.user u
        WHERE u.username = ?1
        """)
    List<CustomerPlanInfoDto> findPlansByCustomerUsername(
            String username,
            Pageable pageable
    );


}
