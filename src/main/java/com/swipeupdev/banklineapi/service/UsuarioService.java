package com.swipeupdev.banklineapi.service;

import com.swipeupdev.banklineapi.model.dto.UsuarioDto;
import com.swipeupdev.banklineapi.model.entity.Usuario;
import com.swipeupdev.banklineapi.model.exception.ExistingRecordException;
import com.swipeupdev.banklineapi.model.exception.InvalidArgumentException;
import com.swipeupdev.banklineapi.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.security.SecureRandom;
import java.util.Objects;
import java.util.Set;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private Validator validator;

    private BCryptPasswordEncoder crypt;

    public UsuarioService() {
        crypt = new BCryptPasswordEncoder(10, new SecureRandom());
    }

    @Transactional
    public void inserir(UsuarioDto dto) {
        this.validate(dto);
        this.inserirNovoUsuario(dto);
        //INSERIR CONTA (1)
        //INSERIR PLANOS DE CONTA PADRÃO(4)
    }

    private void validate(UsuarioDto dto) {
        Set<ConstraintViolation<UsuarioDto>> violations = validator.validate(dto);
        for (ConstraintViolation<UsuarioDto> violation : violations) {
            throw new InvalidArgumentException(violation.getMessage());
        }

        if (Objects.nonNull(usuarioRepository.localizarUsuarioPorCpf(dto.getCpf()))) {
            throw new ExistingRecordException("CPF já cadastrado.");
        } else if (Objects.nonNull(usuarioRepository.localizarUsuarioPorLogin(dto.getLogin()))) {
            throw new ExistingRecordException("Login já cadastrado.");
        }
    }

    private Usuario inserirNovoUsuario(UsuarioDto dto) {
        Usuario usuario = new Usuario();
        usuario.setNome(dto.getNome());
        usuario.setCpf(dto.getCpf());
        usuario.setLogin(dto.getLogin());
        usuario.setSenha(crypt.encode(dto.getSenha()));
        return usuarioRepository.save(usuario);
    }

}
