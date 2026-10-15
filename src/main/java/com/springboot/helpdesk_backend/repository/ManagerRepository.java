package com.springboot.helpdesk_backend.repository;

import com.springboot.helpdesk_backend.dto.response.JobTitleExecutiveCountDto;
import com.springboot.helpdesk_backend.model.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ManagerRepository extends JpaRepository<Manager,Long> {

    @Query("""
            select e.jobTitle,count(e.id) as numberOfExecutives
            from Executive e
            Where e.jobTitle Is Not null
            group by e.jobTitle
            """)
    List<JobTitleExecutiveCountDto> getExecutiveStatWithJobTitleAndCount();
    @Query("""
            select m from Manager m
            join m.user u
            where u.username=?1
            """)
    Manager getByUsername(String managerUsername);
}
