package com.springboot.helpdesk_backend.repository;

import com.springboot.helpdesk_backend.model.Executive;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExecutiveRepository extends JpaRepository<Executive,Long> {
}
