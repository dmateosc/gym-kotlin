package com.cwsdg.cwsdg.repository;

import com.cwsdg.cwsdg.repository.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByDni(String dni);

    @Query("SELECT s FROM User s WHERE s.dni = :dni")
    Optional<User> findByCustomValues(String dni);

}
