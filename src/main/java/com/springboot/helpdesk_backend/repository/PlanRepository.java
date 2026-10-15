package com.springboot.helpdesk_backend.repository;

import com.springboot.helpdesk_backend.enums.PlanType;
import com.springboot.helpdesk_backend.model.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface PlanRepository extends JpaRepository<Plan,Long> {
    Optional<Plan> findByPlanType(PlanType planType);

}
