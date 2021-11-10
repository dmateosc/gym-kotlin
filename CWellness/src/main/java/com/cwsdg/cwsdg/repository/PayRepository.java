package com.cwsdg.cwsdg.repository;

import com.cwsdg.cwsdg.repository.entity.Pay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PayRepository extends JpaRepository<Pay, Long > {




}
