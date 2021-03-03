package com.swipeupdev.banklineapi.service;

import com.swipeupdev.banklineapi.model.dto.NovaSenhaDto;
import com.swipeupdev.banklineapi.model.dto.UsuarioDto;
import com.swipeupdev.banklineapi.model.entity.Usuario;
import com.swipeupdev.banklineapi.model.exception.ExistingRecordException;
import com.swipeupdev.banklineapi.repository.UsuarioRepository;
import com.swipeupdev.banklineapi.util.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.SecureRandom;
import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ContaService contaService;

    @Autowired
    private PlanoContaService planoContaService;

    @Autowired
    private Validator validator;

    private BCryptPasswordEncoder crypt;

    public UsuarioService() {
        crypt = new BCryptPasswordEncoder(10, new SecureRandom());
    }

    @Transactional
    public void inserir(UsuarioDto dto) {
        this.validarInsercao(dto);
        Usuario usuario = this.inserirNovoUsuario(dto);
        contaService.novaContaParaUsuario(usuario);
        planoContaService.novosPlanoContaPadrao(usuario);
    }

    private void validarInsercao(UsuarioDto dto) {
        validator.validate(dto);

        if (usuarioRepository.existsByCpf(dto.getCpf())) {
            throw new ExistingRecordException("CPF já cadastrado.");
        } else if (usuarioRepository.existsByLogin(dto.getLogin())) {
            throw new ExistingRecordException("Login já cadastrado.");
        } else if (usuarioRepository.existsByEmail(dto.getEmail())) {
            throw new ExistingRecordException("Email já cadastrado.");
        }
    }

    private Usuario inserirNovoUsuario(UsuarioDto dto) {
        Usuario usuario = new Usuario();
        usuario.setNome(dto.getNome());
        usuario.setCpf(dto.getCpf());
        usuario.setEmail(dto.getEmail());
        usuario.setLogin(dto.getLogin());
        usuario.setSenha(crypt.encode(dto.getSenha()));
        return usuarioRepository.save(usuario);
    }

    public NovaSenhaDto novaSenha(NovaSenhaDto dto) {
        validator.validate(dto);

        Optional<Usuario> opt = usuarioRepository.findByLogin(dto.getLogin());
        if (opt.isEmpty()) {
            throw new ExistingRecordException("Login não cadastrado.");
        }

        Usuario usuario = opt.get();
        usuario.setSenhaRecuperacao(crypt.encode(dto.getSenhaRecuperacao()));
        usuario.setRecuperarSenha(true);
        usuarioRepository.save(usuario);

        // TODO: 03/03/2021 Verificar problema de validação campo login quando usado
        //  JsonIgnore (campo não é atualizado no recebimento)
        dto.setLogin(null);
        return dto;
    }
}
