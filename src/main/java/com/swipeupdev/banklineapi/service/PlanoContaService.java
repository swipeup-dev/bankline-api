package com.swipeupdev.banklineapi.service;

import com.swipeupdev.banklineapi.model.dto.PlanoContaDto;
import com.swipeupdev.banklineapi.model.entity.PlanoConta;
import com.swipeupdev.banklineapi.model.entity.Usuario;
import com.swipeupdev.banklineapi.model.enums.TipoTransacao;
import com.swipeupdev.banklineapi.model.exception.ExistingRecordException;
import com.swipeupdev.banklineapi.model.exception.InvalidArgumentException;
import com.swipeupdev.banklineapi.model.exception.RecordNotFoundException;
import com.swipeupdev.banklineapi.model.security.UserSecurity;
import com.swipeupdev.banklineapi.repository.PlanoContaRepository;
import com.swipeupdev.banklineapi.util.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PlanoContaService {
    @Autowired
    private PlanoContaRepository planoContaRepository;

    @Autowired
    private Validator validator;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UserSecurity userSecurity;

    @Transactional
    protected void novosPlanoContaPadrao(Usuario usuario) {
        PlanoConta p1 = novoPlanoContaPadrao(PlanoConta.PLANO_PADRAO_R, usuario, TipoTransacao.ENTRADA);
        PlanoConta p2 = novoPlanoContaPadrao(PlanoConta.PLANO_PADRAO_D, usuario, TipoTransacao.SAIDA);
        PlanoConta p3 = novoPlanoContaPadrao(PlanoConta.PLANO_PADRAO_TC, usuario, TipoTransacao.SAIDA);
        PlanoConta p4 = novoPlanoContaPadrao(PlanoConta.PLANO_PADRAO_TU, usuario, TipoTransacao.SAIDA);

        planoContaRepository.saveAll(Arrays.asList(p1, p2, p3, p4));
    }

    private PlanoConta novoPlanoContaPadrao(String desc, Usuario usuario, TipoTransacao tt) {
        return novoPlanoConta(desc, usuario, true, tt);
    }

    private PlanoConta novoPlanoContaUsuario(String desc, Usuario usuario, TipoTransacao tt) {
        return novoPlanoConta(desc, usuario, false, tt);
    }

    private PlanoConta novoPlanoConta(String desc, Usuario user, boolean padrao, TipoTransacao tt) {
        PlanoConta pc = new PlanoConta();
        pc.setDescricao(desc);
        pc.setUsuario(user);
        pc.setPadrao(padrao);
        pc.setTipoTransacao(tt);

        return pc;
    }

    @Transactional
    public void inserir(PlanoContaDto dto) {
        validator.validate(dto);
        userSecurity.validateAuthenticantion(dto.getLogin());
        Usuario usuario = usuarioService.getUsuarioExistente(dto.getLogin());

        if (planoContaRepository.existsByUsuarioAndDescricao(usuario, dto.getDescricao())) {
            throw new ExistingRecordException("Plano de conta já cadastrado para este usuário.");
        }

        planoContaRepository.save(novoPlanoContaUsuario(
                dto.getDescricao(), usuario, dto.getTipoTransacao()));
    }

    public List<PlanoConta> listarPlanosContaUsuario(String login) {
        if (Objects.requireNonNullElse(login, "").isBlank()) {
            throw new InvalidArgumentException("O 'login' não pode ser em branco.");
        }

        userSecurity.validateAuthenticantion(login);
        Usuario usuario = usuarioService.getUsuarioExistente(login);
        return planoContaRepository.findAllByUsuario(usuario);
    }

    protected PlanoConta getPlanoContaUsuario(int id, Usuario usuario) {
        Optional<PlanoConta> opt = planoContaRepository.findByIdAndUsuario(id, usuario);
        if (opt.isEmpty()) {
            throw new RecordNotFoundException(
                    String.format("Plano de conta id:%d não encontrado para usuário %s",
                            id, usuario.getLogin()));
        }
        return opt.get();
    }
}
