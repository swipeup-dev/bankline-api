package com.swipeupdev.banklineapi.repository;

import com.swipeupdev.banklineapi.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    boolean existsByCpf(String cpf);
    boolean existsByLogin(String login);
    Optional<Usuario> findByLogin(String login);
}
