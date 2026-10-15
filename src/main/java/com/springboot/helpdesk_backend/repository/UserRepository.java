package com.springboot.helpdesk_backend.repository;

import com.springboot.helpdesk_backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    @Query("""
            select u
            from User u
            where u.username=?1 AND u.isActive = ?2
            """)
    User loadUserByUsername(String username, boolean b);
}
