package com.sales.repository;

import com.sales.model.Excursion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ExcursionRepository extends JpaRepository<Excursion, UUID> {

}
