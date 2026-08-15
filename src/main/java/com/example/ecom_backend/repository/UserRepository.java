package com.example.ecom_backend.repository;

import com.example.ecom_backend.models.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

    Page<Users> findAll(Pageable pageable);
    Optional<Users> findByEmail(String email);
}
