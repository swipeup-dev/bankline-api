package com.swipeupdev.banklineapi.repository;

import com.swipeupdev.banklineapi.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    @Query(value = "SELECT u FROM Usuario u WHERE u.login = :login")
    Usuario localizarUsuarioPorLogin(@Param(value = "login") String login);
}
