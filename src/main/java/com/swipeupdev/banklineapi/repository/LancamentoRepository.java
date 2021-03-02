package com.swipeupdev.banklineapi.repository;

import com.swipeupdev.banklineapi.model.entity.Lancamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LancamentoRepository extends JpaRepository<Lancamento, Integer> {
}
