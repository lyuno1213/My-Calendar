package com.example.mycalendar.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.mycalendar.domain.Contents;
import com.example.mycalendar.domain.Users;

public interface ContentsRepository extends JpaRepository<Contents, String> {

    Contents findFirstByUsersId(Users users);

    boolean existsByIdIgnoreCase(String id);

}
