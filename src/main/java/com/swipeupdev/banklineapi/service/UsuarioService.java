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
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Objects;
import java.util.Set;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private BCryptPasswordEncoder crypt;

    private Validator validator;

    public UsuarioService() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Transactional
    public UsuarioDto inserir(UsuarioDto dto) {
        Set<ConstraintViolation<UsuarioDto>> violations = validator.validate(dto);
        for (ConstraintViolation<UsuarioDto> violation : violations) {
            throw new InvalidArgumentException(violation.getMessage());
        }
        if(Objects.nonNull(usuarioRepository.localizarUsuarioPorLogin(dto.getLogin()))) {
            throw new ExistingRecordException("Usuário já registrado");
        }
        this.inserirUsuario(dto);
        return dto;
    }

    private Usuario inserirUsuario(UsuarioDto dto) {
        Usuario usuario = new Usuario();
        usuario.setNome(dto.getNome());
        usuario.setCpf(dto.getCpf());
        usuario.setLogin(dto.getLogin());
        usuario.setSenha(crypt.encode(dto.getSenha()));
        return usuarioRepository.save(usuario);
    }
}
