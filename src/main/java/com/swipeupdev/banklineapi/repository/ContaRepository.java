package com.swipeupdev.banklineapi.repository;

import com.swipeupdev.banklineapi.model.entity.Conta;
import com.swipeupdev.banklineapi.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Integer> {
    Optional<Conta> findByUsuario(Usuario usuario);
}
