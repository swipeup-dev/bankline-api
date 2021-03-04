package com.swipeupdev.banklineapi.repository;

import com.swipeupdev.banklineapi.model.entity.PlanoConta;
import com.swipeupdev.banklineapi.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlanoContaRepository extends JpaRepository<PlanoConta, Integer> {
    boolean existsByUsuarioAndDescricao(Usuario usuario, String descricao);
    List<PlanoConta> findAllByUsuario(Usuario usuario);
    Optional<PlanoConta> findByIdAndUsuario(int id, Usuario usuario);
}
