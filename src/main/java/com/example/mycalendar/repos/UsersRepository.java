package com.example.mycalendar.repos;

import com.example.mycalendar.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, String> {
    boolean existsByIdIgnoreCase(String id);
}
