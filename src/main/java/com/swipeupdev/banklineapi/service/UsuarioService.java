package com.swipeupdev.banklineapi.service;

import com.swipeupdev.banklineapi.model.dto.AtualizadorSenhaDto;
import com.swipeupdev.banklineapi.model.dto.LoginDto;
import com.swipeupdev.banklineapi.model.dto.NovaSenhaDto;
import com.swipeupdev.banklineapi.model.dto.SessaoDto;
import com.swipeupdev.banklineapi.model.dto.UsuarioDto;
import com.swipeupdev.banklineapi.model.entity.Usuario;
import com.swipeupdev.banklineapi.model.exception.EntityRequirementException;
import com.swipeupdev.banklineapi.model.exception.ExistingRecordException;
import com.swipeupdev.banklineapi.model.exception.RecordNotFoundException;
import com.swipeupdev.banklineapi.model.security.UserSecurity;
import com.swipeupdev.banklineapi.repository.UsuarioRepository;
import com.swipeupdev.banklineapi.util.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
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

    @Autowired
    private BCryptPasswordEncoder crypt;

    @Autowired
    private UserSecurity userSecurity;

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

    @Transactional
    private Usuario inserirNovoUsuario(UsuarioDto dto) {
        Usuario usuario = new Usuario();
        usuario.setNome(dto.getNome());
        usuario.setCpf(dto.getCpf());
        usuario.setEmail(dto.getEmail());
        usuario.setLogin(dto.getLogin());
        usuario.setSenha(crypt.encode(dto.getSenha()));
        return usuarioRepository.save(usuario);
    }

    @Transactional
    public NovaSenhaDto novaSenha(NovaSenhaDto dto) {
        validator.validate(dto);

        Usuario usuario = getUsuarioExistente(dto.getLogin());
        usuario.setSenhaRecuperacao(crypt.encode(dto.getSenhaRecuperacao()));
        usuario.setRecuperarSenha(true);
        usuarioRepository.save(usuario);

        // TODO: 03/03/2021 Verificar problema de validação campo login quando usado
        //  JsonIgnore (campo não é atualizado no recebimento)
        dto.setLogin(null);
        return dto;
    }

    @Transactional
    public void alterarSenha(AtualizadorSenhaDto dto) {
        validator.validate(dto);

        Usuario usuario = getUsuarioExistente(dto.getLogin());
        if (!usuario.getRecuperarSenha()) {
            throw new EntityRequirementException("Solicitação de nova senha não foi realizada.");
        }

        if (!BCrypt.checkpw(dto.getSenhaRecuperacao(), usuario.getSenhaRecuperacao())) {
            throw new EntityRequirementException("Senha de recuperação inválida.");
        }

        usuario.setSenha(crypt.encode(dto.getNovaSenha()));
        usuario.setRecuperarSenha(false);
        usuario.setSenhaRecuperacao(null);
        usuarioRepository.save(usuario);
    }

    protected Usuario getUsuarioExistente(String login) {
        Optional<Usuario> opt = usuarioRepository.findByLogin(login);
        if (opt.isEmpty()) {
            throw new RecordNotFoundException("Login não cadastrado.");
        }

        return opt.get();
    }

    @Transactional
    public SessaoDto logar(LoginDto dto) {
        validator.validate(dto);
        Usuario usuario = getUsuarioExistente(dto.getLogin());

        if (!BCrypt.checkpw(dto.getSenha(), usuario.getSenha())) {
            throw new EntityRequirementException("Senha inválida.");
        }

        SessaoDto sessao = new SessaoDto();
        sessao.setDataInicio(new Date(System.currentTimeMillis()));
        sessao.setDataFim(new Date(System.currentTimeMillis() + userSecurity.getExpiration()));
        sessao.setLogin(usuario.getLogin());
        sessao.setToken(userSecurity.generateToken(sessao));

        return sessao;
    }

}
