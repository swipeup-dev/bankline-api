package com.swipeupdev.banklineapi.repository;

import com.swipeupdev.banklineapi.model.entity.PlanoConta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanoContaRepository extends JpaRepository<PlanoConta, Integer> {
}
